package com.icarus.androidtest.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import com.icarus.androidtest.R;


public class CustomTextView extends TextView {

    private int typefaceCode;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            // Get Custom Attribute Name and value
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.FontTextView);
            typefaceCode = styledAttrs.getInt(R.styleable.FontTextView_fontType, -1);
            Typeface typeface = TypefaceCache.get(context.getAssets(), typefaceCode);
            setTypeface(typeface);

            styledAttrs.recycle();
            if (isInEditMode()) {
                return;
            }
        }
    }

    public CustomTextView(Context context) {
        super(context);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text != null) {
            text = Html.fromHtml(text.toString());
        }
        super.setText(text, type);
    }
}