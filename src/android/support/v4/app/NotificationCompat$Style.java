package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;

public abstract class NotificationCompat$Style
{
  CharSequence mBigContentTitle;
  NotificationCompat.Builder mBuilder;
  CharSequence mSummaryText;
  boolean mSummaryTextSet = false;
  
  public void addCompatExtras(Bundle paramBundle) {}
  
  public Notification build()
  {
    Notification localNotification = null;
    if (mBuilder != null) {
      localNotification = mBuilder.build();
    }
    return localNotification;
  }
  
  protected void restoreFromCompatExtras(Bundle paramBundle) {}
  
  public void setBuilder(NotificationCompat.Builder paramBuilder)
  {
    if (mBuilder != paramBuilder)
    {
      mBuilder = paramBuilder;
      if (mBuilder != null) {
        mBuilder.setStyle(this);
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.Style
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */