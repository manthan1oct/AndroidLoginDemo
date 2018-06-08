package com.icarus.androidtest.customdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.icarus.androidtest.R;
import com.icarus.androidtest.customview.TypefaceCache;


public class CustomProgressDialog extends ProgressDialog {

    Context mContext;

    public CustomProgressDialog(Context context) {
        super(context);

    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setIndeterminate(true);
        setCancelable(false);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        Drawable drawable = new ProgressBar(mContext).getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary),
                PorterDuff.Mode.SRC_IN);
        setIndeterminateDrawable(drawable);
//        }
        setCanceledOnTouchOutside(false);
        setMessage(mContext.getString(R.string.please_wait));
        TextView textView = (TextView) findViewById(android.R.id.message);
        textView.setTypeface(TypefaceCache.get(mContext.getAssets(), 0));
    }

}
