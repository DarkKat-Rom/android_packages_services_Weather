/*
 * Copyright (C) 2017 DarkKat
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
package net.darkkatroms.weather;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import net.darkkatroms.weather.fragments.NotificationSettings;
import net.darkkatroms.weather.fragments.SettingsFragment;
import net.darkkatroms.weather.utils.ThemeUtil;

public class SettingsActivity extends PreferenceActivity  {

    private static final String[] ENTRY_FRAGMENTS = {
        SettingsFragment.class.getName(),
        NotificationSettings.class.getName()
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (!ThemeUtil.themeColorsDisabled(this) && ThemeUtil.applyThemeColors(this)) {
            getTheme().applyStyle(ThemeUtil.getAppThemeOverlayResId(this), true);
        }

        super.onCreate(savedInstanceState);

        if (savedInstanceState == null && getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT) == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        for (int i = 0; i < ENTRY_FRAGMENTS.length; i++) {
            if (ENTRY_FRAGMENTS[i].equals(fragmentName)) return true;
        }
        return super.isValidFragment(fragmentName);
    }
}
