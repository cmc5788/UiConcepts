package com.christophermcasey.uiconcepts.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import javax.inject.Inject;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.presenter.DrawerPresenter;
import com.christophermcasey.appcore.screenview.DrawerScreenView;
import com.christophermcasey.uiconcepts.screen.DrawerScreen.DrawerScreenComponent;

/**
 * Created by christophercasey on 9/3/15.
 */
public class DrawerView extends DrawerScreenView {

  @Inject DrawerPresenter presenter;

  public DrawerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Nullable
  @Override
  public DrawerPresenter getPresenter() {
    return presenter;
  }

  @Override
  public void onCreate(@NonNull Context context) {
    super.onCreate(context);
    DaggerService //
        .<DrawerScreenComponent>getDaggerComponent(context) //
        .inject(this);
  }
}
