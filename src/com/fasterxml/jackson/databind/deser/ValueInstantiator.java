package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;

public abstract class ValueInstantiator
{
  public boolean canCreateFromBoolean()
  {
    return false;
  }
  
  public boolean canCreateFromDouble()
  {
    return false;
  }
  
  public boolean canCreateFromInt()
  {
    return false;
  }
  
  public boolean canCreateFromLong()
  {
    return false;
  }
  
  public boolean canCreateFromObjectWith()
  {
    return false;
  }
  
  public boolean canCreateFromString()
  {
    return false;
  }
  
  public boolean canCreateUsingDefault()
  {
    return getDefaultCreator() != null;
  }
  
  public boolean canCreateUsingDelegate()
  {
    return false;
  }
  
  public boolean canInstantiate()
  {
    return (canCreateUsingDefault()) || (canCreateUsingDelegate()) || (canCreateFromObjectWith()) || (canCreateFromString()) || (canCreateFromInt()) || (canCreateFromLong()) || (canCreateFromDouble()) || (canCreateFromBoolean());
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Boolean value");
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Floating-point number (double)");
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Integer number (int)");
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from Integer number (long)");
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " with arguments");
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from String value");
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + "; no default creator found");
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " using delegate");
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return null;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return null;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return null;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return null;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return null;
  }
  
  public abstract String getValueTypeDesc();
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.ValueInstantiator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */