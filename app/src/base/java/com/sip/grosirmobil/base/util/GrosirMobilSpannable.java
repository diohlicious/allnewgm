package com.sip.grosirmobil.base.util;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

public class GrosirMobilSpannable extends ClickableSpan {

    private boolean isUnderline = true;

    /**
     * Constructor
     */
    protected GrosirMobilSpannable(boolean isUnderline) {
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