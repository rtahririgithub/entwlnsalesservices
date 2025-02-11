package com.telus.csm.ewlnsc.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//Annotation for Profiling Aspect for a specific method
//Annotation for Profiling Aspect for all public methods of a specific class 
@Retention(RetentionPolicy.RUNTIME)
public @interface MonitorPerformance {
 
}