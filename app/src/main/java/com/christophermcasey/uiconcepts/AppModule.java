package com.christophermcasey.uiconcepts;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import com.christophermcasey.appcore.mortarflow.FramePathContainerView;
import com.christophermcasey.appcore.segue.Segues;
import com.christophermcasey.uiconcepts.view.MainView;
import com.christophermcasey.uiconcepts.view.SplashView;
import com.christophermcasey.uiconcepts.view.SubView;

import static com.christophermcasey.appcore.segue.SegueFactories.SCALE_DOWN_AND_FADE;
import static com.christophermcasey.appcore.segue.SegueFactories.SCALE_UP_AND_FADE;
import static com.christophermcasey.appcore.segue.SegueFactories.VERTICAL_UP_SLIDE;

/**
 * Created by christophercasey on 9/3/15.
 */
@Module
public class AppModule {

  @Provides
  @Singleton
  Segues provideSegues() {
    Segues segues = new Segues();
    segues.putFactory(FramePathContainerView.class, SplashView.class, MainView.class,
        VERTICAL_UP_SLIDE);
    segues.putFactory(FramePathContainerView.class, MainView.class, SubView.class,
        SCALE_DOWN_AND_FADE);
    segues.putFactory(FramePathContainerView.class, SubView.class, MainView.class, //
        SCALE_UP_AND_FADE);
    return segues;
  }
}
