package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer
{
  static final int INITIAL_CHUNK_SIZE = 12;
  static final int MAX_CHUNK_SIZE = 262144;
  static final int SMALL_CHUNK_SIZE = 16384;
  private Node _bufferHead;
  private Node _bufferTail;
  private int _bufferedEntryCount;
  private Object[] _freeBuffer;
  
  protected final void _copyTo(Object paramObject, int paramInt1, Object[] paramArrayOfObject, int paramInt2)
  {
    Node localNode = _bufferHead;
    int i = 0;
    while (localNode != null)
    {
      Object[] arrayOfObject = localNode.getData();
      int j = arrayOfObject.length;
      System.arraycopy(arrayOfObject, 0, paramObject, i, j);
      i += j;
      localNode = localNode.next();
    }
    System.arraycopy(paramArrayOfObject, 0, paramObject, i, paramInt2);
    paramInt2 = i + paramInt2;
    if (paramInt2 != paramInt1) {
      throw new IllegalStateException("Should have gotten " + paramInt1 + " entries, got " + paramInt2);
    }
  }
  
  protected void _reset()
  {
    if (_bufferTail != null) {
      _freeBuffer = _bufferTail.getData();
    }
    _bufferTail = null;
    _bufferHead = null;
    _bufferedEntryCount = 0;
  }
  
  public Object[] appendCompletedChunk(Object[] paramArrayOfObject)
  {
    Node localNode = new Node(paramArrayOfObject);
    int i;
    if (_bufferHead == null)
    {
      _bufferTail = localNode;
      _bufferHead = localNode;
      i = paramArrayOfObject.length;
      _bufferedEntryCount += i;
      if (i >= 16384) {
        break label71;
      }
      i += i;
    }
    for (;;)
    {
      return new Object[i];
      _bufferTail.linkNext(localNode);
      _bufferTail = localNode;
      break;
      label71:
      i += (i >> 2);
    }
  }
  
  public int bufferedSize()
  {
    return _bufferedEntryCount;
  }
  
  public void completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, List<Object> paramList)
  {
    int j = 0;
    int i;
    for (Node localNode = _bufferHead;; localNode = localNode.next())
    {
      i = j;
      if (localNode == null) {
        break;
      }
      Object[] arrayOfObject = localNode.getData();
      int k = arrayOfObject.length;
      i = 0;
      while (i < k)
      {
        paramList.add(arrayOfObject[i]);
        i += 1;
      }
    }
    while (i < paramInt)
    {
      paramList.add(paramArrayOfObject[i]);
      i += 1;
    }
  }
  
  public Object[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt)
  {
    int i = _bufferedEntryCount + paramInt;
    Object[] arrayOfObject = new Object[i];
    _copyTo(arrayOfObject, i, paramArrayOfObject, paramInt);
    return arrayOfObject;
  }
  
  public <T> T[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, Class<T> paramClass)
  {
    int i = paramInt + _bufferedEntryCount;
    paramClass = (Object[])Array.newInstance(paramClass, i);
    _copyTo(paramClass, i, paramArrayOfObject, paramInt);
    _reset();
    return paramClass;
  }
  
  public int initialCapacity()
  {
    if (_freeBuffer == null) {
      return 0;
    }
    return _freeBuffer.length;
  }
  
  public Object[] resetAndStart()
  {
    _reset();
    if (_freeBuffer == null) {
      return new Object[12];
    }
    return _freeBuffer;
  }
  
  static final class Node
  {
    final Object[] _data;
    Node _next;
    
    public Node(Object[] paramArrayOfObject)
    {
      _data = paramArrayOfObject;
    }
    
    public Object[] getData()
    {
      return _data;
    }
    
    public void linkNext(Node paramNode)
    {
      if (_next != null) {
        throw new IllegalStateException();
      }
      _next = paramNode;
    }
    
    public Node next()
    {
      return _next;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ObjectBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */