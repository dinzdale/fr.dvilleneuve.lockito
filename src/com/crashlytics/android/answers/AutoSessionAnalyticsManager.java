package com.crashlytics.android.answers;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.concurrent.ScheduledExecutorService;

@TargetApi(14)
class AutoSessionAnalyticsManager
  extends SessionAnalyticsManager
{
  private static final String EXECUTOR_SERVICE = "Crashlytics Trace Manager";
  private final Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
  {
    public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
    {
      onCreate(paramAnonymousActivity);
    }
    
    public void onActivityDestroyed(Activity paramAnonymousActivity)
    {
      onDestroy(paramAnonymousActivity);
    }
    
    public void onActivityPaused(Activity paramAnonymousActivity)
    {
      onPause(paramAnonymousActivity);
    }
    
    public void onActivityResumed(Activity paramAnonymousActivity)
    {
      onResume(paramAnonymousActivity);
    }
    
    public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
    {
      onSaveInstanceState(paramAnonymousActivity);
    }
    
    public void onActivityStarted(Activity paramAnonymousActivity)
    {
      onStart(paramAnonymousActivity);
    }
    
    public void onActivityStopped(Activity paramAnonymousActivity)
    {
      onStop(paramAnonymousActivity);
    }
  };
  private final Application application;
  
  AutoSessionAnalyticsManager(SessionEventMetadata paramSessionEventMetadata, SessionEventsHandler paramSessionEventsHandler, Application paramApplication)
  {
    super(paramSessionEventMetadata, paramSessionEventsHandler);
    application = paramApplication;
    CommonUtils.logControlled(Answers.getInstance().getContext(), "Registering activity lifecycle callbacks for session analytics.");
    paramApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
  }
  
  public static AutoSessionAnalyticsManager build(Application paramApplication, SessionEventMetadata paramSessionEventMetadata, SessionAnalyticsFilesManager paramSessionAnalyticsFilesManager, HttpRequestFactory paramHttpRequestFactory)
  {
    ScheduledExecutorService localScheduledExecutorService = ExecutorUtils.buildSingleThreadScheduledExecutorService("Crashlytics Trace Manager");
    return new AutoSessionAnalyticsManager(paramSessionEventMetadata, new SessionEventsHandler(paramApplication, new EnabledSessionAnalyticsManagerStrategy(paramApplication, localScheduledExecutorService, paramSessionAnalyticsFilesManager, paramHttpRequestFactory), paramSessionAnalyticsFilesManager, localScheduledExecutorService), paramApplication);
  }
  
  public void disable()
  {
    CommonUtils.logControlled(Answers.getInstance().getContext(), "Unregistering activity lifecycle callbacks for session analytics");
    application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    super.disable();
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.answers.AutoSessionAnalyticsManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */