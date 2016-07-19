package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class StdKeySerializer
  extends StdSerializer<Object>
{
  static final StdKeySerializer instace = new StdKeySerializer();
  
  public StdKeySerializer()
  {
    super(Object.class);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    return createSchemaNode("string");
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if ((paramObject instanceof Date))
    {
      paramSerializerProvider.defaultSerializeDateKey((Date)paramObject, paramJsonGenerator);
      return;
    }
    paramJsonGenerator.writeFieldName(paramObject.toString());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.StdKeySerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */