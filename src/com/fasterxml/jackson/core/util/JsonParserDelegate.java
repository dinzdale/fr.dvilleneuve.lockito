package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate
  extends JsonParser
{
  protected JsonParser delegate;
  
  public JsonParserDelegate(JsonParser paramJsonParser)
  {
    delegate = paramJsonParser;
  }
  
  public boolean canReadObjectId()
  {
    return delegate.canReadObjectId();
  }
  
  public boolean canReadTypeId()
  {
    return delegate.canReadTypeId();
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    return delegate.canUseSchema(paramFormatSchema);
  }
  
  public void clearCurrentToken()
  {
    delegate.clearCurrentToken();
  }
  
  public void close()
    throws IOException
  {
    delegate.close();
  }
  
  public JsonParser disable(JsonParser.Feature paramFeature)
  {
    delegate.disable(paramFeature);
    return this;
  }
  
  public JsonParser enable(JsonParser.Feature paramFeature)
  {
    delegate.enable(paramFeature);
    return this;
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException, JsonParseException
  {
    return delegate.getBigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException, JsonParseException
  {
    return delegate.getBinaryValue(paramBase64Variant);
  }
  
  public boolean getBooleanValue()
    throws IOException, JsonParseException
  {
    return delegate.getBooleanValue();
  }
  
  public byte getByteValue()
    throws IOException, JsonParseException
  {
    return delegate.getByteValue();
  }
  
  public ObjectCodec getCodec()
  {
    return delegate.getCodec();
  }
  
  public JsonLocation getCurrentLocation()
  {
    return delegate.getCurrentLocation();
  }
  
  public String getCurrentName()
    throws IOException, JsonParseException
  {
    return delegate.getCurrentName();
  }
  
  public JsonToken getCurrentToken()
  {
    return delegate.getCurrentToken();
  }
  
  public int getCurrentTokenId()
  {
    return delegate.getCurrentTokenId();
  }
  
  public BigDecimal getDecimalValue()
    throws IOException, JsonParseException
  {
    return delegate.getDecimalValue();
  }
  
  public double getDoubleValue()
    throws IOException, JsonParseException
  {
    return delegate.getDoubleValue();
  }
  
  public Object getEmbeddedObject()
    throws IOException, JsonParseException
  {
    return delegate.getEmbeddedObject();
  }
  
  public int getFeatureMask()
  {
    return delegate.getFeatureMask();
  }
  
  public float getFloatValue()
    throws IOException, JsonParseException
  {
    return delegate.getFloatValue();
  }
  
  public Object getInputSource()
  {
    return delegate.getInputSource();
  }
  
  public int getIntValue()
    throws IOException, JsonParseException
  {
    return delegate.getIntValue();
  }
  
  public JsonToken getLastClearedToken()
  {
    return delegate.getLastClearedToken();
  }
  
  public long getLongValue()
    throws IOException, JsonParseException
  {
    return delegate.getLongValue();
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException, JsonParseException
  {
    return delegate.getNumberType();
  }
  
  public Number getNumberValue()
    throws IOException, JsonParseException
  {
    return delegate.getNumberValue();
  }
  
  public Object getObjectId()
    throws IOException, JsonGenerationException
  {
    return delegate.getObjectId();
  }
  
  public JsonStreamContext getParsingContext()
  {
    return delegate.getParsingContext();
  }
  
  public FormatSchema getSchema()
  {
    return delegate.getSchema();
  }
  
  public short getShortValue()
    throws IOException, JsonParseException
  {
    return delegate.getShortValue();
  }
  
  public String getText()
    throws IOException, JsonParseException
  {
    return delegate.getText();
  }
  
  public char[] getTextCharacters()
    throws IOException, JsonParseException
  {
    return delegate.getTextCharacters();
  }
  
  public int getTextLength()
    throws IOException, JsonParseException
  {
    return delegate.getTextLength();
  }
  
  public int getTextOffset()
    throws IOException, JsonParseException
  {
    return delegate.getTextOffset();
  }
  
  public JsonLocation getTokenLocation()
  {
    return delegate.getTokenLocation();
  }
  
  public Object getTypeId()
    throws IOException, JsonGenerationException
  {
    return delegate.getTypeId();
  }
  
  public boolean getValueAsBoolean()
    throws IOException, JsonParseException
  {
    return delegate.getValueAsBoolean();
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException, JsonParseException
  {
    return delegate.getValueAsBoolean(paramBoolean);
  }
  
  public double getValueAsDouble()
    throws IOException, JsonParseException
  {
    return delegate.getValueAsDouble();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException, JsonParseException
  {
    return delegate.getValueAsDouble(paramDouble);
  }
  
  public int getValueAsInt()
    throws IOException, JsonParseException
  {
    return delegate.getValueAsInt();
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException, JsonParseException
  {
    return delegate.getValueAsInt(paramInt);
  }
  
  public long getValueAsLong()
    throws IOException, JsonParseException
  {
    return delegate.getValueAsLong();
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException, JsonParseException
  {
    return delegate.getValueAsLong(paramLong);
  }
  
  public String getValueAsString()
    throws IOException, JsonParseException
  {
    return delegate.getValueAsString();
  }
  
  public String getValueAsString(String paramString)
    throws IOException, JsonParseException
  {
    return delegate.getValueAsString(paramString);
  }
  
  public boolean hasCurrentToken()
  {
    return delegate.hasCurrentToken();
  }
  
  public boolean hasTextCharacters()
  {
    return delegate.hasTextCharacters();
  }
  
  public boolean isClosed()
  {
    return delegate.isClosed();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return delegate.isEnabled(paramFeature);
  }
  
  public JsonToken nextToken()
    throws IOException, JsonParseException
  {
    return delegate.nextToken();
  }
  
  public JsonToken nextValue()
    throws IOException, JsonParseException
  {
    return delegate.nextValue();
  }
  
  public void overrideCurrentName(String paramString)
  {
    delegate.overrideCurrentName(paramString);
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException, JsonParseException
  {
    return delegate.readBinaryValue(paramBase64Variant, paramOutputStream);
  }
  
  public boolean requiresCustomCodec()
  {
    return delegate.requiresCustomCodec();
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    delegate.setCodec(paramObjectCodec);
  }
  
  public JsonParser setFeatureMask(int paramInt)
  {
    delegate.setFeatureMask(paramInt);
    return this;
  }
  
  public void setSchema(FormatSchema paramFormatSchema)
  {
    delegate.setSchema(paramFormatSchema);
  }
  
  public JsonParser skipChildren()
    throws IOException, JsonParseException
  {
    delegate.skipChildren();
    return this;
  }
  
  public Version version()
  {
    return delegate.version();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.util.JsonParserDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */