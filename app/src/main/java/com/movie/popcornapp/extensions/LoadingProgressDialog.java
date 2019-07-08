package com.movie.popcornapp.extensions;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author george.radu on 2019-07-08.
 */
public class LoadingProgressDialog extends ProgressDialog {

    //region Lifecycle
    public LoadingProgressDialog(Context context) {
        super(context);

        this.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.setIndeterminate(true);
        this.setCancelable(false);
    }

    public void show(String message) {
        this.setMessage(message);
        super.show();
    }
    //endregion;
}
