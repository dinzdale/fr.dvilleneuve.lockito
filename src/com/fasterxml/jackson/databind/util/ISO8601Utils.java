package com.fasterxml.jackson.databind.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final String GMT_ID = "GMT";
  private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
  
  private static void checkOffset(String paramString, int paramInt, char paramChar)
    throws IndexOutOfBoundsException
  {
    char c = paramString.charAt(paramInt);
    if (c != paramChar) {
      throw new IndexOutOfBoundsException("Expected '" + paramChar + "' character but found '" + c + "'");
    }
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int k = "yyyy-MM-ddThh:mm:ss".length();
    int i;
    int j;
    label51:
    char c;
    if (paramBoolean)
    {
      i = ".sss".length();
      if (paramTimeZone.getRawOffset() != 0) {
        break label320;
      }
      j = "Z".length();
      paramDate = new StringBuilder(j + (k + i));
      padInt(paramDate, localGregorianCalendar.get(1), "yyyy".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(2) + 1, "MM".length());
      paramDate.append('-');
      padInt(paramDate, localGregorianCalendar.get(5), "dd".length());
      paramDate.append('T');
      padInt(paramDate, localGregorianCalendar.get(11), "hh".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(12), "mm".length());
      paramDate.append(':');
      padInt(paramDate, localGregorianCalendar.get(13), "ss".length());
      if (paramBoolean)
      {
        paramDate.append('.');
        padInt(paramDate, localGregorianCalendar.get(14), "sss".length());
      }
      i = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
      if (i == 0) {
        break label336;
      }
      j = Math.abs(i / 60000 / 60);
      k = Math.abs(i / 60000 % 60);
      if (i >= 0) {
        break label330;
      }
      c = '-';
      label274:
      paramDate.append(c);
      padInt(paramDate, j, "hh".length());
      paramDate.append(':');
      padInt(paramDate, k, "mm".length());
    }
    for (;;)
    {
      return paramDate.toString();
      i = 0;
      break;
      label320:
      j = "+hh:mm".length();
      break label51;
      label330:
      c = '+';
      break label274;
      label336:
      paramDate.append('Z');
    }
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    paramInt1 = paramInt2 - str.length();
    while (paramInt1 > 0)
    {
      paramStringBuilder.append('0');
      paramInt1 -= 1;
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString)
  {
    try
    {
      k = parseInt(paramString, 0, 4);
      checkOffset(paramString, 4, '-');
      m = parseInt(paramString, 5, 7);
      checkOffset(paramString, 7, '-');
      n = parseInt(paramString, 8, 10);
      checkOffset(paramString, 10, 'T');
      i1 = parseInt(paramString, 11, 13);
      checkOffset(paramString, 13, ':');
      i2 = parseInt(paramString, 14, 16);
      checkOffset(paramString, 16, ':');
      i3 = parseInt(paramString, 17, 19);
      if (paramString.charAt(19) != '.') {
        break label409;
      }
      checkOffset(paramString, 19, '.');
      j = parseInt(paramString, 20, 23);
      i = 23;
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      int k;
      int m;
      int n;
      int i1;
      int i2;
      int i3;
      char c;
      TimeZone localTimeZone;
      for (;;)
      {
        String str;
        if (paramString != null) {
          break label381;
        }
        paramString = null;
        throw new IllegalArgumentException("Failed to parse date [" + paramString + "]: " + localIndexOutOfBoundsException.getMessage(), localIndexOutOfBoundsException);
        if (c != 'Z') {
          break;
        }
        localObject = "GMT";
      }
      throw new IndexOutOfBoundsException("Invalid time zone indicator " + c);
      Object localObject = new GregorianCalendar(localTimeZone);
      ((Calendar)localObject).setLenient(false);
      ((Calendar)localObject).set(1, k);
      ((Calendar)localObject).set(2, m - 1);
      ((Calendar)localObject).set(5, n);
      ((Calendar)localObject).set(11, i1);
      ((Calendar)localObject).set(12, i2);
      ((Calendar)localObject).set(13, i3);
      ((Calendar)localObject).set(14, j);
      localObject = ((Calendar)localObject).getTime();
      return (Date)localObject;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        continue;
        paramString = '"' + paramString + "'";
        continue;
        int i = 19;
        int j = 0;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      label288:
      label381:
      label409:
      for (;;) {}
    }
    c = paramString.charAt(i);
    if ((c == '+') || (c == '-'))
    {
      str = "GMT" + paramString.substring(i);
      localTimeZone = TimeZone.getTimeZone(str);
      if (localTimeZone.getID().equals(str)) {
        break label288;
      }
      throw new IndexOutOfBoundsException();
    }
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 < 0) || (paramInt2 > paramString.length()) || (paramInt1 > paramInt2)) {
      throw new NumberFormatException(paramString);
    }
    int j = 0;
    int i = paramInt1;
    if (paramInt1 < paramInt2)
    {
      i = Character.digit(paramString.charAt(paramInt1), 10);
      if (i < 0) {
        throw new NumberFormatException("Invalid number: " + paramString);
      }
      j = -i;
      i = paramInt1 + 1;
    }
    while (i < paramInt2)
    {
      paramInt1 = Character.digit(paramString.charAt(i), 10);
      if (paramInt1 < 0) {
        throw new NumberFormatException("Invalid number: " + paramString);
      }
      j = j * 10 - paramInt1;
      i += 1;
    }
    return -j;
  }
  
  public static TimeZone timeZoneGMT()
  {
    return TIMEZONE_GMT;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ISO8601Utils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */