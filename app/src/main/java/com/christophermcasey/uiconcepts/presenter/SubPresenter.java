package com.christophermcasey.uiconcepts.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import com.christophermcasey.appcore.mortarflow.popup.Confirmation;
import com.christophermcasey.appcore.mortarflow.presenter.ActionBarPresenter;
import com.christophermcasey.appcore.mortarflow.presenter.DrawerPresenter;
import com.christophermcasey.appcore.mortarflow.scope.PerSubScreen;
import com.christophermcasey.appcore.screenview.RxScreenViewPresenter;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.screen.SubScreen;
import com.christophermcasey.uiconcepts.view.SubView;
import dagger.Module;
import dagger.Provides;
import flow.Flow;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import mortar.PopupPresenter;

import static com.christophermcasey.appcore.ViewGroupLoader.loadIntoViewGroup;
import static java.lang.Boolean.TRUE;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SubPresenter extends RxScreenViewPresenter<SubView> {

  @Module
  public static class SubPresenterModule {
    @Provides
    @PerSubScreen
    SubPresenter provideSubPresenter(String subScreenText, DrawerPresenter drawerPresenter,
        ActionBarPresenter actionBarPresenter) {
      return new SubPresenter(subScreenText, drawerPresenter, actionBarPresenter);
    }
  }

  private final String subScreenText;
  private final DrawerPresenter drawerPresenter;
  private final AppActionBarPresenter actionBarPresenter;
  private final PopupPresenter<Confirmation, Boolean> confirmer;

  private SubPresenter(String subScreenText, DrawerPresenter drawerPresenter,
      ActionBarPresenter actionBarPresenter) {
    this.subScreenText = subScreenText;
    this.drawerPresenter = drawerPresenter;
    this.actionBarPresenter = (AppActionBarPresenter) actionBarPresenter;
    this.confirmer = new PopupPresenter<Confirmation, Boolean>() {
      @Override
      protected void onPopupResult(Boolean confirmed) {
        if (confirmed && SubPresenter.this.hasView()) {
          toastShort(String.format("confirmed from %s!", SubPresenter.this.subScreenText));
        }
      }
    };
  }

  @Override
  protected void onFinishInflate(@NonNull SubView view) {
    super.onFinishInflate(view);

    if (subScreenText.equals("DEEPLY")) {
      view.hideNavvy();
    }

    view.setSubScreenText(subScreenText);
  }

  @Override
  protected Observable<?> autoSub1() {

    return onAttachedToWindow.flatMap(new Function<SubView, ObservableSource<?>>() {
      @Override
      public ObservableSource<?> apply(@NonNull SubView view) {

        return Observable.just(TRUE)
            .delay(3, SECONDS)
            .compose(loadIntoViewGroup(view.getRoot(), R.layout.loading_layout))
            .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
              @Override
              public ObservableSource<?> apply(@NonNull Observable<Object> o) {
                return o.delay(3, SECONDS);
              }
            })
            .doOnDispose(new Action() {
              @Override
              public void run() {
                Log.e("TESTO", "!!!!!!!!");
              }
            })
            .takeUntil(onDetachedFromWindow);
      }
    });
  }

  Disposable loadSomeData = onAttachedToWindow //
      .flatMap(new Function<SubView, ObservableSource<?>>() {
        @Override
        public ObservableSource<?> apply(@NonNull SubView view) {

          return Observable.just(TRUE)
              .delay(3, SECONDS)
              .compose(loadIntoViewGroup(view.getRoot(), R.layout.loading_layout))
              .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(@NonNull Observable<Object> o) {
                  return o.delay(3, SECONDS);
                }
              })
              .doOnDispose(new Action() {
                @Override
                public void run() {
                  Log.e("TESTO", "!!!!!!!!");
                }
              })
              .takeUntil(onDetachedFromWindow);
        }
      }).subscribe();

  Disposable configureActionBar = onAttachedToWindow //
      .doOnNext(new Consumer<SubView>() {
        @Override
        public void accept(@NonNull SubView view) {
          if (subScreenText.equals("DEEPLY")) {
            actionBarPresenter.startConfiguration() //
                .up() //
                .custom(R.layout.ab_custom_view) //
                .commit();
          }
        }
      }).subscribe();

  @Override
  protected void onAttachedToWindow(@NonNull SubView view) {
    super.onAttachedToWindow(view);

    if (subScreenText.equals("DEEPLY")) {
      actionBarPresenter.startConfiguration() //
          .up() //
          .custom(R.layout.ab_custom_view) //
          .commit();
    }

    confirmer.takeView(getView().getConfirmerPopup());
  }

  @Override
  protected void onDetachedFromWindow(@NonNull SubView view) {
    super.onDetachedFromWindow(view);

    confirmer.dropView(view.getConfirmerPopup());
  }

  public void clickShowy() {
    drawerPresenter.openDrawer();
  }

  public void clickPoppy() {
    confirmer.show(new Confirmation("Title", "Body", "Confirm", "Cancel"));
  }

  public void clickNavvy() {
    Flow.get(getView()).set(new SubScreen("DEEPLY"));
  }
}
