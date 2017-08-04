package com.christophermcasey.uiconcepts.screen;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.Layout;
import com.christophermcasey.appcore.mortarflow.Screen;
import com.christophermcasey.appcore.mortarflow.scope.PerScreen;
import com.christophermcasey.uiconcepts.AppComponent;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.view.DrawerView;
import dagger.Subcomponent;
import mortar.MortarScope;

@Layout(R.layout.drawer_view)
public class DrawerScreen extends Screen {

  @PerScreen
  @Subcomponent
  public interface DrawerScreenComponent {
    void inject(DrawerView DrawerView);
  }

  @Nullable
  @Override
  protected Object createDaggerComponent(Resources resources, MortarScope parentScope) {
    return DaggerService //
        .<AppComponent>getDaggerComponent(parentScope) //
        .drawerScreenComponent();
  }
}
