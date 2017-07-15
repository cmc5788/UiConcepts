package com.christophermcasey.uiconcepts.screen;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import dagger.Subcomponent;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.adapter.MainPagerAdapter.MainPagerAdapterModule;
import com.christophermcasey.uiconcepts.AppComponent;
import com.christophermcasey.uiconcepts.presenter.MainPresenter.MainPresenterModule;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.Layout;
import com.christophermcasey.appcore.mortarflow.scope.PerScreen;
import com.christophermcasey.appcore.mortarflow.Screen;
import com.christophermcasey.uiconcepts.view.MainView;
import mortar.MortarScope;

@Layout(R.layout.main_view)
public class MainScreen extends Screen {

  @Override
  public boolean equals(Object o) {
    return this == o || !(o == null || getClass() != o.getClass());
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @PerScreen
  @Subcomponent(modules = { MainPresenterModule.class, MainPagerAdapterModule.class })
  public interface MainScreenComponent {
    void inject(MainView mainView);

    SubScreen.SubScreenComponent plus(SubScreen.SubScreenModule module);
  }

  @Nullable
  @Override
  protected Object createDaggerComponent(Resources resources, MortarScope parentScope) {
    return DaggerService //
        .<AppComponent>getDaggerComponent(parentScope) //
        .mainScreenComponent();
  }
}
