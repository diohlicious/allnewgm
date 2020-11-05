package com.sip.grosirmobil.base.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

import io.reactivex.annotations.Nullable;

public class GrosirSupportMapFragment extends MapView {

    private ViewParent mViewParent;
    public GrosirSupportMapFragment(Context context) {
        super(context);
    }

    public GrosirSupportMapFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GrosirSupportMapFragment(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GrosirSupportMapFragment(Context context, GoogleMapOptions options) {
        super(context, options);
    }

    public void setViewParent(@Nullable final ViewParent viewParent) { //any ViewGroup
        mViewParent = viewParent;
    }

    @Override
    public boolean onInterceptTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null == mViewParent) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    mViewParent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (null == mViewParent) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    mViewParent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(event);
    }
}
