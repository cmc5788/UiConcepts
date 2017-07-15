package com.christophermcasey.appcore.view;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;

public class TextInputLayoutTF extends TextInputLayout implements HasTypeface {

  private final HasTypefaceInitImpl initializer = new HasTypefaceInitImpl();

  public TextInputLayoutTF(Context context) {
    super(context);
    initializer.init(this, context, null);
  }

  public TextInputLayoutTF(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializer.init(this, context, attrs);
  }

  public TextInputLayoutTF(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializer.init(this, context, attrs);
  }
}
