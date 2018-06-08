package com.icarus.androidtest.customdialog;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.icarus.androidtest.R;


public class CustomDialog {

    public static CustomDialog INSTANCE;
    AlertDialog alertDialog;

    public static CustomDialog getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CustomDialog();
        return INSTANCE;
    }

    public void errorDialog(Context mContext, String mMessage) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View promptView = layoutInflater.inflate(R.layout.alert_dialog, null);
        final AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(mContext, R.style.InvitationDialog);
        mAlertBuilder.setView(promptView);

        final TextView mMessageTextView = (TextView) promptView.findViewById(R.id.mMessageText);
        mMessageTextView.setText(mMessage);
        mAlertBuilder.setPositiveButton(mContext.getString(android.R.string.ok), null);
        alertDialog = mAlertBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }


    public interface DismissListenerWithStatus {
        public void onDismissed(String message);
    }
}
