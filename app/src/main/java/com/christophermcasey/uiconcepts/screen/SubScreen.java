package com.christophermcasey.uiconcepts.screen;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.Layout;
import com.christophermcasey.appcore.mortarflow.Screen;
import com.christophermcasey.appcore.mortarflow.scope.PerSubScreen;
import com.christophermcasey.uiconcepts.AppComponent;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.presenter.SubPresenter.SubPresenterModule;
import com.christophermcasey.uiconcepts.screen.MainScreen.MainScreenComponent;
import com.christophermcasey.uiconcepts.view.SubView;
import mortar.MortarScope;

@Layout(R.layout.sub_view)
public class SubScreen extends Screen {

  private final String screenText;

  public SubScreen(String screenText) {
    this.screenText = screenText;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SubScreen subScreen = (SubScreen) o;

    return !(screenText != null
        ? !screenText.equals(subScreen.screenText)
        : subScreen.screenText != null);
  }

  @Override
  public int hashCode() {
    return screenText != null
        ? screenText.hashCode()
        : 0;
  }

  @Module
  public static class SubScreenModule {
    private final SubScreen subScreen;

    SubScreenModule(SubScreen subScreen) {
      this.subScreen = subScreen;
    }

    @Provides
    @PerSubScreen
    String provideSubScreenText() {
      return subScreen.screenText;
    }
  }

  @PerSubScreen
  @Subcomponent(modules = { SubScreenModule.class, SubPresenterModule.class })
  public interface SubScreenComponent {
    void inject(SubView subView);
  }

  @Nullable
  @Override
  protected Object createDaggerComponent(Resources resources, MortarScope parentScope) {
    Object parentComponent = DaggerService.getDaggerComponent(parentScope);
    if (parentComponent instanceof MainScreenComponent) {
      return ((MainScreenComponent) parentComponent).plus(new SubScreenModule(this));
    }
    if (parentComponent instanceof AppComponent) {
      return ((AppComponent) parentComponent).subScreenComponent(new SubScreenModule(this));
    }
    throw new RuntimeException(
        "unknown parent component type " + parentComponent.getClass().getName());
  }

  @NonNull
  @Override
  protected String getScopeName() {
    return super.getScopeName() + screenText;
  }
}
