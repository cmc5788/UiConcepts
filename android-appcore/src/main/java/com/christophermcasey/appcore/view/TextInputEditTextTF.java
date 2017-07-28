package com.christophermcasey.appcore.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

public class TextInputEditTextTF extends TextInputEditText implements HasTypeface {

  private final HasTypefaceInitImpl initializer = new HasTypefaceInitImpl();

  public TextInputEditTextTF(Context context) {
    super(context);
    initializer.init(this, context, null);
  }

  public TextInputEditTextTF(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializer.init(this, context, null);
  }

  public TextInputEditTextTF(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializer.init(this, context, null);
  }

  @Override
  public void setSelection(int index) {
    try {
      super.setSelection(index);
    } catch (Exception ignored) {
      // stop older android versions from crashing on certain accessibility actions
    }
  }

  @Override
  public void setSelection(int start, int stop) {
    try {
      super.setSelection(start, stop);
    } catch (Exception ignored) {
      // stop older android versions from crashing on certain accessibility actions
    }
  }
}
