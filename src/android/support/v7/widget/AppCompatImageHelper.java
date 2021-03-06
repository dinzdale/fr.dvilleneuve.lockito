package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AppCompatImageHelper
{
  private final AppCompatDrawableManager mDrawableManager;
  private final ImageView mView;
  
  public AppCompatImageHelper(ImageView paramImageView, AppCompatDrawableManager paramAppCompatDrawableManager)
  {
    mView = paramImageView;
    mDrawableManager = paramAppCompatDrawableManager;
  }
  
  boolean hasOverlappingRendering()
  {
    Drawable localDrawable = mView.getBackground();
    return (Build.VERSION.SDK_INT < 21) || (!(localDrawable instanceof RippleDrawable));
  }
  
  public void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    Object localObject2 = null;
    AttributeSet localAttributeSet = null;
    Object localObject1 = localObject2;
    try
    {
      Drawable localDrawable2 = mView.getDrawable();
      Drawable localDrawable1 = localDrawable2;
      if (localDrawable2 == null)
      {
        localObject1 = localObject2;
        paramAttributeSet = TintTypedArray.obtainStyledAttributes(mView.getContext(), paramAttributeSet, R.styleable.AppCompatImageView, paramInt, 0);
        localObject1 = paramAttributeSet;
        paramInt = paramAttributeSet.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
        localAttributeSet = paramAttributeSet;
        localDrawable1 = localDrawable2;
        if (paramInt != -1)
        {
          localObject1 = paramAttributeSet;
          localDrawable2 = mDrawableManager.getDrawable(mView.getContext(), paramInt);
          localAttributeSet = paramAttributeSet;
          localDrawable1 = localDrawable2;
          if (localDrawable2 != null)
          {
            localObject1 = paramAttributeSet;
            mView.setImageDrawable(localDrawable2);
            localDrawable1 = localDrawable2;
            localAttributeSet = paramAttributeSet;
          }
        }
      }
      if (localDrawable1 != null)
      {
        localObject1 = localAttributeSet;
        DrawableUtils.fixDrawable(localDrawable1);
      }
      if (localAttributeSet != null) {
        localAttributeSet.recycle();
      }
      return;
    }
    finally
    {
      if (localObject1 != null) {
        ((TintTypedArray)localObject1).recycle();
      }
    }
  }
  
  public void setImageResource(int paramInt)
  {
    if (paramInt != 0)
    {
      if (mDrawableManager != null) {}
      for (Drawable localDrawable = mDrawableManager.getDrawable(mView.getContext(), paramInt);; localDrawable = ContextCompat.getDrawable(mView.getContext(), paramInt))
      {
        if (localDrawable != null) {
          DrawableUtils.fixDrawable(localDrawable);
        }
        mView.setImageDrawable(localDrawable);
        return;
      }
    }
    mView.setImageDrawable(null);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatImageHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */