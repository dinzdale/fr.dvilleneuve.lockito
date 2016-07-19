package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.PARAMETER})
public @interface JsonDeserialize
{
  Class<?> as() default NoClass.class;
  
  Class<?> builder() default NoClass.class;
  
  Class<?> contentAs() default NoClass.class;
  
  Class<? extends Converter<?, ?>> contentConverter() default Converter.None.class;
  
  Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;
  
  Class<? extends Converter<?, ?>> converter() default Converter.None.class;
  
  Class<?> keyAs() default NoClass.class;
  
  Class<? extends KeyDeserializer> keyUsing() default KeyDeserializer.None.class;
  
  Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.annotation.JsonDeserialize
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */