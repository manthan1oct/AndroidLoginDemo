package com.icarus.androidtest.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.EditText;

import com.icarus.androidtest.R;


public class CustomEditText extends EditText {

    private int typefaceCode;

    public CustomEditText(Context context, AttributeSet attrs) {
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

    public CustomEditText(Context context) {
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