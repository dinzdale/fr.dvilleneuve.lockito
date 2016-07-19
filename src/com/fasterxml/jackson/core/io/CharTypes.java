package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes
{
  private static final byte[] HEX_BYTES;
  private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
  static final int[] sHexValues;
  static final int[] sInputCodes;
  static final int[] sInputCodesComment;
  static final int[] sInputCodesJsNames;
  static final int[] sInputCodesUTF8;
  static final int[] sInputCodesUtf8JsNames;
  static final int[] sInputCodesWS;
  static final int[] sOutputEscapes128;
  
  static
  {
    int j = HEX_CHARS.length;
    HEX_BYTES = new byte[j];
    int i = 0;
    while (i < j)
    {
      HEX_BYTES[i] = ((byte)HEX_CHARS[i]);
      i += 1;
    }
    int[] arrayOfInt = new int['Ā'];
    i = 0;
    while (i < 32)
    {
      arrayOfInt[i] = -1;
      i += 1;
    }
    arrayOfInt[34] = 1;
    arrayOfInt[92] = 1;
    sInputCodes = arrayOfInt;
    arrayOfInt = new int[sInputCodes.length];
    System.arraycopy(sInputCodes, 0, arrayOfInt, 0, arrayOfInt.length);
    j = 128;
    if (j < 256)
    {
      if ((j & 0xE0) == 192) {
        i = 2;
      }
      for (;;)
      {
        arrayOfInt[j] = i;
        j += 1;
        break;
        if ((j & 0xF0) == 224) {
          i = 3;
        } else if ((j & 0xF8) == 240) {
          i = 4;
        } else {
          i = -1;
        }
      }
    }
    sInputCodesUTF8 = arrayOfInt;
    arrayOfInt = new int['Ā'];
    Arrays.fill(arrayOfInt, -1);
    i = 33;
    while (i < 256)
    {
      if (Character.isJavaIdentifierPart((char)i)) {
        arrayOfInt[i] = 0;
      }
      i += 1;
    }
    arrayOfInt[64] = 0;
    arrayOfInt[35] = 0;
    arrayOfInt[42] = 0;
    arrayOfInt[45] = 0;
    arrayOfInt[43] = 0;
    sInputCodesJsNames = arrayOfInt;
    arrayOfInt = new int['Ā'];
    System.arraycopy(sInputCodesJsNames, 0, arrayOfInt, 0, arrayOfInt.length);
    Arrays.fill(arrayOfInt, 128, 128, 0);
    sInputCodesUtf8JsNames = arrayOfInt;
    arrayOfInt = new int['Ā'];
    System.arraycopy(sInputCodesUTF8, 128, arrayOfInt, 128, 128);
    Arrays.fill(arrayOfInt, 0, 32, -1);
    arrayOfInt[9] = 0;
    arrayOfInt[10] = 10;
    arrayOfInt[13] = 13;
    arrayOfInt[42] = 42;
    sInputCodesComment = arrayOfInt;
    arrayOfInt = new int['Ā'];
    System.arraycopy(sInputCodesUTF8, 128, arrayOfInt, 128, 128);
    Arrays.fill(arrayOfInt, 0, 32, -1);
    arrayOfInt[32] = 1;
    arrayOfInt[9] = 1;
    arrayOfInt[10] = 10;
    arrayOfInt[13] = 13;
    arrayOfInt[47] = 47;
    arrayOfInt[35] = 35;
    sInputCodesWS = arrayOfInt;
    arrayOfInt = new int[''];
    i = 0;
    while (i < 32)
    {
      arrayOfInt[i] = -1;
      i += 1;
    }
    arrayOfInt[34] = 34;
    arrayOfInt[92] = 92;
    arrayOfInt[8] = 98;
    arrayOfInt[9] = 116;
    arrayOfInt[12] = 102;
    arrayOfInt[10] = 110;
    arrayOfInt[13] = 114;
    sOutputEscapes128 = arrayOfInt;
    sHexValues = new int[''];
    Arrays.fill(sHexValues, -1);
    i = 0;
    while (i < 10)
    {
      sHexValues[(i + 48)] = i;
      i += 1;
    }
    i = 0;
    while (i < 6)
    {
      sHexValues[(i + 97)] = (i + 10);
      sHexValues[(i + 65)] = (i + 10);
      i += 1;
    }
  }
  
  public static void appendQuoted(StringBuilder paramStringBuilder, String paramString)
  {
    int[] arrayOfInt = sOutputEscapes128;
    int k = arrayOfInt.length;
    int j = 0;
    int m = paramString.length();
    if (j < m)
    {
      int i = paramString.charAt(j);
      if ((i >= k) || (arrayOfInt[i] == 0)) {
        paramStringBuilder.append(i);
      }
      for (;;)
      {
        j += 1;
        break;
        paramStringBuilder.append('\\');
        int n = arrayOfInt[i];
        if (n < 0)
        {
          paramStringBuilder.append('u');
          paramStringBuilder.append('0');
          paramStringBuilder.append('0');
          paramStringBuilder.append(HEX_CHARS[(i >> 4)]);
          paramStringBuilder.append(HEX_CHARS[(i & 0xF)]);
        }
        else
        {
          paramStringBuilder.append((char)n);
        }
      }
    }
  }
  
  public static int charToHex(int paramInt)
  {
    if (paramInt > 127) {
      return -1;
    }
    return sHexValues[paramInt];
  }
  
  public static byte[] copyHexBytes()
  {
    return (byte[])HEX_BYTES.clone();
  }
  
  public static char[] copyHexChars()
  {
    return (char[])HEX_CHARS.clone();
  }
  
  public static int[] get7BitOutputEscapes()
  {
    return sOutputEscapes128;
  }
  
  public static int[] getInputCodeComment()
  {
    return sInputCodesComment;
  }
  
  public static int[] getInputCodeLatin1()
  {
    return sInputCodes;
  }
  
  public static int[] getInputCodeLatin1JsNames()
  {
    return sInputCodesJsNames;
  }
  
  public static int[] getInputCodeUtf8()
  {
    return sInputCodesUTF8;
  }
  
  public static int[] getInputCodeUtf8JsNames()
  {
    return sInputCodesUtf8JsNames;
  }
  
  public static int[] getInputCodeWS()
  {
    return sInputCodesWS;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.io.CharTypes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */