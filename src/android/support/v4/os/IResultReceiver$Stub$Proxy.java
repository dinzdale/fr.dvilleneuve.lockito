package android.support.v4.os;

import android.os.IBinder;

class IResultReceiver$Stub$Proxy
  implements IResultReceiver
{
  private IBinder mRemote;
  
  IResultReceiver$Stub$Proxy(IBinder paramIBinder)
  {
    mRemote = paramIBinder;
  }
  
  public IBinder asBinder()
  {
    return mRemote;
  }
  
  public String getInterfaceDescriptor()
  {
    return "android.support.v4.os.IResultReceiver";
  }
  
  /* Error */
  public void send(int paramInt, android.os.Bundle paramBundle)
    throws android.os.RemoteException
  {
    // Byte code:
    //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: aload_3
    //   5: ldc 26
    //   7: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   10: aload_3
    //   11: iload_1
    //   12: invokevirtual 44	android/os/Parcel:writeInt	(I)V
    //   15: aload_2
    //   16: ifnull +33 -> 49
    //   19: aload_3
    //   20: iconst_1
    //   21: invokevirtual 44	android/os/Parcel:writeInt	(I)V
    //   24: aload_2
    //   25: aload_3
    //   26: iconst_0
    //   27: invokevirtual 50	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
    //   30: aload_0
    //   31: getfield 19	android/support/v4/os/IResultReceiver$Stub$Proxy:mRemote	Landroid/os/IBinder;
    //   34: iconst_1
    //   35: aload_3
    //   36: aconst_null
    //   37: iconst_1
    //   38: invokeinterface 56 5 0
    //   43: pop
    //   44: aload_3
    //   45: invokevirtual 59	android/os/Parcel:recycle	()V
    //   48: return
    //   49: aload_3
    //   50: iconst_0
    //   51: invokevirtual 44	android/os/Parcel:writeInt	(I)V
    //   54: goto -24 -> 30
    //   57: astore_2
    //   58: aload_3
    //   59: invokevirtual 59	android/os/Parcel:recycle	()V
    //   62: aload_2
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	Proxy
    //   0	64	1	paramInt	int
    //   0	64	2	paramBundle	android.os.Bundle
    //   3	56	3	localParcel	android.os.Parcel
    // Exception table:
    //   from	to	target	type
    //   4	15	57	finally
    //   19	30	57	finally
    //   30	44	57	finally
    //   49	54	57	finally
  }
}

/* Location:
 * Qualified Name:     android.support.v4.os.IResultReceiver.Stub.Proxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */