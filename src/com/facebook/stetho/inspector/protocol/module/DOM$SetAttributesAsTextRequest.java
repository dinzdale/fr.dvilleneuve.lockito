package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$SetAttributesAsTextRequest
{
  @JsonProperty(required=true)
  public int nodeId;
  @JsonProperty(required=true)
  public String text;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.SetAttributesAsTextRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */