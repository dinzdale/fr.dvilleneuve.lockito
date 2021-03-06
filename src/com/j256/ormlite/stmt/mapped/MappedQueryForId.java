package com.j256.ormlite.stmt.mapped;

import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.db.DatabaseType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableInfo;
import java.sql.SQLException;

public class MappedQueryForId<T, ID>
  extends BaseMappedQuery<T, ID>
{
  private final String label;
  
  protected MappedQueryForId(TableInfo<T, ID> paramTableInfo, String paramString1, FieldType[] paramArrayOfFieldType1, FieldType[] paramArrayOfFieldType2, String paramString2)
  {
    super(paramTableInfo, paramString1, paramArrayOfFieldType1, paramArrayOfFieldType2);
    label = paramString2;
  }
  
  public static <T, ID> MappedQueryForId<T, ID> build(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, FieldType paramFieldType)
    throws SQLException
  {
    FieldType localFieldType = paramFieldType;
    if (paramFieldType == null)
    {
      paramFieldType = paramTableInfo.getIdField();
      localFieldType = paramFieldType;
      if (paramFieldType == null) {
        throw new SQLException("Cannot query-for-id with " + paramTableInfo.getDataClass() + " because it doesn't have an id field");
      }
    }
    paramDatabaseType = buildStatement(paramDatabaseType, paramTableInfo, localFieldType);
    paramFieldType = paramTableInfo.getFieldTypes();
    return new MappedQueryForId(paramTableInfo, paramDatabaseType, new FieldType[] { localFieldType }, paramFieldType, "query-for-id");
  }
  
  protected static <T, ID> String buildStatement(DatabaseType paramDatabaseType, TableInfo<T, ID> paramTableInfo, FieldType paramFieldType)
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendTableName(paramDatabaseType, localStringBuilder, "SELECT * FROM ", paramTableInfo.getTableName());
    appendWhereFieldEq(paramDatabaseType, paramFieldType, localStringBuilder, null);
    return localStringBuilder.toString();
  }
  
  private void logArgs(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length > 0) {
      logger.trace("{} arguments: {}", label, paramArrayOfObject);
    }
  }
  
  public T execute(DatabaseConnection paramDatabaseConnection, ID paramID, ObjectCache paramObjectCache)
    throws SQLException
  {
    if (paramObjectCache != null)
    {
      localObject = paramObjectCache.get(clazz, paramID);
      if (localObject != null) {
        return (T)localObject;
      }
    }
    Object localObject = new Object[1];
    localObject[0] = convertIdToFieldObject(paramID);
    paramDatabaseConnection = paramDatabaseConnection.queryForOne(statement, (Object[])localObject, argFieldTypes, this, paramObjectCache);
    if (paramDatabaseConnection == null) {
      logger.debug("{} using '{}' and {} args, got no results", label, statement, Integer.valueOf(localObject.length));
    }
    for (;;)
    {
      logArgs((Object[])localObject);
      return paramDatabaseConnection;
      if (paramDatabaseConnection == DatabaseConnection.MORE_THAN_ONE)
      {
        logger.error("{} using '{}' and {} args, got >1 results", label, statement, Integer.valueOf(localObject.length));
        logArgs((Object[])localObject);
        throw new SQLException(label + " got more than 1 result: " + statement);
      }
      logger.debug("{} using '{}' and {} args, got 1 result", label, statement, Integer.valueOf(localObject.length));
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.stmt.mapped.MappedQueryForId
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */