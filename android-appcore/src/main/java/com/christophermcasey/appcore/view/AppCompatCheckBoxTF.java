package com.christophermcasey.appcore.view;

import android.content.Context;
import android.util.AttributeSet;

public class AppCompatCheckBoxTF extends android.support.v7.widget.AppCompatCheckBox
    implements HasTypeface {

  private final HasTypefaceInitImpl initializer = new HasTypefaceInitImpl();

  public AppCompatCheckBoxTF(Context context) {
    super(context);
    initializer.init(this, context, null);
  }

  public AppCompatCheckBoxTF(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializer.init(this, context, attrs);
  }

  public AppCompatCheckBoxTF(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializer.init(this, context, attrs);
  }
}
