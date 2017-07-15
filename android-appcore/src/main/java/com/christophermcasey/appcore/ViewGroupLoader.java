package com.christophermcasey.appcore;

import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import static android.os.Looper.getMainLooper;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

@SuppressWarnings("WeakerAccess")
public final class ViewGroupLoader implements Action {

  private static <T> Observable<T> wrapInternal(@NonNull final Observable<T> wrapped,
      final Callable<ViewGroupLoader> vglFactory) {
    return Observable.using(new Callable<AtomicReference<ViewGroupLoader>>() {
      @Override
      public AtomicReference<ViewGroupLoader> call() throws Exception {
        return new AtomicReference<>();
      }
    }, new Function<AtomicReference<ViewGroupLoader>, ObservableSource<T>>() {
      @Override
      public ObservableSource<T> apply(@NonNull final AtomicReference<ViewGroupLoader> r) {
        HANDLER.post(new Runnable() {
          @Override
          public void run() {
            try {
              r.set(vglFactory.call());
            } catch (Exception ignored) {
            }
          }
        });
        return wrapped;
      }
    }, new Consumer<AtomicReference<ViewGroupLoader>>() {
      @Override
      public void accept(@NonNull final AtomicReference<ViewGroupLoader> r) {
        HANDLER.post(new Runnable() {
          @Override
          public void run() {
            ViewGroupLoader vgl = r.getAndSet(null);
            if (vgl != null) vgl.run();
          }
        });
      }
    });
  }

  public static <T> Observable<T> wrapAndGo(@NonNull final Observable<T> wrapped,
      @NonNull ViewGroup root, @LayoutRes final int progressLayout) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return wrapInternal(wrapped, new Callable<ViewGroupLoader>() {
      @Override
      public ViewGroupLoader call() {
        ViewGroup root = rootRef.get();
        return root == null
            ? null
            : new ViewGroupLoader(root, progressLayout, 0, 0, 0, 0);
      }
    });
  }

  public static <T> ObservableTransformer<T, T> loadIntoViewGroup(@NonNull ViewGroup root,
      @LayoutRes final int progressLayout) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(@NonNull Observable<T> o) {
        ViewGroup root = rootRef.get();
        if (root == null) return o;
        return wrapAndGo(o, root, progressLayout);
      }
    };
  }

  public static <T> Observable<T> wrapAndGo(@NonNull Observable<T> wrapped, @NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return wrapInternal(wrapped, new Callable<ViewGroupLoader>() {
      @Override
      public ViewGroupLoader call() {
        ViewGroup root = rootRef.get();
        return root == null
            ? null
            : new ViewGroupLoader(root, progressLayout, delay, min, 0, 0);
      }
    });
  }

  public static <T> ObservableTransformer<T, T> loadIntoViewGroup(@NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(@NonNull Observable<T> o) {
        ViewGroup root = rootRef.get();
        if (root == null) return o;
        return wrapAndGo(o, root, progressLayout, delay, min);
      }
    };
  }

  public static <T> Observable<T> wrapAndGo(@NonNull Observable<T> wrapped, @NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min, final long delaySub) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return wrapInternal(wrapped.delaySubscription(delaySub, MILLISECONDS),
        new Callable<ViewGroupLoader>() {
          @Override
          public ViewGroupLoader call() {
            ViewGroup root = rootRef.get();
            return root == null
                ? null
                : new ViewGroupLoader(root, progressLayout, delay, min, 0, 0);
          }
        });
  }

  public static <T> ObservableTransformer<T, T> loadIntoViewGroup(@NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min, final long delaySub) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(@NonNull Observable<T> o) {
        ViewGroup root = rootRef.get();
        if (root == null) return o;
        return wrapAndGo(o, root, progressLayout, delay, min, delaySub);
      }
    };
  }

  public static <T> Observable<T> wrapAndGo(@NonNull Observable<T> wrapped, @NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min, final long delaySub,
      final int rootWidth, final int rootHeight) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return wrapInternal(wrapped.delaySubscription(delaySub, MILLISECONDS),
        new Callable<ViewGroupLoader>() {
          @Override
          public ViewGroupLoader call() {
            ViewGroup root = rootRef.get();
            return root == null
                ? null
                : new ViewGroupLoader(root, progressLayout, delay, min, rootWidth, rootHeight);
          }
        });
  }

  public static <T> ObservableTransformer<T, T> loadIntoViewGroup(@NonNull ViewGroup root,
      @LayoutRes final int progressLayout, final long delay, final long min, final long delaySub,
      final int rootWidth, final int rootHeight) {
    final WeakReference<ViewGroup> rootRef = new WeakReference<>(root);
    return new ObservableTransformer<T, T>() {
      @Override
      public ObservableSource<T> apply(@NonNull Observable<T> o) {
        ViewGroup root = rootRef.get();
        if (root == null) return o;
        return wrapAndGo(o, root, progressLayout, delay, min, delaySub, rootWidth, rootHeight);
      }
    };
  }

  private static final Handler HANDLER = new Handler(getMainLooper());
  private static final Map<ViewGroupLoader, Set<Runnable>> pendingStops = new HashMap<>();

  private static void addPendingStop(@NonNull ViewGroupLoader view, @NonNull Runnable r) {
    synchronized (pendingStops) {
      Set<Runnable> stops = pendingStops.get(view);
      if (stops == null) pendingStops.put(view, stops = new HashSet<>());
      stops.add(r);
    }
  }

  private static void removePendingStop(@NonNull Runnable r) {
    synchronized (pendingStops) {
      if (pendingStops.isEmpty()) return;
      for (Iterator<Map.Entry<ViewGroupLoader, Set<Runnable>>> i =
          pendingStops.entrySet().iterator(); i.hasNext(); ) {
        Set<Runnable> runnables = i.next().getValue();
        if (runnables.remove(r) && runnables.isEmpty()) i.remove();
      }
    }
  }

  private static void runAllPendingStops(@NonNull ViewGroupLoader view) {
    synchronized (pendingStops) {
      if (pendingStops.isEmpty()) return;
      Set<Runnable> toRun = new HashSet<>();
      for (Map.Entry<ViewGroupLoader, Set<Runnable>> entry : pendingStops.entrySet()) {
        ViewGroupLoader vgl = entry.getKey();
        if (vgl.root == view.root && view != vgl) toRun.addAll(entry.getValue());
      }
      for (Runnable r : toRun) {
        r.run();
      }
    }
  }

  private ViewGroup root;
  private View progressIndicator;
  private long delay;
  private long min;
  private final int rootWidth;
  private final int rootHeight;
  private final List<ViewVis> loadingViews = new ArrayList<>();

  private final AtomicLong startTime = new AtomicLong();

  private ViewGroupLoader(@NonNull ViewGroup root, @LayoutRes int progressLayout, long delay,
      long min, int rootWidth, int rootHeight) {
    if (currentThread() != getMainLooper().getThread()) {
      throw new RuntimeException("not on UI thread.");
    }

    this.root = root;
    this.progressIndicator =
        LayoutInflater.from(root.getContext()).inflate(progressLayout, root, false);
    this.delay = delay;
    this.min = min;
    this.rootWidth = rootWidth;
    this.rootHeight = rootHeight;

    startLoading();
  }

  private void startLoading() {
    runAllPendingStops(this);
    if (root == null || progressIndicator == null || progressIndicator.getParent() != null) {
      return;
    }

    int rootW = rootWidth > 0
        ? rootWidth
        : root.getWidth();
    int rootH = rootHeight > 0
        ? rootHeight
        : root.getHeight();
    if (rootW == 0 || rootH == 0 || delay > 0) {
      long delay = this.delay;
      this.delay = 0;
      HANDLER.postDelayed(new Runnable() {
        @Override
        public void run() {
          startLoading();
        }
      }, delay);
      return;
    }

    int targetW = rootW - (root.getPaddingLeft() + root.getPaddingRight());
    int targetH = rootH - (root.getPaddingTop() + root.getPaddingBottom());

    for (int i = 0; i < root.getChildCount(); i++) {
      View child = root.getChildAt(i);
      loadingViews.add(new ViewVis(child, child.getVisibility()));
      child.setVisibility(View.GONE);
    }

    root.addView(progressIndicator);
    ViewGroup.LayoutParams lp = progressIndicator.getLayoutParams();
    lp.width = targetW;
    lp.height = targetH;

    startTime.compareAndSet(0, System.currentTimeMillis());
  }

  private void stopLoading() {
    if (root == null || progressIndicator == null) return;

    root.removeView(progressIndicator);
    root = null;
    progressIndicator = null;

    for (ViewVis v : loadingViews) {
      v.view.setVisibility(v.vis);
    }
    loadingViews.clear();
  }

  @Override
  public void run() {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        HANDLER.removeCallbacks(this);
        removePendingStop(this);
        stopLoading();
      }
    };
    addPendingStop(this, r);
    long curMs = System.currentTimeMillis();
    startTime.compareAndSet(0, curMs);
    long dl = Math.max(0, min - (curMs - startTime.get()));
    HANDLER.postDelayed(r, dl);
  }

  private static final class ViewVis {
    final View view;
    final int vis;

    public ViewVis(View view, int vis) {
      this.view = view;
      this.vis = vis;
    }
  }
}
