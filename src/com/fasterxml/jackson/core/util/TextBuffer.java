package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public final class TextBuffer
{
  static final int MAX_SEGMENT_LEN = 262144;
  static final int MIN_SEGMENT_LEN = 1000;
  static final char[] NO_CHARS = new char[0];
  private final BufferRecycler _allocator;
  private char[] _currentSegment;
  private int _currentSize;
  private boolean _hasSegments = false;
  private char[] _inputBuffer;
  private int _inputLen;
  private int _inputStart;
  private char[] _resultArray;
  private String _resultString;
  private int _segmentSize;
  private ArrayList<char[]> _segments;
  
  public TextBuffer(BufferRecycler paramBufferRecycler)
  {
    _allocator = paramBufferRecycler;
  }
  
  private char[] _charArray(int paramInt)
  {
    return new char[paramInt];
  }
  
  private char[] buildResultArray()
  {
    if (_resultString != null) {
      return _resultString.toCharArray();
    }
    int j;
    if (_inputStart >= 0)
    {
      i = _inputLen;
      if (i < 1) {
        return NO_CHARS;
      }
      j = _inputStart;
      if (j == 0) {
        return Arrays.copyOf(_inputBuffer, i);
      }
      return Arrays.copyOfRange(_inputBuffer, j, i + j);
    }
    int i = size();
    if (i < 1) {
      return NO_CHARS;
    }
    char[] arrayOfChar1 = _charArray(i);
    if (_segments != null)
    {
      int k = _segments.size();
      j = 0;
      i = 0;
      while (j < k)
      {
        char[] arrayOfChar2 = (char[])_segments.get(j);
        int m = arrayOfChar2.length;
        System.arraycopy(arrayOfChar2, 0, arrayOfChar1, i, m);
        i += m;
        j += 1;
      }
    }
    for (;;)
    {
      System.arraycopy(_currentSegment, 0, arrayOfChar1, i, _currentSize);
      return arrayOfChar1;
      i = 0;
    }
  }
  
  private void clearSegments()
  {
    _hasSegments = false;
    _segments.clear();
    _segmentSize = 0;
    _currentSize = 0;
  }
  
  private void expand(int paramInt)
  {
    if (_segments == null) {
      _segments = new ArrayList();
    }
    char[] arrayOfChar = _currentSegment;
    _hasSegments = true;
    _segments.add(arrayOfChar);
    _segmentSize += arrayOfChar.length;
    int j = arrayOfChar.length;
    int i = j >> 1;
    if (i < paramInt) {}
    for (;;)
    {
      _currentSize = 0;
      _currentSegment = _charArray(Math.min(262144, j + paramInt));
      return;
      paramInt = i;
    }
  }
  
  private char[] findBuffer(int paramInt)
  {
    if (_allocator != null) {
      return _allocator.allocCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, paramInt);
    }
    return new char[Math.max(paramInt, 1000)];
  }
  
  private void unshare(int paramInt)
  {
    int i = _inputLen;
    _inputLen = 0;
    char[] arrayOfChar = _inputBuffer;
    _inputBuffer = null;
    int j = _inputStart;
    _inputStart = -1;
    paramInt = i + paramInt;
    if ((_currentSegment == null) || (paramInt > _currentSegment.length)) {
      _currentSegment = findBuffer(paramInt);
    }
    if (i > 0) {
      System.arraycopy(arrayOfChar, j, _currentSegment, 0, i);
    }
    _segmentSize = 0;
    _currentSize = i;
  }
  
  public void append(char paramChar)
  {
    if (_inputStart >= 0) {
      unshare(16);
    }
    _resultString = null;
    _resultArray = null;
    char[] arrayOfChar2 = _currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (_currentSize >= arrayOfChar2.length)
    {
      expand(1);
      arrayOfChar1 = _currentSegment;
    }
    int i = _currentSize;
    _currentSize = (i + 1);
    arrayOfChar1[i] = paramChar;
  }
  
  public void append(String paramString, int paramInt1, int paramInt2)
  {
    if (_inputStart >= 0) {
      unshare(paramInt2);
    }
    _resultString = null;
    _resultArray = null;
    char[] arrayOfChar = _currentSegment;
    int k = arrayOfChar.length - _currentSize;
    if (k >= paramInt2)
    {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, _currentSize);
      _currentSize += paramInt2;
      return;
    }
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0)
    {
      paramString.getChars(paramInt1, paramInt1 + k, arrayOfChar, _currentSize);
      i = paramInt2 - k;
      j = paramInt1 + k;
    }
    do
    {
      expand(i);
      paramInt1 = Math.min(_currentSegment.length, i);
      paramString.getChars(j, j + paramInt1, _currentSegment, 0);
      _currentSize += paramInt1;
      j += paramInt1;
      paramInt1 = i - paramInt1;
      i = paramInt1;
    } while (paramInt1 > 0);
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (_inputStart >= 0) {
      unshare(paramInt2);
    }
    _resultString = null;
    _resultArray = null;
    char[] arrayOfChar = _currentSegment;
    int k = arrayOfChar.length - _currentSize;
    if (k >= paramInt2)
    {
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, _currentSize, paramInt2);
      _currentSize += paramInt2;
      return;
    }
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0)
    {
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, _currentSize, k);
      j = paramInt1 + k;
      i = paramInt2 - k;
    }
    do
    {
      expand(i);
      paramInt1 = Math.min(_currentSegment.length, i);
      System.arraycopy(paramArrayOfChar, j, _currentSegment, 0, paramInt1);
      _currentSize += paramInt1;
      j += paramInt1;
      paramInt1 = i - paramInt1;
      i = paramInt1;
    } while (paramInt1 > 0);
  }
  
  public char[] contentsAsArray()
  {
    char[] arrayOfChar2 = _resultArray;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null)
    {
      arrayOfChar1 = buildResultArray();
      _resultArray = arrayOfChar1;
    }
    return arrayOfChar1;
  }
  
  public BigDecimal contentsAsDecimal()
    throws NumberFormatException
  {
    if (_resultArray != null) {
      return NumberInput.parseBigDecimal(_resultArray);
    }
    if ((_inputStart >= 0) && (_inputBuffer != null)) {
      return NumberInput.parseBigDecimal(_inputBuffer, _inputStart, _inputLen);
    }
    if ((_segmentSize == 0) && (_currentSegment != null)) {
      return NumberInput.parseBigDecimal(_currentSegment, 0, _currentSize);
    }
    return NumberInput.parseBigDecimal(contentsAsArray());
  }
  
  public double contentsAsDouble()
    throws NumberFormatException
  {
    return NumberInput.parseDouble(contentsAsString());
  }
  
  public String contentsAsString()
  {
    if (_resultString == null)
    {
      if (_resultArray == null) {
        break label34;
      }
      _resultString = new String(_resultArray);
    }
    for (;;)
    {
      return _resultString;
      label34:
      if (_inputStart >= 0)
      {
        if (_inputLen < 1)
        {
          _resultString = "";
          return "";
        }
        _resultString = new String(_inputBuffer, _inputStart, _inputLen);
      }
      else
      {
        int i = _segmentSize;
        int j = _currentSize;
        if (i == 0)
        {
          if (j == 0) {}
          for (localObject = "";; localObject = new String(_currentSegment, 0, j))
          {
            _resultString = ((String)localObject);
            break;
          }
        }
        Object localObject = new StringBuilder(i + j);
        if (_segments != null)
        {
          j = _segments.size();
          i = 0;
          while (i < j)
          {
            char[] arrayOfChar = (char[])_segments.get(i);
            ((StringBuilder)localObject).append(arrayOfChar, 0, arrayOfChar.length);
            i += 1;
          }
        }
        ((StringBuilder)localObject).append(_currentSegment, 0, _currentSize);
        _resultString = ((StringBuilder)localObject).toString();
      }
    }
  }
  
  public char[] emptyAndGetCurrentSegment()
  {
    _inputStart = -1;
    _currentSize = 0;
    _inputLen = 0;
    _inputBuffer = null;
    _resultString = null;
    _resultArray = null;
    if (_hasSegments) {
      clearSegments();
    }
    char[] arrayOfChar2 = _currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null)
    {
      arrayOfChar1 = findBuffer(0);
      _currentSegment = arrayOfChar1;
    }
    return arrayOfChar1;
  }
  
  public void ensureNotShared()
  {
    if (_inputStart >= 0) {
      unshare(16);
    }
  }
  
  public char[] expandCurrentSegment()
  {
    char[] arrayOfChar = _currentSegment;
    int i = arrayOfChar.length;
    if (i == 262144) {}
    for (i = 262145;; i = Math.min(262144, i + (i >> 1)))
    {
      arrayOfChar = Arrays.copyOf(arrayOfChar, i);
      _currentSegment = arrayOfChar;
      return arrayOfChar;
    }
  }
  
  public char[] finishCurrentSegment()
  {
    if (_segments == null) {
      _segments = new ArrayList();
    }
    _hasSegments = true;
    _segments.add(_currentSegment);
    int i = _currentSegment.length;
    _segmentSize += i;
    char[] arrayOfChar = _charArray(Math.min(i + (i >> 1), 262144));
    _currentSize = 0;
    _currentSegment = arrayOfChar;
    return arrayOfChar;
  }
  
  public char[] getCurrentSegment()
  {
    if (_inputStart >= 0) {
      unshare(1);
    }
    for (;;)
    {
      return _currentSegment;
      char[] arrayOfChar = _currentSegment;
      if (arrayOfChar == null) {
        _currentSegment = findBuffer(0);
      } else if (_currentSize >= arrayOfChar.length) {
        expand(1);
      }
    }
  }
  
  public int getCurrentSegmentSize()
  {
    return _currentSize;
  }
  
  public char[] getTextBuffer()
  {
    if (_inputStart >= 0) {
      return _inputBuffer;
    }
    if (_resultArray != null) {
      return _resultArray;
    }
    if (_resultString != null)
    {
      char[] arrayOfChar = _resultString.toCharArray();
      _resultArray = arrayOfChar;
      return arrayOfChar;
    }
    if (!_hasSegments) {
      return _currentSegment;
    }
    return contentsAsArray();
  }
  
  public int getTextOffset()
  {
    if (_inputStart >= 0) {
      return _inputStart;
    }
    return 0;
  }
  
  public boolean hasTextAsCharacters()
  {
    if ((_inputStart >= 0) || (_resultArray != null)) {}
    while (_resultString == null) {
      return true;
    }
    return false;
  }
  
  public void releaseBuffers()
  {
    if (_allocator == null) {
      resetWithEmpty();
    }
    while (_currentSegment == null) {
      return;
    }
    resetWithEmpty();
    char[] arrayOfChar = _currentSegment;
    _currentSegment = null;
    _allocator.releaseCharBuffer(BufferRecycler.CharBufferType.TEXT_BUFFER, arrayOfChar);
  }
  
  public void resetWithCopy(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    _inputBuffer = null;
    _inputStart = -1;
    _inputLen = 0;
    _resultString = null;
    _resultArray = null;
    if (_hasSegments) {
      clearSegments();
    }
    for (;;)
    {
      _segmentSize = 0;
      _currentSize = 0;
      append(paramArrayOfChar, paramInt1, paramInt2);
      return;
      if (_currentSegment == null) {
        _currentSegment = findBuffer(paramInt2);
      }
    }
  }
  
  public void resetWithEmpty()
  {
    _inputStart = -1;
    _currentSize = 0;
    _inputLen = 0;
    _inputBuffer = null;
    _resultString = null;
    _resultArray = null;
    if (_hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithShared(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    _resultString = null;
    _resultArray = null;
    _inputBuffer = paramArrayOfChar;
    _inputStart = paramInt1;
    _inputLen = paramInt2;
    if (_hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithString(String paramString)
  {
    _inputBuffer = null;
    _inputStart = -1;
    _inputLen = 0;
    _resultString = paramString;
    _resultArray = null;
    if (_hasSegments) {
      clearSegments();
    }
    _currentSize = 0;
  }
  
  public void setCurrentLength(int paramInt)
  {
    _currentSize = paramInt;
  }
  
  public int size()
  {
    if (_inputStart >= 0) {
      return _inputLen;
    }
    if (_resultArray != null) {
      return _resultArray.length;
    }
    if (_resultString != null) {
      return _resultString.length();
    }
    return _segmentSize + _currentSize;
  }
  
  public String toString()
  {
    return contentsAsString();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.util.TextBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */