package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T>
  extends JsonDeserializer<T>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _valueClass;
  
  protected StdDeserializer(JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (paramJavaType = null;; paramJavaType = paramJavaType.getRawClass())
    {
      _valueClass = paramJavaType;
      return;
    }
  }
  
  protected StdDeserializer(Class<?> paramClass)
  {
    _valueClass = paramClass;
  }
  
  protected static final double parseDouble(String paramString)
    throws NumberFormatException
  {
    if ("2.2250738585072012e-308".equals(paramString)) {
      return Double.MIN_VALUE;
    }
    return Double.parseDouble(paramString);
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  protected final Boolean _parseBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      return Boolean.TRUE;
    }
    if (localJsonToken == JsonToken.VALUE_FALSE) {
      return Boolean.FALSE;
    }
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
    {
      if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
      {
        if (paramJsonParser.getIntValue() == 0) {
          return Boolean.FALSE;
        }
        return Boolean.TRUE;
      }
      return Boolean.valueOf(_parseBooleanFromNumber(paramJsonParser, paramDeserializationContext));
    }
    if (localJsonToken == JsonToken.VALUE_NULL) {
      return (Boolean)getNullValue();
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if ("true".equals(paramJsonParser)) {
        return Boolean.TRUE;
      }
      if ("false".equals(paramJsonParser)) {
        return Boolean.FALSE;
      }
      if (paramJsonParser.length() == 0) {
        return (Boolean)getEmptyValue();
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Boolean)getNullValue();
      }
      throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "only \"true\" or \"false\" recognized");
    }
    throw paramDeserializationContext.mappingException(_valueClass, localJsonToken);
  }
  
  protected final boolean _parseBooleanFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if (paramJsonParser.getNumberType() == JsonParser.NumberType.LONG)
    {
      if (paramJsonParser.getLongValue() == 0L) {}
      for (paramJsonParser = Boolean.FALSE;; paramJsonParser = Boolean.TRUE) {
        return paramJsonParser.booleanValue();
      }
    }
    paramJsonParser = paramJsonParser.getText();
    if (("0.0".equals(paramJsonParser)) || ("0".equals(paramJsonParser))) {
      return Boolean.FALSE.booleanValue();
    }
    return Boolean.TRUE.booleanValue();
  }
  
  protected final boolean _parseBooleanPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    boolean bool2 = true;
    boolean bool3 = false;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    boolean bool1;
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool3;
            } while (localJsonToken == JsonToken.VALUE_FALSE);
            bool1 = bool3;
          } while (localJsonToken == JsonToken.VALUE_NULL);
          if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
          {
            if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
            {
              if (paramJsonParser.getIntValue() != 0) {}
              for (bool1 = bool2;; bool1 = false) {
                return bool1;
              }
            }
            return _parseBooleanFromNumber(paramJsonParser, paramDeserializationContext);
          }
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          paramJsonParser = paramJsonParser.getText().trim();
          if ("true".equals(paramJsonParser)) {
            return true;
          }
          bool1 = bool3;
        } while ("false".equals(paramJsonParser));
        bool1 = bool3;
      } while (paramJsonParser.length() == 0);
      bool1 = bool3;
    } while (_hasTextualNull(paramJsonParser));
    throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "only \"true\" or \"false\" recognized");
    throw paramDeserializationContext.mappingException(_valueClass, localJsonToken);
  }
  
  protected Byte _parseByte(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Byte.valueOf(paramJsonParser.getByteValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (_hasTextualNull(paramJsonParser)) {
        return (Byte)getNullValue();
      }
      int i;
      try
      {
        if (paramJsonParser.length() == 0) {
          return (Byte)getEmptyValue();
        }
        i = NumberInput.parseInt(paramJsonParser);
        if ((i < -128) || (i > 255)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "overflow, value can not be represented as 8-bit value");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Byte value");
      }
      return Byte.valueOf((byte)i);
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Byte)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected Date _parseDate(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramJsonParser.getCurrentToken();
    if (localObject == JsonToken.VALUE_NUMBER_INT) {
      return new Date(paramJsonParser.getLongValue());
    }
    if (localObject == JsonToken.VALUE_NULL) {
      return (Date)getNullValue();
    }
    if (localObject == JsonToken.VALUE_STRING)
    {
      localObject = null;
      try
      {
        paramJsonParser = paramJsonParser.getText().trim();
        localObject = paramJsonParser;
        if (paramJsonParser.length() == 0)
        {
          localObject = paramJsonParser;
          return (Date)getEmptyValue();
        }
        localObject = paramJsonParser;
        if (_hasTextualNull(paramJsonParser))
        {
          localObject = paramJsonParser;
          return (Date)getNullValue();
        }
        localObject = paramJsonParser;
        paramJsonParser = paramDeserializationContext.parseDate(paramJsonParser);
        return paramJsonParser;
      }
      catch (IllegalArgumentException paramJsonParser)
      {
        throw paramDeserializationContext.weirdStringException((String)localObject, _valueClass, "not a valid representation (error: " + paramJsonParser.getMessage() + ")");
      }
    }
    throw paramDeserializationContext.mappingException(_valueClass, (JsonToken)localObject);
  }
  
  protected final Double _parseDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Double.valueOf(paramJsonParser.getDoubleValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Double)getEmptyValue();
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Double)getNullValue();
      }
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          double d = parseDouble(paramJsonParser);
          return Double.valueOf(d);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Double value");
        }
        if (("Infinity".equals(paramJsonParser)) || ("INF".equals(paramJsonParser)))
        {
          return Double.valueOf(Double.POSITIVE_INFINITY);
          if ("NaN".equals(paramJsonParser))
          {
            return Double.valueOf(NaN.0D);
            if (("-Infinity".equals(paramJsonParser)) || ("-INF".equals(paramJsonParser))) {
              return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
          }
        }
      }
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Double)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final double _parseDoublePrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    double d2 = 0.0D;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    double d1;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      d1 = paramJsonParser.getDoubleValue();
    }
    do
    {
      do
      {
        do
        {
          return d1;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          paramJsonParser = paramJsonParser.getText().trim();
          d1 = d2;
        } while (paramJsonParser.length() == 0);
        d1 = d2;
      } while (_hasTextualNull(paramJsonParser));
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          d1 = parseDouble(paramJsonParser);
          return d1;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid double value");
        }
        if (("Infinity".equals(paramJsonParser)) || ("INF".equals(paramJsonParser)))
        {
          return Double.POSITIVE_INFINITY;
          if ("NaN".equals(paramJsonParser))
          {
            return NaN.0D;
            if (("-Infinity".equals(paramJsonParser)) || ("-INF".equals(paramJsonParser))) {
              return Double.NEGATIVE_INFINITY;
            }
          }
        }
      }
      d1 = d2;
    } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final Float _parseFloat(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Float.valueOf(paramJsonParser.getFloatValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Float)getEmptyValue();
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Float)getNullValue();
      }
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          float f = Float.parseFloat(paramJsonParser);
          return Float.valueOf(f);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Float value");
        }
        if (("Infinity".equals(paramJsonParser)) || ("INF".equals(paramJsonParser)))
        {
          return Float.valueOf(Float.POSITIVE_INFINITY);
          if ("NaN".equals(paramJsonParser))
          {
            return Float.valueOf(NaN.0F);
            if (("-Infinity".equals(paramJsonParser)) || ("-INF".equals(paramJsonParser))) {
              return Float.valueOf(Float.NEGATIVE_INFINITY);
            }
          }
        }
      }
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Float)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final float _parseFloatPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    float f2 = 0.0F;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    float f1;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      f1 = paramJsonParser.getFloatValue();
    }
    do
    {
      do
      {
        do
        {
          return f1;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          paramJsonParser = paramJsonParser.getText().trim();
          f1 = f2;
        } while (paramJsonParser.length() == 0);
        f1 = f2;
      } while (_hasTextualNull(paramJsonParser));
      switch (paramJsonParser.charAt(0))
      {
      }
      for (;;)
      {
        try
        {
          f1 = Float.parseFloat(paramJsonParser);
          return f1;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid float value");
        }
        if (("Infinity".equals(paramJsonParser)) || ("INF".equals(paramJsonParser)))
        {
          return Float.POSITIVE_INFINITY;
          if ("NaN".equals(paramJsonParser))
          {
            return NaN.0F;
            if (("-Infinity".equals(paramJsonParser)) || ("-INF".equals(paramJsonParser))) {
              return Float.NEGATIVE_INFINITY;
            }
          }
        }
      }
      f1 = f2;
    } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final int _parseIntPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    int j = 0;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    int i;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      i = paramJsonParser.getIntValue();
    }
    label174:
    do
    {
      int k;
      do
      {
        do
        {
          return i;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          paramJsonParser = paramJsonParser.getText().trim();
          i = j;
        } while (_hasTextualNull(paramJsonParser));
        long l;
        try
        {
          k = paramJsonParser.length();
          if (k <= 9) {
            break label174;
          }
          l = Long.parseLong(paramJsonParser);
          if ((l < -2147483648L) || (l > 2147483647L)) {
            throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "Overflow: numeric value (" + paramJsonParser + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid int value");
        }
        return (int)l;
        i = j;
      } while (k == 0);
      i = NumberInput.parseInt(paramJsonParser);
      return i;
      i = j;
    } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final Integer _parseInteger(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Integer.valueOf(paramJsonParser.getIntValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      long l;
      try
      {
        i = paramJsonParser.length();
        if (_hasTextualNull(paramJsonParser)) {
          return (Integer)getNullValue();
        }
        if (i <= 9) {
          break label180;
        }
        l = Long.parseLong(paramJsonParser);
        if ((l < -2147483648L) || (l > 2147483647L)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "Overflow: numeric value (" + paramJsonParser + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Integer value");
      }
      int i = (int)l;
      return Integer.valueOf(i);
      label180:
      if (i == 0) {
        return (Integer)getEmptyValue();
      }
      i = NumberInput.parseInt(paramJsonParser);
      return Integer.valueOf(i);
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Integer)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final Long _parseLong(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Long.valueOf(paramJsonParser.getLongValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      if (paramJsonParser.length() == 0) {
        return (Long)getEmptyValue();
      }
      if (_hasTextualNull(paramJsonParser)) {
        return (Long)getNullValue();
      }
      try
      {
        long l = NumberInput.parseLong(paramJsonParser);
        return Long.valueOf(l);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Long value");
      }
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Long)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final long _parseLongPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    long l2 = 0L;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    long l1;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      l1 = paramJsonParser.getLongValue();
    }
    do
    {
      do
      {
        do
        {
          return l1;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          paramJsonParser = paramJsonParser.getText().trim();
          l1 = l2;
        } while (paramJsonParser.length() == 0);
        l1 = l2;
      } while (_hasTextualNull(paramJsonParser));
      try
      {
        l1 = NumberInput.parseLong(paramJsonParser);
        return l1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid long value");
      }
      l1 = l2;
    } while (localIllegalArgumentException == JsonToken.VALUE_NULL);
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected Short _parseShort(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      return Short.valueOf(paramJsonParser.getShortValue());
    }
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      paramJsonParser = paramJsonParser.getText().trim();
      int i;
      try
      {
        if (paramJsonParser.length() == 0) {
          return (Short)getEmptyValue();
        }
        if (_hasTextualNull(paramJsonParser)) {
          return (Short)getNullValue();
        }
        i = NumberInput.parseInt(paramJsonParser);
        if ((i < 32768) || (i > 32767)) {
          throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "overflow, value can not be represented as 16-bit value");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(paramJsonParser, _valueClass, "not a valid Short value");
      }
      return Short.valueOf((short)i);
    }
    if (localIllegalArgumentException == JsonToken.VALUE_NULL) {
      return (Short)getNullValue();
    }
    throw paramDeserializationContext.mappingException(_valueClass, localIllegalArgumentException);
  }
  
  protected final short _parseShortPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    int i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    if ((i < 32768) || (i > 32767)) {
      throw paramDeserializationContext.weirdStringException(String.valueOf(i), _valueClass, "overflow, value can not be represented as 16-bit value");
    }
    return (short)i;
  }
  
  protected final String _parseString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    String str = paramJsonParser.getValueAsString();
    if (str != null) {
      return str;
    }
    throw paramDeserializationContext.mappingException(String.class, paramJsonParser.getCurrentToken());
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Object localObject2 = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject1 = paramJsonDeserializer;
    if (localObject2 != null)
    {
      localObject1 = paramJsonDeserializer;
      if (paramBeanProperty != null)
      {
        localObject2 = ((AnnotationIntrospector)localObject2).findDeserializationContentConverter(paramBeanProperty.getMember());
        localObject1 = paramJsonDeserializer;
        if (localObject2 != null)
        {
          localObject2 = paramDeserializationContext.converterInstance(paramBeanProperty.getMember(), localObject2);
          JavaType localJavaType = ((Converter)localObject2).getInputType(paramDeserializationContext.getTypeFactory());
          localObject1 = paramJsonDeserializer;
          if (paramJsonDeserializer == null) {
            localObject1 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);
          }
          localObject1 = new StdDelegatingDeserializer((Converter)localObject2, localJavaType, (JsonDeserializer)localObject1);
        }
      }
    }
    return (JsonDeserializer<?>)localObject1;
  }
  
  protected JsonDeserializer<Object> findDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return paramDeserializationContext.findContextualValueDeserializer(paramJavaType, paramBeanProperty);
  }
  
  @Deprecated
  public final Class<?> getValueClass()
  {
    return _valueClass;
  }
  
  public JavaType getValueType()
  {
    return null;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = handledType();
    }
    if (paramDeserializationContext.handleUnknownProperty(paramJsonParser, this, localObject, paramString)) {
      return;
    }
    paramDeserializationContext.reportUnknownProperty(localObject, paramString, this);
    paramJsonParser.skipChildren();
  }
  
  public Class<?> handledType()
  {
    return _valueClass;
  }
  
  protected boolean isDefaultDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramJsonDeserializer);
  }
  
  protected boolean isDefaultKeyDeserializer(KeyDeserializer paramKeyDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramKeyDeserializer);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */