package com.christophermcasey.appcore.screenview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

@SuppressWarnings({ "WeakerAccess", "unused" })
public class RxScreenViewPresenter<V extends View & ScreenView> extends ScreenViewPresenter<V> {

  private final Subject<V> _onCreate = PublishSubject.create();
  private final Subject<V> _onFinishInflate = PublishSubject.create();
  private final Subject<OnProvideTransientParamsParams> _onProvideTransientParams =
      PublishSubject.create();
  private final Subject<OnReceiveTransientParamsParams> _onReceiveTransientParams =
      PublishSubject.create();
  private final Subject<V> _onAttachedToWindow = PublishSubject.create();
  private final Subject<V> _onDetachedFromWindow = PublishSubject.create();
  private final Subject<OnVisibilityChangedParams> _onVisibilityChanged = PublishSubject.create();
  private final Subject<OnSizeChangedParams> _onSizeChanged = PublishSubject.create();
  private final Subject<SaveRestoreHierarchyStateParams> _restoreHierarchyState =
      PublishSubject.create();
  private final Subject<SaveRestoreHierarchyStateParams> _saveHierarchyState =
      PublishSubject.create();
  private final Subject<OnSaveRestoreInstanceStateParams> _onSaveInstanceState =
      PublishSubject.create();
  private final Subject<OnSaveRestoreInstanceStateParams> _onRestoreInstanceState =
      PublishSubject.create();
  private final Subject<OnActivityResultParams> _onActivityResult = PublishSubject.create();
  private final Subject<V> _onBackPressed = PublishSubject.create();
  private final Subject<OnOptionsItemSelectedParams> _onOptionsItemSelected =
      PublishSubject.create();
  private final Subject<V> _onPause = PublishSubject.create();
  private final Subject<V> _onResume = PublishSubject.create();

  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //

  public final Observable<V> onCreate = _onCreate;
  public final Observable<V> onFinishInflate = _onFinishInflate;
  public final Observable<OnProvideTransientParamsParams> onProvideTransientParams =
      _onProvideTransientParams;
  public final Observable<OnReceiveTransientParamsParams> onReceiveTransientParams =
      _onReceiveTransientParams;
  public final Observable<V> onAttachedToWindow = _onAttachedToWindow;
  public final Observable<V> onDetachedFromWindow = _onDetachedFromWindow;
  public final Observable<OnVisibilityChangedParams> onVisibilityChanged = _onVisibilityChanged;
  public final Observable<OnSizeChangedParams> onSizeChanged = _onSizeChanged;
  public final Observable<SaveRestoreHierarchyStateParams> restoreHierarchyState =
      _restoreHierarchyState;
  public final Observable<SaveRestoreHierarchyStateParams> saveHierarchyState = _saveHierarchyState;
  public final Observable<OnSaveRestoreInstanceStateParams> onSaveInstanceState =
      _onSaveInstanceState;
  public final Observable<OnSaveRestoreInstanceStateParams> onRestoreInstanceState =
      _onRestoreInstanceState;
  public final Observable<OnActivityResultParams> onActivityResult = _onActivityResult;
  public final Observable<V> onBackPressed = _onBackPressed;
  public final Observable<OnOptionsItemSelectedParams> onOptionsItemSelected =
      _onOptionsItemSelected;
  public final Observable<V> onPause = _onPause;
  public final Observable<V> onResume = _onResume;

  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //

  protected RxScreenViewPresenter() {
    Observable<?> o;

    o = autoSub1();
    if (o != null) o.subscribe();
    o = autoSub2();
    if (o != null) o.subscribe();
    o = autoSub3();
    if (o != null) o.subscribe();
    o = autoSub4();
    if (o != null) o.subscribe();
    o = autoSub5();
    if (o != null) o.subscribe();
    o = autoSub6();
    if (o != null) o.subscribe();
    o = autoSub7();
    if (o != null) o.subscribe();
    o = autoSub8();
    if (o != null) o.subscribe();
    o = autoSub9();
    if (o != null) o.subscribe();
    o = autoSub10();
    if (o != null) o.subscribe();
    o = autoSub11();
    if (o != null) o.subscribe();
    o = autoSub12();
    if (o != null) o.subscribe();
    o = autoSub13();
    if (o != null) o.subscribe();
    o = autoSub14();
    if (o != null) o.subscribe();
    o = autoSub15();
    if (o != null) o.subscribe();
    o = autoSub16();
    if (o != null) o.subscribe();
    o = autoSub17();
    if (o != null) o.subscribe();
    o = autoSub18();
    if (o != null) o.subscribe();
    o = autoSub19();
    if (o != null) o.subscribe();
    o = autoSub20();
    if (o != null) o.subscribe();
  }

  protected Observable<?> autoSub1() {
    return null;
  }

  protected Observable<?> autoSub2() {
    return null;
  }

  protected Observable<?> autoSub3() {
    return null;
  }

  protected Observable<?> autoSub4() {
    return null;
  }

  protected Observable<?> autoSub5() {
    return null;
  }

  protected Observable<?> autoSub6() {
    return null;
  }

  protected Observable<?> autoSub7() {
    return null;
  }

  protected Observable<?> autoSub8() {
    return null;
  }

  protected Observable<?> autoSub9() {
    return null;
  }

  protected Observable<?> autoSub10() {
    return null;
  }

  protected Observable<?> autoSub11() {
    return null;
  }

  protected Observable<?> autoSub12() {
    return null;
  }

  protected Observable<?> autoSub13() {
    return null;
  }

  protected Observable<?> autoSub14() {
    return null;
  }

  protected Observable<?> autoSub15() {
    return null;
  }

  protected Observable<?> autoSub16() {
    return null;
  }

  protected Observable<?> autoSub17() {
    return null;
  }

  protected Observable<?> autoSub18() {
    return null;
  }

  protected Observable<?> autoSub19() {
    return null;
  }

  protected Observable<?> autoSub20() {
    return null;
  }

  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //

  @CallSuper
  @Override
  protected void onCreate(@NonNull V v) {
    _onCreate.onNext(v);
    super.onCreate(v);
  }

  @CallSuper
  @Override
  protected void onFinishInflate(@NonNull V v) {
    _onFinishInflate.onNext(v);
    super.onFinishInflate(v);
  }

  @Nullable
  @CallSuper
  @Override
  protected Bundle onProvideTransientParams(@NonNull V v, @NonNull View view,
      @Nullable Bundle params) {
    _onProvideTransientParams.onNext(new OnProvideTransientParamsParams<>(v, view, params));
    return super.onProvideTransientParams(v, view, params);
  }

  @CallSuper
  @Override
  protected void onReceiveTransientParams(@NonNull V v, @Nullable Bundle params) {
    _onReceiveTransientParams.onNext(new OnReceiveTransientParamsParams<>(v, params));
    super.onReceiveTransientParams(v, params);
  }

  @CallSuper
  @Override
  protected void onAttachedToWindow(@NonNull V v) {
    _onAttachedToWindow.onNext(v);
    super.onAttachedToWindow(v);
  }

  @CallSuper
  @Override
  protected void onDetachedFromWindow(@NonNull V v) {
    _onDetachedFromWindow.onNext(v);
    super.onDetachedFromWindow(v);
  }

  @CallSuper
  @Override
  protected void onVisibilityChanged(@NonNull V v, @NonNull View changedView, int visibility) {
    _onVisibilityChanged.onNext(new OnVisibilityChangedParams<>(v, changedView, visibility));
    super.onVisibilityChanged(v, changedView, visibility);
  }

  @CallSuper
  @Override
  protected void onSizeChanged(@NonNull V v, int w, int h, int oldw, int oldh) {
    _onSizeChanged.onNext(new OnSizeChangedParams<>(v, w, h, oldw, oldh));
    super.onSizeChanged(v, w, h, oldw, oldh);
  }

  @CallSuper
  @Override
  protected void restoreHierarchyState(@NonNull V v, @NonNull SparseArray<Parcelable> container) {
    _restoreHierarchyState.onNext(new SaveRestoreHierarchyStateParams<>(v, container));
    super.restoreHierarchyState(v, container);
  }

  @CallSuper
  @Override
  protected void saveHierarchyState(@NonNull V v, @NonNull SparseArray<Parcelable> container) {
    _saveHierarchyState.onNext(new SaveRestoreHierarchyStateParams<>(v, container));
    super.saveHierarchyState(v, container);
  }

  @CallSuper
  @Override
  protected void onSaveInstanceState(@NonNull V v, @NonNull Bundle outState) {
    _onSaveInstanceState.onNext(new OnSaveRestoreInstanceStateParams<>(v, outState));
    super.onSaveInstanceState(v, outState);
  }

  @CallSuper
  @Override
  protected void onRestoreInstanceState(@NonNull V v, @NonNull Bundle inState) {
    _onRestoreInstanceState.onNext(new OnSaveRestoreInstanceStateParams<>(v, inState));
    super.onRestoreInstanceState(v, inState);
  }

  @CallSuper
  @Override
  protected boolean onActivityResult(@NonNull V v, int requestCode, int resultCode, Intent data) {
    _onActivityResult.onNext(new OnActivityResultParams<>(v, requestCode, resultCode, data));
    return super.onActivityResult(v, requestCode, resultCode, data);
  }

  @CallSuper
  @Override
  protected boolean onBackPressed(@NonNull V v) {
    _onBackPressed.onNext(v);
    return super.onBackPressed(v);
  }

  @CallSuper
  @Override
  protected boolean onOptionsItemSelected(@NonNull V v, MenuItem item) {
    _onOptionsItemSelected.onNext(new OnOptionsItemSelectedParams<>(v, item));
    return super.onOptionsItemSelected(v, item);
  }

  @CallSuper
  @Override
  protected void onPause(@NonNull V v) {
    _onPause.onNext(v);
    super.onPause(v);
  }

  @CallSuper
  @Override
  protected void onResume(@NonNull V v) {
    _onResume.onNext(v);
    super.onResume(v);
  }

  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //
  // -------------------------------------------------------------------------------------------- //

  protected static final class OnProvideTransientParamsParams<V extends View & ScreenView> {

    @NonNull public final V v;
    @NonNull public final View view;
    @Nullable public final Bundle params;

    OnProvideTransientParamsParams(@NonNull V v, @NonNull View view, @Nullable Bundle params) {
      this.v = v;
      this.view = view;
      this.params = params;
    }
  }

  protected static final class OnReceiveTransientParamsParams<V extends View & ScreenView> {

    @NonNull public final V v;
    @Nullable public final Bundle params;

    OnReceiveTransientParamsParams(@NonNull V v, @Nullable Bundle params) {
      this.v = v;
      this.params = params;
    }
  }

  protected static final class OnVisibilityChangedParams<V extends View & ScreenView> {

    @NonNull public final V v;
    @NonNull public final View changedView;
    public final int visibility;

    OnVisibilityChangedParams(@NonNull V v, @NonNull View changedView, int visibility) {
      this.v = v;
      this.changedView = changedView;
      this.visibility = visibility;
    }
  }

  protected static final class OnSizeChangedParams<V extends View & ScreenView> {

    @NonNull public final V v;
    public final int w;
    public final int h;
    public final int oldw;
    public final int oldh;

    public OnSizeChangedParams(@NonNull V v, int w, int h, int oldw, int oldh) {
      this.v = v;
      this.w = w;
      this.h = h;
      this.oldw = oldw;
      this.oldh = oldh;
    }
  }

  protected static final class SaveRestoreHierarchyStateParams<V extends View & ScreenView> {

    @NonNull public final V v;
    @NonNull public final SparseArray<Parcelable> container;

    public SaveRestoreHierarchyStateParams(@NonNull V v,
        @NonNull SparseArray<Parcelable> container) {
      this.v = v;
      this.container = container;
    }
  }

  protected static final class OnSaveRestoreInstanceStateParams<V extends View & ScreenView> {

    @NonNull public final V v;
    @NonNull public final Bundle state;

    public OnSaveRestoreInstanceStateParams(@NonNull V v, @NonNull Bundle state) {
      this.v = v;
      this.state = state;
    }
  }

  protected static final class OnActivityResultParams<V extends View & ScreenView> {

    @NonNull public final V v;
    public final int requestCode;
    public final int resultCode;
    public final Intent data;

    public OnActivityResultParams(@NonNull V v, int requestCode, int resultCode, Intent data) {
      this.v = v;
      this.requestCode = requestCode;
      this.resultCode = resultCode;
      this.data = data;
    }
  }

  protected static final class OnOptionsItemSelectedParams<V extends View & ScreenView> {

    @NonNull public final V v;
    public final MenuItem item;

    public OnOptionsItemSelectedParams(@NonNull V v, MenuItem item) {
      this.v = v;
      this.item = item;
    }
  }
}
