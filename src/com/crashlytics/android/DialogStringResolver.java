package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.PromptSettingsData;

class DialogStringResolver
{
  private static final String PROMPT_MESSAGE_RES_NAME = "com.crashlytics.CrashSubmissionPromptMessage";
  private static final String PROMPT_TITLE_RES_NAME = "com.crashlytics.CrashSubmissionPromptTitle";
  private static final String SUBMISSION_ALWAYS_SEND_RES_NAME = "com.crashlytics.CrashSubmissionAlwaysSendTitle";
  private static final String SUBMISSION_CANCEL_RES_NAME = "com.crashlytics.CrashSubmissionCancelTitle";
  private static final String SUBMISSION_SEND_RES_NAME = "com.crashlytics.CrashSubmissionSendTitle";
  private final Context context;
  private final PromptSettingsData promptData;
  
  public DialogStringResolver(Context paramContext, PromptSettingsData paramPromptSettingsData)
  {
    context = paramContext;
    promptData = paramPromptSettingsData;
  }
  
  private boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  private String resourceOrFallbackValue(String paramString1, String paramString2)
  {
    return stringOrFallback(CommonUtils.getStringsFileValue(context, paramString1), paramString2);
  }
  
  private String stringOrFallback(String paramString1, String paramString2)
  {
    if (isNullOrEmpty(paramString1)) {
      return paramString2;
    }
    return paramString1;
  }
  
  public String getAlwaysSendButtonTitle()
  {
    return resourceOrFallbackValue("com.crashlytics.CrashSubmissionAlwaysSendTitle", promptData.alwaysSendButtonTitle);
  }
  
  public String getCancelButtonTitle()
  {
    return resourceOrFallbackValue("com.crashlytics.CrashSubmissionCancelTitle", promptData.cancelButtonTitle);
  }
  
  public String getMessage()
  {
    return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptMessage", promptData.message);
  }
  
  public String getSendButtonTitle()
  {
    return resourceOrFallbackValue("com.crashlytics.CrashSubmissionSendTitle", promptData.sendButtonTitle);
  }
  
  public String getTitle()
  {
    return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptTitle", promptData.title);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.DialogStringResolver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */