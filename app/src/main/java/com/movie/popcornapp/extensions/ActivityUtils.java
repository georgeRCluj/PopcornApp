package com.movie.popcornapp.extensions;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.movie.popcornapp.R;

/**
 * @author george.radu on 2019-07-07.
 */
public class ActivityUtils {

    //region Fragment Add/Replace
    public enum FragmentAnimationType {
        SlideInSlideOut
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     * @param fragmentManager       - fragment manager
     * @param fragment              - the fragment to add
     * @param frameId               - the frame id to replace
     * @param addToBackStack        - initial fragment?
     * @param fragmentAnimationType - optional animation type
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,
                                             int frameId,
                                             Boolean addToBackStack,
                                             FragmentAnimationType... fragmentAnimationType) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentAnimationType != null && fragmentAnimationType.length > 0) {
            switch (fragmentAnimationType[0]) {
                case SlideInSlideOut:
                    transaction.setCustomAnimations(
                            R.anim.right_slide_in,
                            R.anim.right_slide_out,
                            R.anim.right_slide_in,
                            R.anim.right_slide_out
                    );
                    break;
            }
        }
        transaction.add(frameId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(String.valueOf(fragment.getId()));
        }
        transaction.commit();
    }
    //endregion;
}
