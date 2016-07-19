package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

public abstract class ArraySerializerBase<T>
  extends ContainerSerializer<T>
{
  protected final BeanProperty _property;
  
  protected ArraySerializerBase(ArraySerializerBase<?> paramArraySerializerBase)
  {
    super(_handledType, false);
    _property = _property;
  }
  
  protected ArraySerializerBase(ArraySerializerBase<?> paramArraySerializerBase, BeanProperty paramBeanProperty)
  {
    super(_handledType, false);
    _property = paramBeanProperty;
  }
  
  protected ArraySerializerBase(Class<T> paramClass)
  {
    super(paramClass);
    _property = null;
  }
  
  protected ArraySerializerBase(Class<T> paramClass, BeanProperty paramBeanProperty)
  {
    super(paramClass);
    _property = paramBeanProperty;
  }
  
  public final void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if ((paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && (hasSingleElement(paramT)))
    {
      serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray();
    serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  protected abstract void serializeContents(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException;
  
  public final void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForArray(paramT, paramJsonGenerator);
    serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForArray(paramT, paramJsonGenerator);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */