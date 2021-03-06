package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.common.internal.zzl.zza;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzqd
  extends GoogleApiClient
  implements zzqm.zza
{
  private final Context mContext;
  private final int rV;
  private final GoogleApiAvailability rX;
  final Api.zza<? extends zzvx, zzvy> rY;
  final zzg tD;
  final Map<Api<?>, Integer> tE;
  private final zzl tQ;
  private zzqm tR = null;
  final Queue<zzpr.zza<?, ?>> tS = new LinkedList();
  private volatile boolean tT;
  private long tU = 120000L;
  private long tV = 5000L;
  private final zza tW;
  zzqj tX;
  final Map<Api.zzc<?>, Api.zze> tY;
  Set<Scope> tZ = new HashSet();
  private final Lock th;
  private final zzqt ua = new zzqt();
  private final ArrayList<zzpu> ub;
  private Integer uc = null;
  Set<zzrc> ud = null;
  final zzrd ue;
  private final zzl.zza uf = new zzl.zza()
  {
    public boolean isConnected()
    {
      return zzqd.this.isConnected();
    }
    
    public Bundle zzamc()
    {
      return null;
    }
  };
  private final Looper zzahv;
  
  public zzqd(Context paramContext, Lock paramLock, Looper paramLooper, zzg paramzzg, GoogleApiAvailability paramGoogleApiAvailability, Api.zza<? extends zzvx, zzvy> paramzza, Map<Api<?>, Integer> paramMap, List<GoogleApiClient.ConnectionCallbacks> paramList, List<GoogleApiClient.OnConnectionFailedListener> paramList1, Map<Api.zzc<?>, Api.zze> paramMap1, int paramInt1, int paramInt2, ArrayList<zzpu> paramArrayList)
  {
    mContext = paramContext;
    th = paramLock;
    tQ = new zzl(paramLooper, uf);
    zzahv = paramLooper;
    tW = new zza(paramLooper);
    rX = paramGoogleApiAvailability;
    rV = paramInt1;
    if (rV >= 0) {
      uc = Integer.valueOf(paramInt2);
    }
    tE = paramMap;
    tY = paramMap1;
    ub = paramArrayList;
    ue = new zzrd(tY);
    paramContext = paramList.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.ConnectionCallbacks)paramContext.next();
      tQ.registerConnectionCallbacks(paramLock);
    }
    paramContext = paramList1.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.OnConnectionFailedListener)paramContext.next();
      tQ.registerConnectionFailedListener(paramLock);
    }
    tD = paramzzg;
    rY = paramzza;
  }
  
  private void resume()
  {
    th.lock();
    try
    {
      if (isResuming()) {
        zzapq();
      }
      return;
    }
    finally
    {
      th.unlock();
    }
  }
  
  public static int zza(Iterable<Api.zze> paramIterable, boolean paramBoolean)
  {
    int k = 1;
    paramIterable = paramIterable.iterator();
    int i = 0;
    int j = 0;
    if (paramIterable.hasNext())
    {
      Api.zze localzze = (Api.zze)paramIterable.next();
      if (localzze.zzafk()) {
        j = 1;
      }
      if (!localzze.zzafz()) {
        break label85;
      }
      i = 1;
    }
    label85:
    for (;;)
    {
      break;
      if (j != 0)
      {
        j = k;
        if (i != 0)
        {
          j = k;
          if (paramBoolean) {
            j = 2;
          }
        }
        return j;
      }
      return 3;
    }
  }
  
  private void zza(final GoogleApiClient paramGoogleApiClient, final zzqz paramzzqz, final boolean paramBoolean)
  {
    zzrj.zh.zzg(paramGoogleApiClient).setResultCallback(new ResultCallback()
    {
      public void zzp(@NonNull Status paramAnonymousStatus)
      {
        zzk.zzbc(zzqd.zzc(zzqd.this)).zzagl();
        if ((paramAnonymousStatus.isSuccess()) && (isConnected())) {
          reconnect();
        }
        paramzzqz.zzc(paramAnonymousStatus);
        if (paramBoolean) {
          paramGoogleApiClient.disconnect();
        }
      }
    });
  }
  
  private void zzapq()
  {
    tQ.zzast();
    tR.connect();
  }
  
  private void zzapr()
  {
    th.lock();
    try
    {
      if (zzapt()) {
        zzapq();
      }
      return;
    }
    finally
    {
      th.unlock();
    }
  }
  
  private void zzb(@NonNull zzqn paramzzqn)
  {
    if (rV >= 0)
    {
      zzpp.zza(paramzzqn).zzff(rV);
      return;
    }
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  private void zzfi(int paramInt)
  {
    if (uc == null) {
      uc = Integer.valueOf(paramInt);
    }
    Object localObject2;
    while (tR != null)
    {
      return;
      if (uc.intValue() != paramInt)
      {
        localObject1 = String.valueOf(zzfj(paramInt));
        localObject2 = String.valueOf(zzfj(uc.intValue()));
        throw new IllegalStateException(String.valueOf(localObject1).length() + 51 + String.valueOf(localObject2).length() + "Cannot use sign-in mode: " + (String)localObject1 + ". Mode was already set to " + (String)localObject2);
      }
    }
    Object localObject1 = tY.values().iterator();
    paramInt = 0;
    int i = 0;
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Api.zze)((Iterator)localObject1).next();
      if (((Api.zze)localObject2).zzafk()) {
        i = 1;
      }
      if (!((Api.zze)localObject2).zzafz()) {
        break label345;
      }
      paramInt = 1;
    }
    label345:
    for (;;)
    {
      break;
      switch (uc.intValue())
      {
      }
      do
      {
        do
        {
          tR = new zzqf(mContext, this, th, zzahv, rX, tY, tD, tE, rY, ub, this);
          return;
          if (i == 0) {
            throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
          }
        } while (paramInt == 0);
        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
      } while (i == 0);
      tR = zzpv.zza(mContext, this, th, zzahv, rX, tY, tD, tE, rY, ub);
      return;
    }
  }
  
  static String zzfj(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 3: 
      return "SIGN_IN_MODE_NONE";
    case 1: 
      return "SIGN_IN_MODE_REQUIRED";
    }
    return "SIGN_IN_MODE_OPTIONAL";
  }
  
  public ConnectionResult blockingConnect()
  {
    boolean bool2 = true;
    boolean bool1;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool1 = true;
    }
    for (;;)
    {
      zzab.zza(bool1, "blockingConnect must not be called on the UI thread");
      th.lock();
      try
      {
        if (rV >= 0) {
          if (uc != null)
          {
            bool1 = bool2;
            label45:
            zzab.zza(bool1, "Sign-in mode should have been set explicitly by auto-manage.");
          }
        }
        do
        {
          for (;;)
          {
            zzfi(uc.intValue());
            tQ.zzast();
            ConnectionResult localConnectionResult = tR.blockingConnect();
            return localConnectionResult;
            bool1 = false;
            break;
            bool1 = false;
            break label45;
            if (uc != null) {
              break label143;
            }
            uc = Integer.valueOf(zza(tY.values(), false));
          }
        } while (uc.intValue() != 2);
      }
      finally
      {
        th.unlock();
      }
    }
    label143:
    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit)
  {
    boolean bool = false;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    }
    zzab.zza(bool, "blockingConnect must not be called on the UI thread");
    zzab.zzb(paramTimeUnit, "TimeUnit must not be null");
    th.lock();
    try
    {
      if (uc == null) {
        uc = Integer.valueOf(zza(tY.values(), false));
      }
      while (uc.intValue() != 2)
      {
        zzfi(uc.intValue());
        tQ.zzast();
        paramTimeUnit = tR.blockingConnect(paramLong, paramTimeUnit);
        return paramTimeUnit;
      }
      throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    finally
    {
      th.unlock();
    }
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect()
  {
    zzab.zza(isConnected(), "GoogleApiClient is not connected yet.");
    if (uc.intValue() != 2) {}
    final zzqz localzzqz;
    for (boolean bool = true;; bool = false)
    {
      zzab.zza(bool, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
      localzzqz = new zzqz(this);
      if (!tY.containsKey(zzrj.bN)) {
        break;
      }
      zza(this, localzzqz, false);
      return localzzqz;
    }
    final AtomicReference localAtomicReference = new AtomicReference();
    Object localObject = new GoogleApiClient.ConnectionCallbacks()
    {
      public void onConnected(Bundle paramAnonymousBundle)
      {
        zzqd.zza(zzqd.this, (GoogleApiClient)localAtomicReference.get(), localzzqz, true);
      }
      
      public void onConnectionSuspended(int paramAnonymousInt) {}
    };
    GoogleApiClient.OnConnectionFailedListener local3 = new GoogleApiClient.OnConnectionFailedListener()
    {
      public void onConnectionFailed(@NonNull ConnectionResult paramAnonymousConnectionResult)
      {
        localzzqz.zzc(new Status(8));
      }
    };
    localObject = new GoogleApiClient.Builder(mContext).addApi(zzrj.API).addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject).addOnConnectionFailedListener(local3).setHandler(tW).build();
    localAtomicReference.set(localObject);
    ((GoogleApiClient)localObject).connect();
    return localzzqz;
  }
  
  public void connect()
  {
    boolean bool = false;
    th.lock();
    try
    {
      if (rV >= 0)
      {
        if (uc != null) {
          bool = true;
        }
        zzab.zza(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      }
      do
      {
        for (;;)
        {
          connect(uc.intValue());
          return;
          if (uc != null) {
            break;
          }
          uc = Integer.valueOf(zza(tY.values(), false));
        }
      } while (uc.intValue() != 2);
    }
    finally
    {
      th.unlock();
    }
    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
  }
  
  /* Error */
  public void connect(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 115	com/google/android/gms/internal/zzqd:th	Ljava/util/concurrent/locks/Lock;
    //   6: invokeinterface 193 1 0
    //   11: iload_3
    //   12: istore_2
    //   13: iload_1
    //   14: iconst_3
    //   15: if_icmpeq +17 -> 32
    //   18: iload_3
    //   19: istore_2
    //   20: iload_1
    //   21: iconst_1
    //   22: if_icmpeq +10 -> 32
    //   25: iload_1
    //   26: iconst_2
    //   27: if_icmpne +50 -> 77
    //   30: iload_3
    //   31: istore_2
    //   32: iload_2
    //   33: new 293	java/lang/StringBuilder
    //   36: dup
    //   37: bipush 33
    //   39: invokespecial 298	java/lang/StringBuilder:<init>	(I)V
    //   42: ldc_w 449
    //   45: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: iload_1
    //   49: invokevirtual 452	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   52: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 454	com/google/android/gms/common/internal/zzab:zzb	(ZLjava/lang/Object;)V
    //   58: aload_0
    //   59: iload_1
    //   60: invokespecial 363	com/google/android/gms/internal/zzqd:zzfi	(I)V
    //   63: aload_0
    //   64: invokespecial 199	com/google/android/gms/internal/zzqd:zzapq	()V
    //   67: aload_0
    //   68: getfield 115	com/google/android/gms/internal/zzqd:th	Ljava/util/concurrent/locks/Lock;
    //   71: invokeinterface 202 1 0
    //   76: return
    //   77: iconst_0
    //   78: istore_2
    //   79: goto -47 -> 32
    //   82: astore 4
    //   84: aload_0
    //   85: getfield 115	com/google/android/gms/internal/zzqd:th	Ljava/util/concurrent/locks/Lock;
    //   88: invokeinterface 202 1 0
    //   93: aload 4
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	zzqd
    //   0	96	1	paramInt	int
    //   12	67	2	bool1	boolean
    //   1	30	3	bool2	boolean
    //   82	12	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   32	67	82	finally
  }
  
  public void disconnect()
  {
    th.lock();
    try
    {
      ue.release();
      if (tR != null) {
        tR.disconnect();
      }
      ua.release();
      Iterator localIterator = tS.iterator();
      while (localIterator.hasNext())
      {
        zzpr.zza localzza = (zzpr.zza)localIterator.next();
        localzza.zza(null);
        localzza.cancel();
      }
      tS.clear();
    }
    finally
    {
      th.unlock();
    }
    zzqm localzzqm = tR;
    if (localzzqm == null)
    {
      th.unlock();
      return;
    }
    zzapt();
    tQ.zzass();
    th.unlock();
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("mContext=").println(mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(tT);
    paramPrintWriter.append(" mWorkQueue.size()=").print(tS.size());
    ue.dump(paramPrintWriter);
    if (tR != null) {
      tR.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    th.lock();
    try
    {
      if ((!isConnected()) && (!isResuming())) {
        throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
      }
    }
    finally
    {
      th.unlock();
    }
    if (tY.containsKey(paramApi.zzanp()))
    {
      ConnectionResult localConnectionResult = tR.getConnectionResult(paramApi);
      if (localConnectionResult == null)
      {
        if (isResuming())
        {
          paramApi = ConnectionResult.qR;
          th.unlock();
          return paramApi;
        }
        Log.i("GoogleApiClientImpl", zzapv());
        Log.wtf("GoogleApiClientImpl", String.valueOf(paramApi.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
        paramApi = new ConnectionResult(8, null);
        th.unlock();
        return paramApi;
      }
      th.unlock();
      return localConnectionResult;
    }
    throw new IllegalArgumentException(String.valueOf(paramApi.getName()).concat(" was never registered with GoogleApiClient"));
  }
  
  public Context getContext()
  {
    return mContext;
  }
  
  public Looper getLooper()
  {
    return zzahv;
  }
  
  public int getSessionId()
  {
    return System.identityHashCode(this);
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi)
  {
    paramApi = (Api.zze)tY.get(paramApi.zzanp());
    return (paramApi != null) && (paramApi.isConnected());
  }
  
  public boolean isConnected()
  {
    return (tR != null) && (tR.isConnected());
  }
  
  public boolean isConnecting()
  {
    return (tR != null) && (tR.isConnecting());
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return tQ.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return tQ.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  boolean isResuming()
  {
    return tT;
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    tQ.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    tQ.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity)
  {
    zzb(new zzqn(paramFragmentActivity));
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    tQ.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    tQ.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  @NonNull
  public <C extends Api.zze> C zza(@NonNull Api.zzc<C> paramzzc)
  {
    paramzzc = (Api.zze)tY.get(paramzzc);
    zzab.zzb(paramzzc, "Appropriate Api was not requested.");
    return paramzzc;
  }
  
  public void zza(zzrc paramzzrc)
  {
    th.lock();
    try
    {
      if (ud == null) {
        ud = new HashSet();
      }
      ud.add(paramzzrc);
      return;
    }
    finally
    {
      th.unlock();
    }
  }
  
  public boolean zza(@NonNull Api<?> paramApi)
  {
    return tY.containsKey(paramApi.zzanp());
  }
  
  public boolean zza(zzqy paramzzqy)
  {
    return (tR != null) && (tR.zza(paramzzqy));
  }
  
  public void zzaoc()
  {
    if (tR != null) {
      tR.zzaoc();
    }
  }
  
  void zzaps()
  {
    if (isResuming()) {
      return;
    }
    tT = true;
    if (tX == null) {
      tX = rX.zza(mContext.getApplicationContext(), new zzb(this));
    }
    tW.sendMessageDelayed(tW.obtainMessage(1), tU);
    tW.sendMessageDelayed(tW.obtainMessage(2), tV);
  }
  
  boolean zzapt()
  {
    if (!isResuming()) {
      return false;
    }
    tT = false;
    tW.removeMessages(2);
    tW.removeMessages(1);
    if (tX != null)
    {
      tX.unregister();
      tX = null;
    }
    return true;
  }
  
  boolean zzapu()
  {
    boolean bool1 = false;
    th.lock();
    try
    {
      Set localSet = ud;
      if (localSet == null) {
        return false;
      }
      boolean bool2 = ud.isEmpty();
      if (!bool2) {
        bool1 = true;
      }
      return bool1;
    }
    finally
    {
      th.unlock();
    }
  }
  
  String zzapv()
  {
    StringWriter localStringWriter = new StringWriter();
    dump("", null, new PrintWriter(localStringWriter), null);
    return localStringWriter.toString();
  }
  
  <C extends Api.zze> C zzb(Api.zzc<?> paramzzc)
  {
    paramzzc = (Api.zze)tY.get(paramzzc);
    zzab.zzb(paramzzc, "Appropriate Api was not requested.");
    return paramzzc;
  }
  
  public void zzb(zzrc paramzzrc)
  {
    th.lock();
    for (;;)
    {
      try
      {
        if (ud == null)
        {
          Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
          return;
        }
        if (!ud.remove(paramzzrc))
        {
          Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
          continue;
        }
        if (zzapu()) {
          continue;
        }
      }
      finally
      {
        th.unlock();
      }
      tR.zzaoy();
    }
  }
  
  public <A extends Api.zzb, R extends Result, T extends zzpr.zza<R, A>> T zzc(@NonNull T paramT)
  {
    boolean bool;
    if (paramT.zzanp() != null) {
      bool = true;
    }
    for (;;)
    {
      zzab.zzb(bool, "This task can not be enqueued (it's probably a Batch or malformed)");
      bool = tY.containsKey(paramT.zzanp());
      String str;
      if (paramT.zzanw() != null)
      {
        str = paramT.zzanw().getName();
        label45:
        zzab.zzb(bool, String.valueOf(str).length() + 65 + "GoogleApiClient is not configured to use " + str + " required for this call.");
        th.lock();
      }
      try
      {
        if (tR == null)
        {
          tS.add(paramT);
          return paramT;
          bool = false;
          continue;
          str = "the API";
          break label45;
        }
        paramT = tR.zzc(paramT);
        return paramT;
      }
      finally
      {
        th.unlock();
      }
    }
  }
  
  public void zzc(int paramInt, boolean paramBoolean)
  {
    if ((paramInt == 1) && (!paramBoolean)) {
      zzaps();
    }
    ue.zzaqv();
    tQ.zzgb(paramInt);
    tQ.zzass();
    if (paramInt == 2) {
      zzapq();
    }
  }
  
  public <A extends Api.zzb, T extends zzpr.zza<? extends Result, A>> T zzd(@NonNull T paramT)
  {
    boolean bool;
    if (paramT.zzanp() != null)
    {
      bool = true;
      zzab.zzb(bool, "This task can not be executed (it's probably a Batch or malformed)");
      bool = tY.containsKey(paramT.zzanp());
      if (paramT.zzanw() == null) {
        break label129;
      }
    }
    label129:
    for (Object localObject = paramT.zzanw().getName();; localObject = "the API")
    {
      zzab.zzb(bool, String.valueOf(localObject).length() + 65 + "GoogleApiClient is not configured to use " + (String)localObject + " required for this call.");
      th.lock();
      try
      {
        if (tR != null) {
          break label136;
        }
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
      }
      finally
      {
        th.unlock();
      }
      bool = false;
      break;
    }
    label136:
    if (isResuming())
    {
      tS.add(paramT);
      while (!tS.isEmpty())
      {
        localObject = (zzpr.zza)tS.remove();
        ue.zzg((zzpr.zza)localObject);
        ((zzpr.zza)localObject).zzz(Status.si);
      }
      th.unlock();
      return paramT;
    }
    paramT = tR.zzd(paramT);
    th.unlock();
    return paramT;
  }
  
  public void zzd(ConnectionResult paramConnectionResult)
  {
    if (!rX.zzc(mContext, paramConnectionResult.getErrorCode())) {
      zzapt();
    }
    if (!isResuming())
    {
      tQ.zzm(paramConnectionResult);
      tQ.zzass();
    }
  }
  
  public void zzm(Bundle paramBundle)
  {
    while (!tS.isEmpty()) {
      zzd((zzpr.zza)tS.remove());
    }
    tQ.zzo(paramBundle);
  }
  
  public <L> zzqs<L> zzt(@NonNull L paramL)
  {
    th.lock();
    try
    {
      paramL = ua.zzb(paramL, zzahv);
      return paramL;
    }
    finally
    {
      th.unlock();
    }
  }
  
  final class zza
    extends Handler
  {
    zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        int i = what;
        Log.w("GoogleApiClientImpl", 31 + "Unknown message id: " + i);
        return;
      case 1: 
        zzqd.zzb(zzqd.this);
        return;
      }
      zzqd.zza(zzqd.this);
    }
  }
  
  static class zzb
    extends zzqj.zza
  {
    private WeakReference<zzqd> uk;
    
    zzb(zzqd paramzzqd)
    {
      uk = new WeakReference(paramzzqd);
    }
    
    public void zzaor()
    {
      zzqd localzzqd = (zzqd)uk.get();
      if (localzzqd == null) {
        return;
      }
      zzqd.zza(localzzqd);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzqd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */