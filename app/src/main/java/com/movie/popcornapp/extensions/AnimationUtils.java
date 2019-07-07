package com.movie.popcornapp.extensions;

import android.app.Activity;
import android.transition.Slide;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author george.radu on 2019-07-08.
 */
public class AnimationUtils {

    public static void setAnimation(Activity activity, int orientation, int animationTimeInMillis) {
        Slide slide = new Slide();
        slide.setSlideEdge(orientation);
        slide.setDuration(animationTimeInMillis);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());
        activity.getWindow().setExitTransition(slide);
        activity.getWindow().setEnterTransition(slide);
    }
}
