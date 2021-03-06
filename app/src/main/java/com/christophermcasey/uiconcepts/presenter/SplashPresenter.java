package com.christophermcasey.uiconcepts.presenter;

import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import flow.Flow;
import flow.History;
import com.christophermcasey.appcore.mortarflow.presenter.ActionBarPresenter;
import com.christophermcasey.appcore.mortarflow.scope.PerScreen;
import com.christophermcasey.appcore.screenview.ScreenViewPresenter;
import com.christophermcasey.uiconcepts.screen.MainScreen;
import com.christophermcasey.uiconcepts.view.SplashView;

public class SplashPresenter extends ScreenViewPresenter<SplashView> {

  @Module
  public static class SplashPresenterModule {
    @Provides
    @PerScreen
    SplashPresenter provideSplashPresenter(ActionBarPresenter actionBarPresenter) {
      return new SplashPresenter(actionBarPresenter);
    }
  }

  private final ActionBarPresenter actionBarPresenter;

  private SplashPresenter(ActionBarPresenter actionBarPresenter) {
    this.actionBarPresenter = actionBarPresenter;
  }

  @Override
  protected void onAttachedToWindow(@NonNull SplashView view) {
    actionBarPresenter.startConfiguration() //
        .hide() //
        .commit();

    view.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (hasView()) {
          Flow.get(getView()).setHistory(History.single(new MainScreen()), Flow.Direction.FORWARD);
        }
      }
    }, 2000);
  }
}
