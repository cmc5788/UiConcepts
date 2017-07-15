package com.christophermcasey.uiconcepts.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.christophermcasey.appcore.dagger.DaggerService;
import com.christophermcasey.appcore.mortarflow.popup.ConfirmerPopup;
import com.christophermcasey.appcore.screenview.LinearLayoutScreenView;
import com.christophermcasey.uiconcepts.R;
import com.christophermcasey.uiconcepts.presenter.SubPresenter;
import com.christophermcasey.uiconcepts.screen.SubScreen.SubScreenComponent;
import javax.inject.Inject;

/**
 * Created by christophercasey on 9/3/15.
 */
public class SubView extends LinearLayoutScreenView<SubView, SubPresenter> {

  @Inject SubPresenter presenter;

  @Bind(R.id.sub_showy) TextView showy;
  @Bind(R.id.sub_navvy) TextView navvy;
  @Bind(R.id.sub_view) ViewGroup root;

  private final ConfirmerPopup confirmerPopup;

  public SubView(Context context, AttributeSet attrs) {
    super(context, attrs);
    confirmerPopup = new ConfirmerPopup(context);
  }

  @Override
  public boolean isButtery() {
    return true;
  }

  @Nullable
  @Override
  public SubPresenter getPresenter() {
    return presenter;
  }

  @Override
  public void onCreate(@NonNull Context context) {
    super.onCreate(context);
    DaggerService //
        .<SubScreenComponent>getDaggerComponent(context) //
        .inject(this);
  }

  @OnClick(R.id.sub_showy)
  void showy() {
    presenter.clickShowy();
  }

  @OnClick(R.id.sub_poppy)
  void poppy() {
    presenter.clickPoppy();
  }

  @OnClick(R.id.sub_navvy)
  void navvy() {
    presenter.clickNavvy();
  }

  public void setSubScreenText(String subScreenText) {
    showy.setText(subScreenText);
  }

  public ConfirmerPopup getConfirmerPopup() {
    return confirmerPopup;
  }

  public void hideNavvy() {
    navvy.setVisibility(GONE);
  }

  public ViewGroup getRoot() {
    return root;
  }
}
