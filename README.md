# Android base UI lib

Base UI library cobbled together from a number of other solutions - Dagger 2, Mortar & Flow, RxJava. Needs some cleanup - Mortar needs to be ripped out and replaced with latest Flow. Common "utility" service injection at the Activity level needs to be reworked. Some utilities included here are probably better split into their own libraries (in particular the ViewGroupLoader). Otto event bus should be completely ripped out and replaced with Rx.
