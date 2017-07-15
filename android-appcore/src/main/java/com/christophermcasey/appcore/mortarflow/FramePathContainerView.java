package com.christophermcasey.appcore.mortarflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import flow.Flow;
import flow.path.PathContainerView;
import flow.path.PathContextFactory;
import java.util.Set;
import com.christophermcasey.appcore.R;
import com.christophermcasey.appcore.mortarflow.android.ActivityResultSupport;
import com.christophermcasey.appcore.mortarflow.android.BackSupport;
import com.christophermcasey.appcore.mortarflow.android.HandlesActivityResult;
import com.christophermcasey.appcore.mortarflow.android.HandlesBack;
import com.christophermcasey.appcore.mortarflow.android.HandlesOptionsItemSelected;
import com.christophermcasey.appcore.mortarflow.android.HandlesPauseResume;
import com.christophermcasey.appcore.mortarflow.android.HandlesSearchSuggestions;
import com.christophermcasey.appcore.mortarflow.android.OptionsItemSelectedSupport;
import com.christophermcasey.appcore.mortarflow.android.PauseResumeSupport;
import com.christophermcasey.appcore.mortarflow.android.SearchSuggestionsSupport;
import com.christophermcasey.appcore.screenview.HandlesTransientParams;
import com.christophermcasey.appcore.segue.Segues;

/** A FrameLayout that can show screens for a {@link Flow}. */
public class FramePathContainerView extends FrameLayout
    implements PathContainerView, HandlesBack, HandlesActivityResult, HandlesOptionsItemSelected,
    HandlesSearchSuggestions, HandlesPauseResume, HandlesTransientParams {
  private SimplePathContainer container;
  private boolean disabled;

  private HandlesTransientParams transientParamHandler;

  public FramePathContainerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void init(PathContextFactory contextFactory, Segues segues) {
    if (this.container != null) throw new IllegalStateException("already initialized.");
    this.container = new SimplePathContainer(R.id.screen_switcher_tag,
        flow.path.Path.contextFactory(contextFactory), segues);
  }

  @Override
  public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
    checkInit();
    return !disabled && super.dispatchTouchEvent(ev);
  }

  @Override
  public ViewGroup getContainerView() {
    checkInit();
    return this;
  }

  @Override
  public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
    checkInit();
    disabled = true;
    container.executeTraversal(this, traversal, new Flow.TraversalCallback() {
      @Override
      public void onTraversalCompleted() {
        callback.onTraversalCompleted();
        disabled = false;
      }
    });
  }

  @Override
  public boolean onBackPressed() {
    checkInit();
    return BackSupport.onBackPressed(getCurrentChild());
  }

  @Override
  public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
    checkInit();
    return ActivityResultSupport.onActivityResult(getCurrentChild(), requestCode, resultCode, data);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    checkInit();
    return OptionsItemSelectedSupport.onOptionsItemSelected(getCurrentChild(), item);
  }

  @Override
  public boolean onSearchQuerySubmit(@NonNull String query) {
    checkInit();
    return SearchSuggestionsSupport.onSearchQuerySubmit(getCurrentChild(), query);
  }

  @Override
  public boolean onSearchQueryChange(@NonNull String query, @NonNull Set<String> outSet) {
    checkInit();
    return SearchSuggestionsSupport.onSearchQueryChange(getCurrentChild(), query, outSet);
  }

  @Override
  public void onPause() {
    checkInit();
    PauseResumeSupport.onPause(getCurrentChild());
  }

  @Override
  public void onResume() {
    checkInit();
    PauseResumeSupport.onResume(getCurrentChild());
  }

  @Override
  public ViewGroup getCurrentChild() {
    checkInit();
    return (ViewGroup) getContainerView().getChildAt(0);
  }

  private void checkInit() {
    if (container == null) throw new IllegalStateException("needs init.");
  }

  public void setTransientParamHandler(HandlesTransientParams transientParamHandler) {
    this.transientParamHandler = transientParamHandler;
  }

  @Nullable
  @Override
  public Bundle onProvideTransientParams(@NonNull View view, Bundle params) {
    if (transientParamHandler != null) {
      return transientParamHandler.onProvideTransientParams(view, params);
    }
    return null;
  }

  @Override
  public void onReceiveTransientParams(@Nullable Bundle params) {
    if (transientParamHandler != null) {
      transientParamHandler.onReceiveTransientParams(params);
    }
  }
}
