package com.sip.grosirmobil.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.sip.grosirmobil.base.util.SmartFragmentStatePagerAdapter;
import com.sip.grosirmobil.fragment.onboarding.OnBoardingStepOneFragment;
import com.sip.grosirmobil.fragment.onboarding.OnBoardingStepThreeFragment;
import com.sip.grosirmobil.fragment.onboarding.OnBoardingStepTwoFragment;

public class OnBoardingAdapter extends SmartFragmentStatePagerAdapter {

    public OnBoardingAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return OnBoardingStepOneFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return OnBoardingStepTwoFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return OnBoardingStepThreeFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
