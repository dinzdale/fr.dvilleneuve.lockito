package com.j256.ormlite.dao;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.logger.Log.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.ObjectFactory;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RuntimeExceptionDao<T, ID>
  implements CloseableIterable<T>
{
  private static final Log.Level LOG_LEVEL = Log.Level.DEBUG;
  private static final Logger logger = LoggerFactory.getLogger(RuntimeExceptionDao.class);
  private Dao<T, ID> dao;
  
  public RuntimeExceptionDao(Dao<T, ID> paramDao)
  {
    dao = paramDao;
  }
  
  public static <T, ID> RuntimeExceptionDao<T, ID> createDao(ConnectionSource paramConnectionSource, DatabaseTableConfig<T> paramDatabaseTableConfig)
    throws SQLException
  {
    return new RuntimeExceptionDao(DaoManager.createDao(paramConnectionSource, paramDatabaseTableConfig));
  }
  
  public static <T, ID> RuntimeExceptionDao<T, ID> createDao(ConnectionSource paramConnectionSource, Class<T> paramClass)
    throws SQLException
  {
    return new RuntimeExceptionDao(DaoManager.createDao(paramConnectionSource, paramClass));
  }
  
  private void logMessage(Exception paramException, String paramString)
  {
    logger.log(LOG_LEVEL, paramException, paramString);
  }
  
  public void assignEmptyForeignCollection(T paramT, String paramString)
  {
    try
    {
      dao.assignEmptyForeignCollection(paramT, paramString);
      return;
    }
    catch (SQLException paramT)
    {
      logMessage(paramT, "assignEmptyForeignCollection threw exception on " + paramString);
      throw new RuntimeException(paramT);
    }
  }
  
  public <CT> CT callBatchTasks(Callable<CT> paramCallable)
  {
    try
    {
      Object localObject = dao.callBatchTasks(paramCallable);
      return (CT)localObject;
    }
    catch (Exception localException)
    {
      logMessage(localException, "callBatchTasks threw exception on: " + paramCallable);
      throw new RuntimeException(localException);
    }
  }
  
  public void clearObjectCache()
  {
    dao.clearObjectCache();
  }
  
  public void closeLastIterator()
  {
    try
    {
      dao.closeLastIterator();
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "closeLastIterator threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public CloseableIterator<T> closeableIterator()
  {
    return dao.closeableIterator();
  }
  
  public void commit(DatabaseConnection paramDatabaseConnection)
  {
    try
    {
      dao.commit(paramDatabaseConnection);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "commit(" + paramDatabaseConnection + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public long countOf()
  {
    try
    {
      long l = dao.countOf();
      return l;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "countOf threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public long countOf(PreparedQuery<T> paramPreparedQuery)
  {
    try
    {
      long l = dao.countOf(paramPreparedQuery);
      return l;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "countOf threw exception on " + paramPreparedQuery);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int create(T paramT)
  {
    try
    {
      int i = dao.create(paramT);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "create threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public T createIfNotExists(T paramT)
  {
    try
    {
      Object localObject = dao.createIfNotExists(paramT);
      return (T)localObject;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "createIfNotExists threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public Dao.CreateOrUpdateStatus createOrUpdate(T paramT)
  {
    try
    {
      Dao.CreateOrUpdateStatus localCreateOrUpdateStatus = dao.createOrUpdate(paramT);
      return localCreateOrUpdateStatus;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "createOrUpdate threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int delete(PreparedDelete<T> paramPreparedDelete)
  {
    try
    {
      int i = dao.delete(paramPreparedDelete);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "delete threw exception on: " + paramPreparedDelete);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int delete(T paramT)
  {
    try
    {
      int i = dao.delete(paramT);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "delete threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int delete(Collection<T> paramCollection)
  {
    try
    {
      int i = dao.delete(paramCollection);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "delete threw exception on: " + paramCollection);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public DeleteBuilder<T, ID> deleteBuilder()
  {
    return dao.deleteBuilder();
  }
  
  public int deleteById(ID paramID)
  {
    try
    {
      int i = dao.deleteById(paramID);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "deleteById threw exception on: " + paramID);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int deleteIds(Collection<ID> paramCollection)
  {
    try
    {
      int i = dao.deleteIds(paramCollection);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "deleteIds threw exception on: " + paramCollection);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void endThreadConnection(DatabaseConnection paramDatabaseConnection)
  {
    try
    {
      dao.endThreadConnection(paramDatabaseConnection);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "endThreadConnection(" + paramDatabaseConnection + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int executeRaw(String paramString, String... paramVarArgs)
  {
    try
    {
      int i = dao.executeRaw(paramString, paramVarArgs);
      return i;
    }
    catch (SQLException paramVarArgs)
    {
      logMessage(paramVarArgs, "executeRaw threw exception on: " + paramString);
      throw new RuntimeException(paramVarArgs);
    }
  }
  
  public int executeRawNoArgs(String paramString)
  {
    try
    {
      int i = dao.executeRawNoArgs(paramString);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "executeRawNoArgs threw exception on: " + paramString);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public ID extractId(T paramT)
  {
    try
    {
      Object localObject = dao.extractId(paramT);
      return (ID)localObject;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "extractId threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public FieldType findForeignFieldType(Class<?> paramClass)
  {
    return dao.findForeignFieldType(paramClass);
  }
  
  public ConnectionSource getConnectionSource()
  {
    return dao.getConnectionSource();
  }
  
  public Class<T> getDataClass()
  {
    return dao.getDataClass();
  }
  
  public <FT> ForeignCollection<FT> getEmptyForeignCollection(String paramString)
  {
    try
    {
      ForeignCollection localForeignCollection = dao.getEmptyForeignCollection(paramString);
      return localForeignCollection;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "getEmptyForeignCollection threw exception on " + paramString);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public ObjectCache getObjectCache()
  {
    return dao.getObjectCache();
  }
  
  public RawRowMapper<T> getRawRowMapper()
  {
    return dao.getRawRowMapper();
  }
  
  public GenericRowMapper<T> getSelectStarRowMapper()
  {
    try
    {
      GenericRowMapper localGenericRowMapper = dao.getSelectStarRowMapper();
      return localGenericRowMapper;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "getSelectStarRowMapper threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public CloseableWrappedIterable<T> getWrappedIterable()
  {
    return dao.getWrappedIterable();
  }
  
  public CloseableWrappedIterable<T> getWrappedIterable(PreparedQuery<T> paramPreparedQuery)
  {
    return dao.getWrappedIterable(paramPreparedQuery);
  }
  
  public boolean idExists(ID paramID)
  {
    try
    {
      boolean bool = dao.idExists(paramID);
      return bool;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "idExists threw exception on " + paramID);
      throw new RuntimeException(localSQLException);
    }
  }
  
  @Deprecated
  public boolean isAutoCommit()
  {
    try
    {
      boolean bool = dao.isAutoCommit();
      return bool;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "isAutoCommit() threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public boolean isAutoCommit(DatabaseConnection paramDatabaseConnection)
  {
    try
    {
      boolean bool = dao.isAutoCommit(paramDatabaseConnection);
      return bool;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "isAutoCommit(" + paramDatabaseConnection + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public boolean isTableExists()
  {
    try
    {
      boolean bool = dao.isTableExists();
      return bool;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "isTableExists threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public boolean isUpdatable()
  {
    return dao.isUpdatable();
  }
  
  public CloseableIterator<T> iterator()
  {
    return dao.iterator();
  }
  
  public CloseableIterator<T> iterator(int paramInt)
  {
    return dao.iterator(paramInt);
  }
  
  public CloseableIterator<T> iterator(PreparedQuery<T> paramPreparedQuery)
  {
    try
    {
      CloseableIterator localCloseableIterator = dao.iterator(paramPreparedQuery);
      return localCloseableIterator;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "iterator threw exception on: " + paramPreparedQuery);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public CloseableIterator<T> iterator(PreparedQuery<T> paramPreparedQuery, int paramInt)
  {
    try
    {
      CloseableIterator localCloseableIterator = dao.iterator(paramPreparedQuery, paramInt);
      return localCloseableIterator;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "iterator threw exception on: " + paramPreparedQuery);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public T mapSelectStarRow(DatabaseResults paramDatabaseResults)
  {
    try
    {
      paramDatabaseResults = dao.mapSelectStarRow(paramDatabaseResults);
      return paramDatabaseResults;
    }
    catch (SQLException paramDatabaseResults)
    {
      logMessage(paramDatabaseResults, "mapSelectStarRow threw exception on results");
      throw new RuntimeException(paramDatabaseResults);
    }
  }
  
  public String objectToString(T paramT)
  {
    return dao.objectToString(paramT);
  }
  
  public boolean objectsEqual(T paramT1, T paramT2)
  {
    try
    {
      boolean bool = dao.objectsEqual(paramT1, paramT2);
      return bool;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "objectsEqual threw exception on: " + paramT1 + " and " + paramT2);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public List<T> query(PreparedQuery<T> paramPreparedQuery)
  {
    try
    {
      List localList = dao.query(paramPreparedQuery);
      return localList;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "query threw exception on: " + paramPreparedQuery);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public QueryBuilder<T, ID> queryBuilder()
  {
    return dao.queryBuilder();
  }
  
  public List<T> queryForAll()
  {
    try
    {
      List localList = dao.queryForAll();
      return localList;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForAll threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public List<T> queryForEq(String paramString, Object paramObject)
  {
    try
    {
      paramObject = dao.queryForEq(paramString, paramObject);
      return (List<T>)paramObject;
    }
    catch (SQLException paramObject)
    {
      logMessage((Exception)paramObject, "queryForEq threw exception on: " + paramString);
      throw new RuntimeException((Throwable)paramObject);
    }
  }
  
  public List<T> queryForFieldValues(Map<String, Object> paramMap)
  {
    try
    {
      paramMap = dao.queryForFieldValues(paramMap);
      return paramMap;
    }
    catch (SQLException paramMap)
    {
      logMessage(paramMap, "queryForFieldValues threw exception");
      throw new RuntimeException(paramMap);
    }
  }
  
  public List<T> queryForFieldValuesArgs(Map<String, Object> paramMap)
  {
    try
    {
      paramMap = dao.queryForFieldValuesArgs(paramMap);
      return paramMap;
    }
    catch (SQLException paramMap)
    {
      logMessage(paramMap, "queryForFieldValuesArgs threw exception");
      throw new RuntimeException(paramMap);
    }
  }
  
  public T queryForFirst(PreparedQuery<T> paramPreparedQuery)
  {
    try
    {
      Object localObject = dao.queryForFirst(paramPreparedQuery);
      return (T)localObject;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForFirst threw exception on: " + paramPreparedQuery);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public T queryForId(ID paramID)
  {
    try
    {
      Object localObject = dao.queryForId(paramID);
      return (T)localObject;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForId threw exception on: " + paramID);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public List<T> queryForMatching(T paramT)
  {
    try
    {
      List localList = dao.queryForMatching(paramT);
      return localList;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForMatching threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public List<T> queryForMatchingArgs(T paramT)
  {
    try
    {
      List localList = dao.queryForMatchingArgs(paramT);
      return localList;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForMatchingArgs threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public T queryForSameId(T paramT)
  {
    try
    {
      Object localObject = dao.queryForSameId(paramT);
      return (T)localObject;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "queryForSameId threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public <UO> GenericRawResults<UO> queryRaw(String paramString, RawRowMapper<UO> paramRawRowMapper, String... paramVarArgs)
  {
    try
    {
      paramRawRowMapper = dao.queryRaw(paramString, paramRawRowMapper, paramVarArgs);
      return paramRawRowMapper;
    }
    catch (SQLException paramRawRowMapper)
    {
      logMessage(paramRawRowMapper, "queryRaw threw exception on: " + paramString);
      throw new RuntimeException(paramRawRowMapper);
    }
  }
  
  public <UO> GenericRawResults<UO> queryRaw(String paramString, DataType[] paramArrayOfDataType, RawRowObjectMapper<UO> paramRawRowObjectMapper, String... paramVarArgs)
  {
    try
    {
      paramArrayOfDataType = dao.queryRaw(paramString, paramArrayOfDataType, paramRawRowObjectMapper, paramVarArgs);
      return paramArrayOfDataType;
    }
    catch (SQLException paramArrayOfDataType)
    {
      logMessage(paramArrayOfDataType, "queryRaw threw exception on: " + paramString);
      throw new RuntimeException(paramArrayOfDataType);
    }
  }
  
  public GenericRawResults<Object[]> queryRaw(String paramString, DataType[] paramArrayOfDataType, String... paramVarArgs)
  {
    try
    {
      paramArrayOfDataType = dao.queryRaw(paramString, paramArrayOfDataType, paramVarArgs);
      return paramArrayOfDataType;
    }
    catch (SQLException paramArrayOfDataType)
    {
      logMessage(paramArrayOfDataType, "queryRaw threw exception on: " + paramString);
      throw new RuntimeException(paramArrayOfDataType);
    }
  }
  
  public GenericRawResults<String[]> queryRaw(String paramString, String... paramVarArgs)
  {
    try
    {
      paramVarArgs = dao.queryRaw(paramString, paramVarArgs);
      return paramVarArgs;
    }
    catch (SQLException paramVarArgs)
    {
      logMessage(paramVarArgs, "queryRaw threw exception on: " + paramString);
      throw new RuntimeException(paramVarArgs);
    }
  }
  
  public long queryRawValue(String paramString, String... paramVarArgs)
  {
    try
    {
      long l = dao.queryRawValue(paramString, paramVarArgs);
      return l;
    }
    catch (SQLException paramVarArgs)
    {
      logMessage(paramVarArgs, "queryRawValue threw exception on: " + paramString);
      throw new RuntimeException(paramVarArgs);
    }
  }
  
  public int refresh(T paramT)
  {
    try
    {
      int i = dao.refresh(paramT);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "refresh threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void rollBack(DatabaseConnection paramDatabaseConnection)
  {
    try
    {
      dao.rollBack(paramDatabaseConnection);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "rollBack(" + paramDatabaseConnection + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void setAutoCommit(DatabaseConnection paramDatabaseConnection, boolean paramBoolean)
  {
    try
    {
      dao.setAutoCommit(paramDatabaseConnection, paramBoolean);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "setAutoCommit(" + paramDatabaseConnection + "," + paramBoolean + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  @Deprecated
  public void setAutoCommit(boolean paramBoolean)
  {
    try
    {
      dao.setAutoCommit(paramBoolean);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "setAutoCommit(" + paramBoolean + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void setObjectCache(ObjectCache paramObjectCache)
  {
    try
    {
      dao.setObjectCache(paramObjectCache);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "setObjectCache threw exception on " + paramObjectCache);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void setObjectCache(boolean paramBoolean)
  {
    try
    {
      dao.setObjectCache(paramBoolean);
      return;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "setObjectCache(" + paramBoolean + ") threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public void setObjectFactory(ObjectFactory<T> paramObjectFactory)
  {
    dao.setObjectFactory(paramObjectFactory);
  }
  
  public DatabaseConnection startThreadConnection()
  {
    try
    {
      DatabaseConnection localDatabaseConnection = dao.startThreadConnection();
      return localDatabaseConnection;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "startThreadConnection() threw exception");
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int update(PreparedUpdate<T> paramPreparedUpdate)
  {
    try
    {
      int i = dao.update(paramPreparedUpdate);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "update threw exception on: " + paramPreparedUpdate);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public int update(T paramT)
  {
    try
    {
      int i = dao.update(paramT);
      return i;
    }
    catch (SQLException localSQLException)
    {
      logMessage(localSQLException, "update threw exception on: " + paramT);
      throw new RuntimeException(localSQLException);
    }
  }
  
  public UpdateBuilder<T, ID> updateBuilder()
  {
    return dao.updateBuilder();
  }
  
  public int updateId(T paramT, ID paramID)
  {
    try
    {
      int i = dao.updateId(paramT, paramID);
      return i;
    }
    catch (SQLException paramID)
    {
      logMessage(paramID, "updateId threw exception on: " + paramT);
      throw new RuntimeException(paramID);
    }
  }
  
  public int updateRaw(String paramString, String... paramVarArgs)
  {
    try
    {
      int i = dao.updateRaw(paramString, paramVarArgs);
      return i;
    }
    catch (SQLException paramVarArgs)
    {
      logMessage(paramVarArgs, "updateRaw threw exception on: " + paramString);
      throw new RuntimeException(paramVarArgs);
    }
  }
}

/* Location:
 * Qualified Name:     com.j256.ormlite.dao.RuntimeExceptionDao
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */