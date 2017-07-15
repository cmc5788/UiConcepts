package com.christophermcasey.appcore.mortarflow.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;
import com.christophermcasey.appcore.mortarflow.Screen;

/** Default scope for {@link Screen} - should be used for any non-nested screen. */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerScreen {
}
