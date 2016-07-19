package org.joda.convert;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR})
public @interface FromString {}

/* Location:
 * Qualified Name:     org.joda.convert.FromString
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */