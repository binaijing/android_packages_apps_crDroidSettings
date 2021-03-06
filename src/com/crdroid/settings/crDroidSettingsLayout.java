/*
 * Copyright (C) 2017 crDroid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.crdroid.settings;

import android.app.Activity;
import android.content.Context;
import android.content.ContentResolver;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import com.crdroid.settings.fragments.StatusBarSettings;
import com.crdroid.settings.fragments.QuickSettings;
import com.crdroid.settings.fragments.ButtonSettings;
import com.crdroid.settings.fragments.NavbarSettings;
import com.crdroid.settings.fragments.NotificationsSettings;
import com.crdroid.settings.fragments.DisplaySettings;
import com.crdroid.settings.fragments.LockScreenSettings;
import com.crdroid.settings.fragments.PowerMenuSettings;
import com.crdroid.settings.fragments.RecentsSettings;
import com.crdroid.settings.fragments.SoundSettings;
import com.crdroid.settings.fragments.AnimationSettings;
import com.crdroid.settings.fragments.MiscSettings;
import com.crdroid.settings.fragments.About;

public class crDroidSettingsLayout extends SettingsPreferenceFragment {

    private static final String TAG = "crDroidSettingsLayout";
    ViewPager mViewPager;
    ViewGroup mContainer;
    PagerSlidingTabStrip mTabs;
    SectionsPagerAdapter mSectionsPagerAdapter;
    protected Context mContext;
    private LinearLayout mLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
        View view = inflater.inflate(R.layout.crdroid_settings, container, false);
        mLayout = (LinearLayout) view.findViewById(R.id.crdroid_content);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabs.setViewPager(mViewPager);
        mContext = getActivity().getApplicationContext();
        ContentResolver resolver = getActivity().getContentResolver();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {

        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new StatusBarSettings();
            frags[1] = new QuickSettings();
            frags[2] = new ButtonSettings();
            frags[3] = new NavbarSettings();
            frags[4] = new NotificationsSettings();
            frags[5] = new DisplaySettings();
            frags[6] = new LockScreenSettings();
            frags[7] = new PowerMenuSettings();
            frags[8] = new RecentsSettings();
            frags[9] = new SoundSettings();
            frags[10] = new AnimationSettings();
            frags[11] = new MiscSettings();
            frags[12] = new About();
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[] {
            getString(R.string.statusbar_title),
            getString(R.string.quicksettings_title),
            getString(R.string.button_title),
            getString(R.string.navbar_title),
            getString(R.string.notifications_title),
            getString(R.string.display_title),
            getString(R.string.lockscreen_title),
            getString(R.string.powermenu_title),
            getString(R.string.recents_title),
            getString(R.string.sound_title),
            getString(R.string.animation_title),
            getString(R.string.misc_title),
            getString(R.string.about_crdroid)
        };
        return titleString;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.CRDROID_SETTINGS;
     }
}
