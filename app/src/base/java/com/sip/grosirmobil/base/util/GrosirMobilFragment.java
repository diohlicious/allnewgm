package com.sip.grosirmobil.base.util;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * Created by Syahrul Hajji on 22/09/18.
 */
public class GrosirMobilFragment extends Fragment {

    private FragmentInteractionListener mListener;

    public interface FragmentInteractionListener{
        void onFragmentInteraction(boolean isTabSolid,boolean isTabVisible);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (FragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.onFragmentInteraction(isTabSolid(),isTabVisible());
        if(!isTabSolid()){
            final View view = getView();
            if (view != null) {
                if(view.getTag()==null) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                                }
                            });
                        }
                    });

                    view.setTag(true);
                }
            }

        }
    }


    protected boolean isTabSolid() {
        return true;
    }

    protected boolean isTabVisible(){
        return true;
    }
}
