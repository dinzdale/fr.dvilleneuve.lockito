package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class CSS$GetComputedStyleForNodeResult
  implements JsonRpcResult
{
  @JsonProperty(required=true)
  public List<CSS.CSSComputedStyleProperty> computedStyle;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.GetComputedStyleForNodeResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */