package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.io.Serializable;
import java.util.Collection;

public class ObjectMapper$DefaultTypeResolverBuilder
  extends StdTypeResolverBuilder
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final ObjectMapper.DefaultTyping _appliesFor;
  
  public ObjectMapper$DefaultTypeResolverBuilder(ObjectMapper.DefaultTyping paramDefaultTyping)
  {
    _appliesFor = paramDefaultTyping;
  }
  
  public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    if (useForType(paramJavaType)) {
      return super.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramCollection);
    }
    return null;
  }
  
  public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    if (useForType(paramJavaType)) {
      return super.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramCollection);
    }
    return null;
  }
  
  public boolean useForType(JavaType paramJavaType)
  {
    boolean bool = false;
    JavaType localJavaType1 = paramJavaType;
    JavaType localJavaType2 = paramJavaType;
    JavaType localJavaType3 = paramJavaType;
    switch (ObjectMapper.2.$SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[_appliesFor.ordinal()])
    {
    default: 
      if (paramJavaType.getRawClass() != Object.class) {
        break;
      }
    case 1: 
    case 2: 
    case 3: 
      do
      {
        return true;
        for (;;)
        {
          localJavaType2 = localJavaType1;
          if (!localJavaType1.isArrayType()) {
            break;
          }
          localJavaType1 = localJavaType1.getContentType();
        }
        if ((localJavaType2.getRawClass() == Object.class) || (!localJavaType2.isConcrete())) {
          bool = true;
        }
        return bool;
        while (localJavaType3.isArrayType()) {
          localJavaType3 = localJavaType3.getContentType();
        }
      } while (!localJavaType3.isFinal());
      return false;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */