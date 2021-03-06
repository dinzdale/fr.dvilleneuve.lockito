package com.j256.ormlite.field;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ForeignCollectionField
{
  public static final int MAX_EAGER_LEVEL = 1;
  
  String columnName() default "";
  
  boolean eager() default false;
  
  @Deprecated
  String foreignColumnName() default "";
  
  String foreignFieldName() default "";
  
  @Deprecated
  int maxEagerForeignCollectionLevel() default 1;
  
  int maxEagerLevel() default 1;
  
  boolean orderAscending() default true;
  
  String orderColumnName() default "";
}

/* Location:
 * Qualified Name:     com.j256.ormlite.field.ForeignCollectionField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */