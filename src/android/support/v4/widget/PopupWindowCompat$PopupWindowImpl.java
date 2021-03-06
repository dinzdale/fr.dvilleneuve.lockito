package android.support.v4.widget;

import android.view.View;
import android.widget.PopupWindow;

abstract interface PopupWindowCompat$PopupWindowImpl
{
  public abstract boolean getOverlapAnchor(PopupWindow paramPopupWindow);
  
  public abstract int getWindowLayoutType(PopupWindow paramPopupWindow);
  
  public abstract void setOverlapAnchor(PopupWindow paramPopupWindow, boolean paramBoolean);
  
  public abstract void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt);
  
  public abstract void showAsDropDown(PopupWindow paramPopupWindow, View paramView, int paramInt1, int paramInt2, int paramInt3);
}

/* Location:
 * Qualified Name:     android.support.v4.widget.PopupWindowCompat.PopupWindowImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */