package com.telus.csm.ewlnsc.grid.loader;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class WlnGridLoaderVersion {
	
	public static String getVersion() {
		
		StringBuilder sb  = new StringBuilder ("[" + WlnGridLoaderVersion.class.getSimpleName() +"]");
		
	    String verionFilePath = "META-INF/wlnGridLoader.version";
		
	    try{
			
			URL url = WlnGridLoaderVersion.class.getClassLoader().getResource( verionFilePath );

			if ( url!=null ) {
				
		    	InputStream stream = url.openStream();
			    
				if( stream == null ) {
					
					sb.append( " build info undetermined. Failed to load version file from classpath, full path: ")
					.append( verionFilePath )
					.append(",  classLoader: ").append( WlnGridLoaderVersion.class.getClassLoader() );
					
			    } else {
			    
				    Properties properties = new Properties();
				    properties.load(stream);			    
				    stream.close();
				    
				    sb.append("  build label[").append( properties.getProperty( "version") ).append("] " );
				    sb.append( " date [" ).append( properties.getProperty( "buildDate") ).append( "]" );
				    
				    sb.append( " load from: ").append( url.toExternalForm() );
			    }
			} else {
				
				sb.append( " build info undetermined. Failed to load version file from classpath, full path: ")
				.append( verionFilePath )
				.append(",  classLoader: ").append( WlnGridLoaderVersion.class.getClassLoader() );
			}
		    
		} catch(Exception e){
			sb.append( " build info undetermined. Failed to load version file from classpath, full path: ")
			.append( verionFilePath )
			.append(",  classLoader: ").append( WlnGridLoaderVersion.class.getClassLoader() )
			.append( ",  encountered exception: ") .append( e.getMessage() )
			;
			
			e.printStackTrace(); 		
		} 
		return sb.toString() ;
	} 

	//Test drive
	public static void main( String[] args ) {
		
		System.out.println( WlnGridLoaderVersion.getVersion() );
		
	}

}
