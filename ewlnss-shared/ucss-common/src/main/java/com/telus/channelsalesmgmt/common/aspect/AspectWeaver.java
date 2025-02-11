/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;

// TODO: Auto-generated Javadoc
/**
 * The Class AspectWeaver.
 */
public class AspectWeaver implements java.lang.reflect.InvocationHandler {
	
	/** The aspects. */
	private Aspect[] aspects;
	
	/** The target. */
	private Object target;
		
    /**
     * Weave.
     *
     * @param target the target
     * @param aspects the aspects
     * @return the object
     * @throws Exception the exception
     */
    public static synchronized Object weave(Object target, Aspect[] aspects) throws Exception {
    	// no target to weave the aspects into
    	if (target == null) {
    		return null;
    	}
    	
    	// no aspects to weave into the target
    	if (aspects == null || aspects.length == 0) {
    		return target;	
    	} 
    	    	
    	for (int i = 0; i < aspects.length; ++i) {
    		aspects[i].setPointcutType(target.getClass().getName());
   		}
    	
    	return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(), 
    			target.getClass().getInterfaces(), new AspectWeaver(target, aspects));
    }
    
    /**
     * Weave.
     *
     * @param target the target
     * @param aspectClassNames the aspect class names
     * @return the object
     * @throws Exception the exception
     */
    public static Object weave(Object target, String[] aspectClassNames) throws Exception {
    	// no target to weave aspects into
    	if (target == null) {
    		return null;
    	} 
    	
    	// no aspects to weave into the target
    	if (aspectClassNames == null || aspectClassNames.length == 0) {
    		return target;	
    	} 
    		
    	Aspect[] wovenAspects = new Aspect[aspectClassNames.length];
    	for (int i = 0; i < aspectClassNames.length; ++i) {
    		wovenAspects[i] = (Aspect)Class.forName(aspectClassNames[i]).newInstance();
   		}
    		
    	return weave(target, wovenAspects);	
    	
    }
    
    /**
     * Instantiates a new aspect weaver.
     *
     * @param target the target
     * @param aspects the aspects
     * @throws Exception the exception
     */
    private AspectWeaver(Object target, Aspect[] aspects) throws Exception {
    	this.target = target;
    	this.aspects = aspects;
    }
    
    /* (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object proxy, Method method, Object[] actualParameters) throws Throwable {
        if (aspects == null || aspects.length == 0) {
        	return method.invoke(target, actualParameters);
        }

        Object result = null;	
        Object[] beforeAspectReturnValue = new Object[aspects.length];
        boolean hasException = false; 
        
        try {
        	// apply the before advice
        	for (int i = 0; i < aspects.length; ++i) {
        		beforeAspectReturnValue[i] = aspects[i].before(target, method.getName(), actualParameters);
        	}
        	
        	result = method.invoke(target, actualParameters);
        		
        } catch (InvocationTargetException e) {
       		// apply the exception handler advice
        	for (int i = 0; i < aspects.length; ++i) {
        		aspects[i].exception(target, method.getName(), actualParameters, beforeAspectReturnValue[i], e.getCause());
    		}
        	hasException = true;
        	throw e.getCause();
        	
        } catch (Exception e) {
	    	throw new RuntimeException("Unexpected invocation exception: " + e.getMessage(), e);
	    	
        } finally {
        	// apply the after advice in the reverse order
    		for (int i = aspects.length - 1; i >= 0; --i) {
    			aspects[i].after(target, method.getName(), actualParameters, beforeAspectReturnValue[i], result, hasException);
    		}
        }
        
        return result;
    }
	
}
