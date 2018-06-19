package com.janelaaj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.janelaaj.fragment.RegistrationFirstpageFragment;
import com.janelaaj.fragment.RegistrationSecondPageFragment;


public class RegistrationPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfPage = 2;
    String email;
    String PersonName;
    public RegistrationPagerAdapter(FragmentManager fm, String email,String PersonName) {
        super(fm);
        this.email=email;
        this.PersonName=PersonName;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RegistrationFirstpageFragment tab1 = RegistrationFirstpageFragment.newInstance(email, PersonName);
                return tab1;
            case 1:
                RegistrationSecondPageFragment tab2 = RegistrationSecondPageFragment.newInstance("", "");
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfPage;
    }
}
