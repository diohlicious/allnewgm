package com.office.template.base.util;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

public class TemplateSpannable extends ClickableSpan {

    private boolean isUnderline = true;

    /**
     * Constructor
     */
    protected TemplateSpannable(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setUnderlineText(isUnderline);
        ds.setColor(Color.parseColor("#192D56"));
    }

    @Override
    public void onClick(@NonNull View widget) {

    }
}