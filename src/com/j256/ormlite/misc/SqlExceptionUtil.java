package com.j256.ormlite.misc;

import java.sql.SQLException;

public class SqlExceptionUtil
{
  public static SQLException create(String paramString, Throwable paramThrowable)
  {
    paramString = new SQLException(paramString);
    paramString.initCause(paramThrowable);
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.misc.SqlExceptionUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */