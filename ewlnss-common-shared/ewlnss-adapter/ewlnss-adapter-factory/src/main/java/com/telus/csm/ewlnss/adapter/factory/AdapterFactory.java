package com.telus.csm.ewlnss.adapter.factory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import com.telus.channelsalesmgmt.common.aspect.AspectWeaver;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.aspect.FlatProfilingAspect;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;
import com.telus.csm.ewlnss.adapter.common.ServiceAdapterBase;

public abstract class AdapterFactory {

	private static LoggerUtil logger = LoggerUtil.getLogger(AdapterFactory.class);
	
	/** The default aspects. */
	private static Aspect[] keyServiceProfileAspects = null;
	
	private static HashMap<String, Class<IAdapterBase>> adapters = new HashMap<String, Class<IAdapterBase>>();

	static {
		Aspect flatProfilingAspect = new FlatProfilingAspect();
		Aspect adapterAspect = new AdapterAspect();
		keyServiceProfileAspects = new Aspect[2];
		// logging
		keyServiceProfileAspects[0] = adapterAspect;
		// profile aspects
		keyServiceProfileAspects[1] = flatProfilingAspect;
		
		//init table of all adapters
		initAdapterTable();
		
	}

	private AdapterFactory() {
	}


	private static void initAdapterTable() {
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AssignableTypeFilter(ServiceAdapterBase.class));

		Set<BeanDefinition> components = provider.findCandidateComponents("com/telus/csm/ewlnsc/adapter/impl");
		int ct = 0;
		for (BeanDefinition component : components) {
			String beanClassName = component.getBeanClassName();
			Class<IAdapterBase> cls = null;
			try {
				cls = (Class<IAdapterBase>) Class.forName(beanClassName);
			} catch (ClassNotFoundException e) {
				logger.error("error", "initAdapterTable", "ClassNotFound:" + beanClassName, e);
				throw new RuntimeException(e);
			} catch (ClassCastException e) {
				logger.info("initAdapterTable", beanClassName + " cannot be casted to IAdapterBase");
			} catch (Exception e) {
				logger.error("error", "initAdapterTable", "Exception initializing:" + beanClassName, e);
				throw new RuntimeException(e);
			} catch (ExceptionInInitializerError e) {
				logger.error("error", "initAdapterTable", "Exception initializing:" + beanClassName, e);
				throw e;
			}

			if (cls != null) {
				for (Class<?> interfaceClass : cls.getInterfaces()) {
					if (IAdapterBase.class.isAssignableFrom(interfaceClass)) {
						logger.info("Adapter interface " + ++ct + ": ", interfaceClass.getSimpleName() + " " + beanClassName);
						adapters.put(interfaceClass.getSimpleName(), cls);
					}
				}
			}

		}

	}

	public static <T extends IAdapterBase> T getAdapter(final Class<T> adapterType) {

		return getAdapter(adapterType, null);		
	}

	@SuppressWarnings("unchecked")
	public static <T extends IAdapterBase> T getAdapter(final Class<T> adapterType, final AdapterFeatureDriver adapterFeatureDriver) {

		IAdapterBase result = getAdapter(adapterType.getSimpleName(), adapterFeatureDriver);
		return (T) result;		
	}
	
	public static IAdapterBase getAdapter(final String interfaceName) {
		return getAdapter(interfaceName, null);
	}
	
	private static IAdapterBase getAdapter(final String interfaceName, final AdapterFeatureDriver adapterFeatureDriver) {

		IAdapterBase result = getAdapterBase(interfaceName, adapterFeatureDriver);
	
		try {
			result = (IAdapterBase) AspectWeaver.weave(result, keyServiceProfileAspects);
		} catch (Exception e) {
			logger.warn(null,"getAdapter","Could not weave " + interfaceName + " , Returning Adapter without weaving it",e);
		}
		
		return result;

	}

	private static IAdapterBase getAdapterBase(final String interfaceName, AdapterFeatureDriver adapterFeatureDriverIn) {

		Class<IAdapterBase> adapterClass = adapters.get(interfaceName);
		if (adapterClass == null) {
			throw new NoClassDefFoundError("No adapter exists for: " + interfaceName);
		}
		
		try {
			AdapterFeatureDriver adapterFeatureDriver = adapterFeatureDriverIn;
			if (adapterFeatureDriverIn == null) {
				adapterFeatureDriver = new AdapterFeatureDriver();
			}
			Constructor<IAdapterBase> constructor;
			try {
				constructor = adapterClass.getConstructor(AdapterFeatureDriver.class);
				return constructor.newInstance(adapterFeatureDriver);
			} catch (Exception e) {
			}
			return adapterClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public static List<IAdapterBase> getAllAdapters() {
		
		List<IAdapterBase> result = new ArrayList<IAdapterBase>();
		
		for (String interfaceName : adapters.keySet()) {
			result.add(getAdapter(interfaceName));
		}
		
		return result;
	}

}
