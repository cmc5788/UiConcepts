package com.christophermcasey.uiconcepts.presenter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import dagger.Module;
import dagger.Provides;
import com.christophermcasey.appcore.mortarflow.presenter.ActionBarPresenter;
import com.christophermcasey.appcore.mortarflow.scope.PerScreen;
import com.christophermcasey.appcore.screenview.ScreenViewPresenter;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.adapter.MainPagerAdapter;
import com.christophermcasey.uiconcepts.view.MainView;

public class MainPresenter extends ScreenViewPresenter<MainView> {

  @Module
  public static class MainPresenterModule {
    @Provides
    @PerScreen
    MainPresenter provideMainPresenter(ActionBarPresenter actionBarPresenter,
        MainPagerAdapter pagerAdapter) {
      return new MainPresenter(actionBarPresenter, pagerAdapter);
    }
  }

  private final AppActionBarPresenter actionBarPresenter;
  private final MainPagerAdapter pagerAdapter;

  private MainPresenter(ActionBarPresenter actionBarPresenter, MainPagerAdapter pagerAdapter) {
    this.actionBarPresenter = (AppActionBarPresenter) actionBarPresenter;
    this.pagerAdapter = pagerAdapter;
  }

  @Override
  protected void onAttachedToWindow(@NonNull MainView view) {
    actionBarPresenter.startConfiguration() //
        .homeTitle() //
        .color(Color.BLUE) //
        .menus(R.menu.test_menu) //
        .commit();
    view.setPagerAdapter(pagerAdapter);
  }

  @Override
  protected void onDetachedFromWindow(@NonNull MainView view) {
    view.clearPagerAdapter();
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MainView view, MenuItem item) {
    if (item.getItemId() == R.id.action_test) {
      toastShort("got test action!");
      return true;
    }
    return false;
  }
}
