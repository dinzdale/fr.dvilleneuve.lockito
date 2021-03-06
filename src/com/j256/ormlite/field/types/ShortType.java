package com.j256.ormlite.field.types;

import com.j256.ormlite.field.SqlType;

public class ShortType
  extends ShortObjectType
{
  private static final ShortType singleTon = new ShortType();
  
  private ShortType()
  {
    super(SqlType.SHORT, new Class[] { Short.TYPE });
  }
  
  protected ShortType(SqlType paramSqlType, Class<?>[] paramArrayOfClass)
  {
    super(paramSqlType, paramArrayOfClass);
  }
  
  public static ShortType getSingleton()
  {
    return singleTon;
  }
  
  public boolean isPrimitive()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.field.types.ShortType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */