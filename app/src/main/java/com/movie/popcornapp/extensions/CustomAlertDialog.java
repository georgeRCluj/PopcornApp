package com.movie.popcornapp.extensions;

import android.content.Context;

import com.movie.popcornapp.R;

/**
 * @author george.radu on 2019-07-08.
 */
public class CustomAlertDialog extends android.app.AlertDialog {

    //region Properties
    private Context mContext;
    private String mTitle;
    private int mDialogIcon;
    private CustomAlertDialogInterface mCustomAlertDialogInterface;
    //endregion;

    //region Lifecycle
    public CustomAlertDialog(Context context,
                             String title,
                             int dialogIcon,
                             CustomAlertDialogInterface customAlertDialogInterface) {
        super(context);
        this.mContext = context;
        this.mTitle = title;
        this.mDialogIcon = dialogIcon;
        this.mCustomAlertDialogInterface = customAlertDialogInterface;
    }

    public void showAlert(String message) {
        this.setTitle(mTitle);
        this.setMessage(message);
        this.setIconAttribute(mDialogIcon);
        this.setCancelable(false);
        this.setButton(android.app.AlertDialog.BUTTON_POSITIVE, mContext.getString(R.string.dialog_positive_button), (dialog, id) -> {
            mCustomAlertDialogInterface.onOkButtonPressed();
        });
        super.show();
    }
    //endregion;
}
