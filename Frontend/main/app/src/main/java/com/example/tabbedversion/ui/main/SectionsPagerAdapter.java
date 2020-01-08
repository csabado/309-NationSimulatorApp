package com.example.tabbedversion.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabbedversion.MessageCompose;
import com.example.tabbedversion.MessageInbox;
import com.example.tabbedversion.R;
import com.example.tabbedversion.TaxPage;
import com.example.tabbedversion.decisions;
import com.example.tabbedversion.issues_logic;
import com.example.tabbedversion.purchasing;
import com.example.tabbedversion.tab1;
import com.example.tabbedversion.tab2;
import com.example.tabbedversion.tab3;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new tab1();
                break;
            case 1:
                fragment = new tab2();
                break;
            case 2:
                fragment = new issues_logic();
                break;

            case 3:
                fragment = new purchasing();
                break;
            case 4:
                fragment = new TaxPage();
                break;
            case 5:
                fragment = new tab3();
                break;

        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Stats";
            case 1:
                return "Pals";
            case 2:

                return "Issue";
            case 3:
                return "$$$";
            case 4:
                return "Tax";
            case 5:
                return "\uD83D\uDD27";

        }
        return null;
    }

    @Override
    public int getCount() {
// Show 6 total pages.

        return 6;

    }
}