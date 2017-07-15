package com.christophermcasey.uiconcepts;

import dagger.Component;
import javax.inject.Singleton;
import com.christophermcasey.appcore.mortarflow.MortarContextFactory.MortarContextFactoryModule;
import com.christophermcasey.appcore.mortarflow.presenter.DrawerPresenter.DrawerPresenterModule;
import com.christophermcasey.appcore.parcel.GsonParceler.GsonModule;
import com.christophermcasey.appcore.parcel.GsonParceler.GsonParcelerModule;
import com.christophermcasey.uiconcepts.presenter.AppActionBarPresenter.AppActionBarPresenterModule;
import com.christophermcasey.uiconcepts.screen.DrawerScreen;
import com.christophermcasey.uiconcepts.screen.MainScreen;
import com.christophermcasey.uiconcepts.screen.SplashScreen;
import com.christophermcasey.uiconcepts.screen.SubScreen;

@Singleton
@Component(modules = {
    AppModule.class, //
    MortarContextFactoryModule.class, //
    DrawerPresenterModule.class, //
    //ActionBarPresenterModule.class, //
    AppActionBarPresenterModule.class, //
    GsonModule.class,
    GsonParcelerModule.class,
})
public interface AppComponent {
  void inject(MainActivity mainActivity);

  SplashScreen.SplashScreenComponent splashScreenComponent();

  DrawerScreen.DrawerScreenComponent drawerScreenComponent();

  MainScreen.MainScreenComponent mainScreenComponent();

  SubScreen.SubScreenComponent subScreenComponent(SubScreen.SubScreenModule mod);
}
