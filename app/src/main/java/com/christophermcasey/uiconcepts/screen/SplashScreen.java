package com.christophermcasey.uiconcepts.screen;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import dagger.Subcomponent;
import flow.NotPersistent;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.AppComponent;
import com.christophermcasey.uiconcepts.presenter.SplashPresenter.SplashPresenterModule;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.Layout;
import com.christophermcasey.appcore.mortarflow.scope.PerScreen;
import com.christophermcasey.appcore.mortarflow.Screen;
import com.christophermcasey.uiconcepts.view.SplashView;
import mortar.MortarScope;

@NotPersistent
@Layout(R.layout.splash_view)
public class SplashScreen extends Screen {

  @PerScreen
  @Subcomponent(modules = SplashPresenterModule.class)
  public interface SplashScreenComponent {
    void inject(SplashView SplashView);
  }

  @Nullable
  @Override
  protected Object createDaggerComponent(Resources resources, MortarScope parentScope) {
    return DaggerService //
        .<AppComponent>getDaggerComponent(parentScope) //
        .splashScreenComponent();
  }
}
