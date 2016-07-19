package com.facebook.stetho.dumpapp;

import java.util.Iterator;

public class ArgsHelper
{
  public static String nextArg(Iterator<String> paramIterator, String paramString)
    throws DumpUsageException
  {
    if (!paramIterator.hasNext()) {
      throw new DumpUsageException(paramString);
    }
    return (String)paramIterator.next();
  }
  
  public static String nextOptionalArg(Iterator<String> paramIterator, String paramString)
  {
    if (paramIterator.hasNext()) {
      return (String)paramIterator.next();
    }
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.ArgsHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */