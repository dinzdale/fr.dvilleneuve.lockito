package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.google.android.gms.common.util.zze;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

class zzcf$zzb
  extends SQLiteOpenHelper
{
  private boolean axe;
  private long axf = 0L;
  
  zzcf$zzb(zzcf paramzzcf, Context paramContext, String paramString)
  {
    super(paramContext, paramString, null, 1);
  }
  
  /* Error */
  private boolean zza(String paramString, SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_2
    //   4: ldc 29
    //   6: iconst_1
    //   7: anewarray 31	java/lang/String
    //   10: dup
    //   11: iconst_0
    //   12: ldc 33
    //   14: aastore
    //   15: ldc 35
    //   17: iconst_1
    //   18: anewarray 31	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: aload_1
    //   24: aastore
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokevirtual 41	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_2
    //   32: aload_2
    //   33: invokeinterface 47 1 0
    //   38: istore_3
    //   39: aload_2
    //   40: ifnull +9 -> 49
    //   43: aload_2
    //   44: invokeinterface 51 1 0
    //   49: iload_3
    //   50: ireturn
    //   51: astore_2
    //   52: aconst_null
    //   53: astore_2
    //   54: aload_1
    //   55: invokestatic 55	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   58: astore_1
    //   59: aload_1
    //   60: invokevirtual 59	java/lang/String:length	()I
    //   63: ifeq +26 -> 89
    //   66: ldc 61
    //   68: aload_1
    //   69: invokevirtual 65	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   72: astore_1
    //   73: aload_1
    //   74: invokestatic 71	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   77: aload_2
    //   78: ifnull +9 -> 87
    //   81: aload_2
    //   82: invokeinterface 51 1 0
    //   87: iconst_0
    //   88: ireturn
    //   89: new 31	java/lang/String
    //   92: dup
    //   93: ldc 61
    //   95: invokespecial 73	java/lang/String:<init>	(Ljava/lang/String;)V
    //   98: astore_1
    //   99: goto -26 -> 73
    //   102: astore_1
    //   103: aload_2
    //   104: ifnull +9 -> 113
    //   107: aload_2
    //   108: invokeinterface 51 1 0
    //   113: aload_1
    //   114: athrow
    //   115: astore_1
    //   116: aload 4
    //   118: astore_2
    //   119: goto -16 -> 103
    //   122: astore_1
    //   123: goto -20 -> 103
    //   126: astore 4
    //   128: goto -74 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	this	zzb
    //   0	131	1	paramString	String
    //   0	131	2	paramSQLiteDatabase	SQLiteDatabase
    //   38	12	3	bool	boolean
    //   1	116	4	localObject	Object
    //   126	1	4	localSQLiteException	SQLiteException
    // Exception table:
    //   from	to	target	type
    //   3	32	51	android/database/sqlite/SQLiteException
    //   54	73	102	finally
    //   73	77	102	finally
    //   89	99	102	finally
    //   3	32	115	finally
    //   32	39	122	finally
    //   32	39	126	android/database/sqlite/SQLiteException
  }
  
  private void zzc(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
    HashSet localHashSet = new HashSet();
    try
    {
      String[] arrayOfString = paramSQLiteDatabase.getColumnNames();
      int i = 0;
      while (i < arrayOfString.length)
      {
        localHashSet.add(arrayOfString[i]);
        i += 1;
      }
      paramSQLiteDatabase.close();
      if ((!localHashSet.remove("hit_id")) || (!localHashSet.remove("hit_url")) || (!localHashSet.remove("hit_time")) || (!localHashSet.remove("hit_first_send_time"))) {
        throw new SQLiteException("Database column missing");
      }
    }
    finally
    {
      paramSQLiteDatabase.close();
    }
    if (!((Set)localObject).isEmpty()) {
      throw new SQLiteException("Database has extra columns");
    }
  }
  
  public SQLiteDatabase getWritableDatabase()
  {
    if ((axe) && (axf + 3600000L > zzcf.zza(axd).currentTimeMillis())) {
      throw new SQLiteException("Database creation failed");
    }
    Object localObject1 = null;
    axe = true;
    axf = zzcf.zza(axd).currentTimeMillis();
    try
    {
      localObject2 = super.getWritableDatabase();
      localObject1 = localObject2;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        Object localObject2;
        zzcf.zzc(axd).getDatabasePath(zzcf.zzb(axd)).delete();
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = super.getWritableDatabase();
    }
    axe = false;
    return (SQLiteDatabase)localObject2;
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    zzam.zzet(paramSQLiteDatabase.getPath());
  }
  
  public void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    Cursor localCursor;
    if (Build.VERSION.SDK_INT < 15) {
      localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
    }
    try
    {
      localCursor.moveToFirst();
      localCursor.close();
      if (!zza("gtm_hits", paramSQLiteDatabase))
      {
        paramSQLiteDatabase.execSQL(zzcf.zzccd());
        return;
      }
    }
    finally
    {
      localCursor.close();
    }
    zzc(paramSQLiteDatabase);
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzcf.zzb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */