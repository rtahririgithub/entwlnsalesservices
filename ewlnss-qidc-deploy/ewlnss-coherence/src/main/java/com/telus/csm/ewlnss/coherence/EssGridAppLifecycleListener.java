package com.telus.csm.ewlnss.coherence;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import com.tangosol.application.Context;
import com.tangosol.application.LifecycleListener;

public class EssGridAppLifecycleListener implements  LifecycleListener {
	
	public static Logger logger =  Logger.getLogger( EssGridAppLifecycleListener.class.getSimpleName()); 
 


	@Override
	public void postStart(Context context) {

	}

	@Override
	public void postStop(Context context) {
		
		String appName = "<" + context.getExtendedContext().getApplicationName() + ">";
		logger.info( appName +  "  postStop... " );
		
		logBuildInfos();
	}

	@Override
	public void preStart(Context context) {
		
		String appName = "<" + context.getExtendedContext().getApplicationName() + ">";
		logger.info( appName +  "  preStart... " );

		logBuildInfos();
	}

	@Override
	public void preStop(Context context) {
	}
	
	
	public void logBuildInfos() {
		
		//GAR itself's version
		logBuildInfo( "essGar.version",  "META-INF/", "version", "buildDate" );
		
		//cache store version
		logBuildInfo( "esdgCacheStore.version",  "META-INF/", "esdgVersion", "esdgBuildDate" );
		
		//WLN grid loader version
		logBuildInfo( "wlnGridLoader.version",  "META-INF/", "version", "buildDate" );
		
		//Wireless grid loader version
		logBuildInfo( "wlsGridLoader.version",  "META-INF/", "version", "buildDate" );
		
	}
	
	public void logBuildInfo( String verPropFileName, String verPropFullPath, String versionKey, String buildDateKey) {
		
	    String fullPath = verPropFullPath + verPropFileName;

	    StringBuilder sb = new StringBuilder( "\"").append( verPropFileName ).append("\"  ");
	    
		try {
			
			URL url = getClass().getClassLoader().getResource( fullPath );

			if ( url!=null ) {

				InputStream stream = url.openStream();
			    
			    if( stream == null ) {
			    	
			    	sb.append("Failed to load version file from classpath, full path: " ).append(fullPath);
			    	
			    } else {
	
			    	
				    Properties properties = new Properties();
				    properties.load(stream);			    
				    stream.close();
					
			    	sb.append( "build# : ");
			    	
				    String version = properties.getProperty(versionKey);
				    if ( version!=null) {
				    	sb.append ( version );
				    } else {
				    	sb.append( "property ").append( versionKey ).append(" not found." );
				    }
				    
				    String date = properties.getProperty(versionKey);
				    if ( date!=null ) {
				    	sb.append( ",  on: ").append( properties.getProperty( buildDateKey ) );
				    	
				    } else {
				    	sb.append( ",  property ").append( buildDateKey ).append(" not found." );
				    }
				    
				    sb.append (";  load from: ").append( url.toExternalForm() );
	
			    }
			} else {
		    	sb.append("Failed to load version file from classpath, full path: " ).append(fullPath);
				
			}
		    
		} catch(Exception e){
			
			e.printStackTrace();
	    	sb.append("Failed to load version file from classpath, full path: " ).append(fullPath);
			
		} finally {
			
			logger.info( sb.toString() );
		}
	}

	
	//Test drive
	public static void main( String[] args ) {
		
		new EssGridAppLifecycleListener().logBuildInfos();
		
	}

}
