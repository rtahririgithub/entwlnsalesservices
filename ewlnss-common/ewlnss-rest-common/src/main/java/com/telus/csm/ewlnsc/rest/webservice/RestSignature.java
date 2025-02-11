package com.telus.csm.ewlnsc.rest.webservice;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RestSignature {
  Class<?> request() default Object.class;
  Class<?> response() default Object.class;
  //error 400
  Class<?> errorResponse() default Object.class;
  //error 500
  Class<?> systemErrorResponse() default Object.class;
}
