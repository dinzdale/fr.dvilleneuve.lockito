package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$ShorthandEntry
{
  @JsonProperty
  public Boolean imporant;
  @JsonProperty(required=true)
  public String name;
  @JsonProperty(required=true)
  public String value;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.ShorthandEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */