package com.christophermcasey.appcore.mortarflow.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;
import com.christophermcasey.appcore.mortarflow.Screen;

/**
 * Convenience scope for {@link Screen screens} whose parent scopes are another screen - ViewPager
 * use case.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerSubScreen {
}
