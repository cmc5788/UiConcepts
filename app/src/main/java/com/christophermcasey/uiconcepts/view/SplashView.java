package com.christophermcasey.uiconcepts.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import javax.inject.Inject;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.screenview.LinearLayoutScreenView;
import com.christophermcasey.uiconcepts.presenter.SplashPresenter;
import com.christophermcasey.uiconcepts.screen.SplashScreen;

/**
 * Created by christophercasey on 9/3/15.
 */
public class SplashView extends LinearLayoutScreenView<SplashView, SplashPresenter> {

  @Inject SplashPresenter presenter;

  public SplashView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Nullable
  @Override
  public SplashPresenter getPresenter() {
    return presenter;
  }

  @Override
  public void onCreate(@NonNull Context context) {
    super.onCreate(context);
    DaggerService //
        .<SplashScreen.SplashScreenComponent>getDaggerComponent(context) //
        .inject(this);
  }
}
