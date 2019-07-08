package com.movie.popcornapp.extensions;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author george.radu on 2019-07-08.
 */
public class KeyboardUtils {

    public static void showKeyboard(Context context, EditText editText, boolean isShowNeeded) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        if (isShowNeeded) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } else {
            if (editText != null && editText.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }
}
