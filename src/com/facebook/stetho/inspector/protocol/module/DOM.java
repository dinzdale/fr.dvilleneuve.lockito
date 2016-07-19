package com.facebook.stetho.inspector.protocol.module;

import android.graphics.Color;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.Document.AttributeListAccumulator;
import com.facebook.stetho.inspector.elements.Document.UpdateListener;
import com.facebook.stetho.inspector.elements.DocumentView;
import com.facebook.stetho.inspector.elements.ElementInfo;
import com.facebook.stetho.inspector.elements.NodeDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeersRegisteredListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class DOM
  implements ChromeDevtoolsDomain
{
  private ChildNodeInsertedEvent mCachedChildNodeInsertedEvent;
  private ChildNodeRemovedEvent mCachedChildNodeRemovedEvent;
  private final Document mDocument;
  private final DocumentUpdateListener mListener;
  private final ObjectMapper mObjectMapper = new ObjectMapper();
  private final ChromePeerManager mPeerManager;
  private final AtomicInteger mResultCounter;
  private final Map<String, List<Integer>> mSearchResults;
  
  public DOM(Document paramDocument)
  {
    mDocument = ((Document)Util.throwIfNull(paramDocument));
    mSearchResults = Collections.synchronizedMap(new HashMap());
    mResultCounter = new AtomicInteger(0);
    mPeerManager = new ChromePeerManager();
    mPeerManager.setListener(new PeerManagerListener(null));
    mListener = new DocumentUpdateListener(null);
  }
  
  private ChildNodeInsertedEvent acquireChildNodeInsertedEvent()
  {
    ChildNodeInsertedEvent localChildNodeInsertedEvent2 = mCachedChildNodeInsertedEvent;
    ChildNodeInsertedEvent localChildNodeInsertedEvent1 = localChildNodeInsertedEvent2;
    if (localChildNodeInsertedEvent2 == null) {
      localChildNodeInsertedEvent1 = new ChildNodeInsertedEvent(null);
    }
    mCachedChildNodeInsertedEvent = null;
    return localChildNodeInsertedEvent1;
  }
  
  private ChildNodeRemovedEvent acquireChildNodeRemovedEvent()
  {
    ChildNodeRemovedEvent localChildNodeRemovedEvent2 = mCachedChildNodeRemovedEvent;
    ChildNodeRemovedEvent localChildNodeRemovedEvent1 = localChildNodeRemovedEvent2;
    if (localChildNodeRemovedEvent2 == null) {
      localChildNodeRemovedEvent1 = new ChildNodeRemovedEvent(null);
    }
    mCachedChildNodeRemovedEvent = null;
    return localChildNodeRemovedEvent1;
  }
  
  private Node createNodeForElement(Object paramObject, DocumentView paramDocumentView)
  {
    Object localObject = mDocument.getNodeDescriptor(paramObject);
    Node localNode = new Node(null);
    nodeId = mDocument.getNodeIdForElement(paramObject).intValue();
    nodeType = ((NodeDescriptor)localObject).getNodeType(paramObject);
    nodeName = ((NodeDescriptor)localObject).getNodeName(paramObject);
    localName = ((NodeDescriptor)localObject).getLocalName(paramObject);
    nodeValue = ((NodeDescriptor)localObject).getNodeValue(paramObject);
    Document.AttributeListAccumulator localAttributeListAccumulator = new Document.AttributeListAccumulator();
    ((NodeDescriptor)localObject).getAttributes(paramObject, localAttributeListAccumulator);
    attributes = localAttributeListAccumulator;
    localObject = paramDocumentView.getElementInfo(paramObject);
    if (children.size() == 0) {}
    for (paramObject = Collections.emptyList();; paramObject = new ArrayList(children.size()))
    {
      int i = 0;
      int j = children.size();
      while (i < j)
      {
        ((List)paramObject).add(createNodeForElement(children.get(i), paramDocumentView));
        i += 1;
      }
    }
    children = ((List)paramObject);
    childNodeCount = Integer.valueOf(((List)paramObject).size());
    return localNode;
  }
  
  private void releaseChildNodeInsertedEvent(ChildNodeInsertedEvent paramChildNodeInsertedEvent)
  {
    parentNodeId = -1;
    previousNodeId = -1;
    node = null;
    if (mCachedChildNodeInsertedEvent == null) {
      mCachedChildNodeInsertedEvent = paramChildNodeInsertedEvent;
    }
  }
  
  private void releaseChildNodeRemovedEvent(ChildNodeRemovedEvent paramChildNodeRemovedEvent)
  {
    parentNodeId = -1;
    nodeId = -1;
    if (mCachedChildNodeRemovedEvent == null) {
      mCachedChildNodeRemovedEvent = paramChildNodeRemovedEvent;
    }
  }
  
  @ChromeDevtoolsMethod
  public void disable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    mPeerManager.removePeer(paramJsonRpcPeer);
  }
  
  @ChromeDevtoolsMethod
  public void discardSearchResults(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (DiscardSearchResultsRequest)mObjectMapper.convertValue(paramJSONObject, DiscardSearchResultsRequest.class);
    if (searchId != null) {
      mSearchResults.remove(searchId);
    }
  }
  
  @ChromeDevtoolsMethod
  public void enable(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    mPeerManager.addPeer(paramJsonRpcPeer);
  }
  
  @ChromeDevtoolsMethod
  public JsonRpcResult getDocument(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = new GetDocumentResponse(null);
    root = ((Node)mDocument.postAndWait(new UncheckedCallable()
    {
      public DOM.Node call()
      {
        Object localObject = mDocument.getRootElement();
        return DOM.this.createNodeForElement(localObject, mDocument.getDocumentView());
      }
    }));
    return paramJsonRpcPeer;
  }
  
  @ChromeDevtoolsMethod
  public GetSearchResultsResponse getSearchResults(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (GetSearchResultsRequest)mObjectMapper.convertValue(paramJSONObject, GetSearchResultsRequest.class);
    if (searchId == null)
    {
      LogUtil.w("searchId may not be null");
      return null;
    }
    paramJSONObject = (List)mSearchResults.get(searchId);
    if (paramJSONObject == null)
    {
      LogUtil.w("\"" + searchId + "\" is not a valid reference to a search result");
      return null;
    }
    paramJsonRpcPeer = paramJSONObject.subList(fromIndex, toIndex);
    paramJSONObject = new GetSearchResultsResponse(null);
    nodeIds = paramJsonRpcPeer;
    return paramJSONObject;
  }
  
  @ChromeDevtoolsMethod
  public void hideHighlight(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    mDocument.postAndWait(new Runnable()
    {
      public void run()
      {
        mDocument.hideHighlight();
      }
    });
  }
  
  @ChromeDevtoolsMethod
  public void highlightNode(final JsonRpcPeer paramJsonRpcPeer, final JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (HighlightNodeRequest)mObjectMapper.convertValue(paramJSONObject, HighlightNodeRequest.class);
    if (nodeId == null)
    {
      LogUtil.w("DOM.highlightNode was not given a nodeId; JS objectId is not supported");
      return;
    }
    paramJSONObject = highlightConfig.contentColor;
    if (paramJSONObject == null)
    {
      LogUtil.w("DOM.highlightNode was not given a color to highlight with");
      return;
    }
    mDocument.postAndWait(new Runnable()
    {
      public void run()
      {
        Object localObject = mDocument.getElementForNodeId(paramJsonRpcPeernodeId.intValue());
        if (localObject != null) {
          mDocument.highlightElement(localObject, paramJSONObject.getColor());
        }
      }
    });
  }
  
  @ChromeDevtoolsMethod
  public PerformSearchResponse performSearch(final JsonRpcPeer paramJsonRpcPeer, final JSONObject paramJSONObject)
  {
    paramJSONObject = (PerformSearchRequest)mObjectMapper.convertValue(paramJSONObject, PerformSearchRequest.class);
    paramJsonRpcPeer = new ArrayListAccumulator();
    mDocument.postAndWait(new Runnable()
    {
      public void run()
      {
        mDocument.findMatchingElements(paramJSONObjectquery, paramJsonRpcPeer);
      }
    });
    paramJSONObject = String.valueOf(mResultCounter.getAndIncrement());
    mSearchResults.put(paramJSONObject, paramJsonRpcPeer);
    PerformSearchResponse localPerformSearchResponse = new PerformSearchResponse(null);
    searchId = paramJSONObject;
    resultCount = paramJsonRpcPeer.size();
    return localPerformSearchResponse;
  }
  
  @ChromeDevtoolsMethod
  public ResolveNodeResponse resolveNode(JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
    throws JsonRpcException
  {
    final ResolveNodeRequest localResolveNodeRequest = (ResolveNodeRequest)mObjectMapper.convertValue(paramJSONObject, ResolveNodeRequest.class);
    paramJSONObject = mDocument.postAndWait(new UncheckedCallable()
    {
      public Object call()
      {
        return mDocument.getElementForNodeId(localResolveNodeRequestnodeId);
      }
    });
    if (paramJSONObject == null) {
      throw new JsonRpcException(new JsonRpcError(JsonRpcError.ErrorCode.INVALID_PARAMS, "No known nodeId=" + nodeId, null));
    }
    int i = Runtime.mapObject(paramJsonRpcPeer, paramJSONObject);
    paramJsonRpcPeer = new Runtime.RemoteObject();
    type = Runtime.ObjectType.OBJECT;
    subtype = Runtime.ObjectSubType.NODE;
    className = paramJSONObject.getClass().getName();
    value = null;
    description = null;
    objectId = String.valueOf(i);
    paramJSONObject = new ResolveNodeResponse(null);
    object = paramJsonRpcPeer;
    return paramJSONObject;
  }
  
  @ChromeDevtoolsMethod
  public void setAttributesAsText(final JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (SetAttributesAsTextRequest)mObjectMapper.convertValue(paramJSONObject, SetAttributesAsTextRequest.class);
    mDocument.postAndWait(new Runnable()
    {
      public void run()
      {
        Object localObject = mDocument.getElementForNodeId(paramJsonRpcPeernodeId);
        if (localObject != null) {
          mDocument.setAttributesAsText(localObject, paramJsonRpcPeertext);
        }
      }
    });
  }
  
  @ChromeDevtoolsMethod
  public void setInspectModeEnabled(final JsonRpcPeer paramJsonRpcPeer, JSONObject paramJSONObject)
  {
    paramJsonRpcPeer = (SetInspectModeEnabledRequest)mObjectMapper.convertValue(paramJSONObject, SetInspectModeEnabledRequest.class);
    mDocument.postAndWait(new Runnable()
    {
      public void run()
      {
        mDocument.setInspectModeEnabled(paramJsonRpcPeerenabled);
      }
    });
  }
  
  private static class AttributeModifiedEvent
  {
    @JsonProperty(required=true)
    public String name;
    @JsonProperty(required=true)
    public int nodeId;
    @JsonProperty(required=true)
    public String value;
  }
  
  private static class AttributeRemovedEvent
  {
    @JsonProperty(required=true)
    public String name;
    @JsonProperty(required=true)
    public int nodeId;
  }
  
  private static class ChildNodeInsertedEvent
  {
    @JsonProperty(required=true)
    public DOM.Node node;
    @JsonProperty(required=true)
    public int parentNodeId;
    @JsonProperty(required=true)
    public int previousNodeId;
  }
  
  private static class ChildNodeRemovedEvent
  {
    @JsonProperty(required=true)
    public int nodeId;
    @JsonProperty(required=true)
    public int parentNodeId;
  }
  
  private static class DiscardSearchResultsRequest
  {
    @JsonProperty(required=true)
    public String searchId;
  }
  
  private final class DocumentUpdateListener
    implements Document.UpdateListener
  {
    private DocumentUpdateListener() {}
    
    public void onAttributeModified(Object paramObject, String paramString1, String paramString2)
    {
      DOM.AttributeModifiedEvent localAttributeModifiedEvent = new DOM.AttributeModifiedEvent(null);
      nodeId = mDocument.getNodeIdForElement(paramObject).intValue();
      name = paramString1;
      value = paramString2;
      mPeerManager.sendNotificationToPeers("DOM.onAttributeModified", localAttributeModifiedEvent);
    }
    
    public void onAttributeRemoved(Object paramObject, String paramString)
    {
      DOM.AttributeRemovedEvent localAttributeRemovedEvent = new DOM.AttributeRemovedEvent(null);
      nodeId = mDocument.getNodeIdForElement(paramObject).intValue();
      name = paramString;
      mPeerManager.sendNotificationToPeers("DOM.attributeRemoved", localAttributeRemovedEvent);
    }
    
    public void onChildNodeInserted(DocumentView paramDocumentView, Object paramObject, int paramInt1, int paramInt2, Accumulator<Object> paramAccumulator)
    {
      DOM.ChildNodeInsertedEvent localChildNodeInsertedEvent = DOM.this.acquireChildNodeInsertedEvent();
      parentNodeId = paramInt1;
      previousNodeId = paramInt2;
      node = DOM.this.createNodeForElement(paramObject, paramDocumentView);
      paramAccumulator.store(paramObject);
      mPeerManager.sendNotificationToPeers("DOM.childNodeInserted", localChildNodeInsertedEvent);
      DOM.this.releaseChildNodeInsertedEvent(localChildNodeInsertedEvent);
    }
    
    public void onChildNodeRemoved(int paramInt1, int paramInt2)
    {
      DOM.ChildNodeRemovedEvent localChildNodeRemovedEvent = DOM.this.acquireChildNodeRemovedEvent();
      parentNodeId = paramInt1;
      nodeId = paramInt2;
      mPeerManager.sendNotificationToPeers("DOM.childNodeRemoved", localChildNodeRemovedEvent);
      DOM.this.releaseChildNodeRemovedEvent(localChildNodeRemovedEvent);
    }
    
    public void onInspectRequested(Object paramObject)
    {
      Integer localInteger = mDocument.getNodeIdForElement(paramObject);
      if (localInteger == null)
      {
        LogUtil.d("DocumentProvider.Listener.onInspectRequested() called for a non-mapped node: element=%s", new Object[] { paramObject });
        return;
      }
      paramObject = new DOM.InspectNodeRequestedEvent(null);
      nodeId = localInteger.intValue();
      mPeerManager.sendNotificationToPeers("DOM.inspectNodeRequested", paramObject);
    }
  }
  
  private static class GetDocumentResponse
    implements JsonRpcResult
  {
    @JsonProperty(required=true)
    public DOM.Node root;
  }
  
  private static class GetSearchResultsRequest
  {
    @JsonProperty(required=true)
    public int fromIndex;
    @JsonProperty(required=true)
    public String searchId;
    @JsonProperty(required=true)
    public int toIndex;
  }
  
  private static class GetSearchResultsResponse
    implements JsonRpcResult
  {
    @JsonProperty(required=true)
    public List<Integer> nodeIds;
  }
  
  private static class HighlightConfig
  {
    @JsonProperty
    public DOM.RGBAColor contentColor;
  }
  
  private static class HighlightNodeRequest
  {
    @JsonProperty(required=true)
    public DOM.HighlightConfig highlightConfig;
    @JsonProperty
    public Integer nodeId;
    @JsonProperty
    public String objectId;
  }
  
  private static class InspectNodeRequestedEvent
  {
    @JsonProperty
    public int nodeId;
  }
  
  private static class Node
    implements JsonRpcResult
  {
    @JsonProperty
    public List<String> attributes;
    @JsonProperty
    public Integer childNodeCount;
    @JsonProperty
    public List<Node> children;
    @JsonProperty(required=true)
    public String localName;
    @JsonProperty(required=true)
    public int nodeId;
    @JsonProperty(required=true)
    public String nodeName;
    @JsonProperty(required=true)
    public NodeType nodeType;
    @JsonProperty(required=true)
    public String nodeValue;
  }
  
  private final class PeerManagerListener
    extends PeersRegisteredListener
  {
    private PeerManagerListener() {}
    
    protected void onFirstPeerRegistered()
    {
      try
      {
        mDocument.addRef();
        mDocument.addUpdateListener(mListener);
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    protected void onLastPeerUnregistered()
    {
      try
      {
        mSearchResults.clear();
        mDocument.removeUpdateListener(mListener);
        mDocument.release();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
  
  private static class PerformSearchRequest
  {
    @JsonProperty
    public Boolean includeUserAgentShadowDOM;
    @JsonProperty(required=true)
    public String query;
  }
  
  private static class PerformSearchResponse
    implements JsonRpcResult
  {
    @JsonProperty(required=true)
    public int resultCount;
    @JsonProperty(required=true)
    public String searchId;
  }
  
  private static class RGBAColor
  {
    @JsonProperty
    public Double a;
    @JsonProperty(required=true)
    public int b;
    @JsonProperty(required=true)
    public int g;
    @JsonProperty(required=true)
    public int r;
    
    public int getColor()
    {
      int i;
      if (a == null)
      {
        i = -1;
        return Color.argb(i, r, g, b);
      }
      long l = Math.round(a.doubleValue() * 255.0D);
      if (l < 0L) {
        i = 0;
      }
      for (;;)
      {
        break;
        if (l >= 255L) {
          i = -1;
        } else {
          i = (byte)(int)l;
        }
      }
    }
  }
  
  private static class ResolveNodeRequest
  {
    @JsonProperty(required=true)
    public int nodeId;
    @JsonProperty
    public String objectGroup;
  }
  
  private static class ResolveNodeResponse
    implements JsonRpcResult
  {
    @JsonProperty(required=true)
    public Runtime.RemoteObject object;
  }
  
  private static class SetAttributesAsTextRequest
  {
    @JsonProperty(required=true)
    public int nodeId;
    @JsonProperty(required=true)
    public String text;
  }
  
  private static class SetInspectModeEnabledRequest
  {
    @JsonProperty(required=true)
    public boolean enabled;
    @JsonProperty
    public DOM.HighlightConfig highlightConfig;
    @JsonProperty
    public Boolean inspectShadowDOM;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */