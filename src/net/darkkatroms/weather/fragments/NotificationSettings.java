/*
 * Copyright (C) 2016 DarkKat
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

package net.darkkatroms.weather.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import net.darkkatroms.weather.Config;
import net.darkkatroms.weather.R;
import net.darkkatroms.weather.WeatherService;

public class NotificationSettings extends PreferenceFragment implements
        OnPreferenceChangeListener {

    private SwitchPreference mShow;
    private SwitchPreference mShowLocation;
    private SwitchPreference mShowSecure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.notification_settings);

        mShow = (SwitchPreference) findPreference(Config.PREF_KEY_SHOW_NOTIF);
        mShow.setOnPreferenceChangeListener(this);

        mShowLocation =
                (SwitchPreference) findPreference(Config.PREF_KEY_NOTIF_SHOW_LOCATION);
        mShowLocation.setOnPreferenceChangeListener(this);

        mShowSecure =
                (SwitchPreference) findPreference(Config.PREF_KEY_NOTIF_SHOW_SECURE);
        mShowSecure.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value;

        if (preference == mShow) {
            value = (Boolean) newValue;
            if (value) {
                updateNotification();
            } else {
                WeatherService.removeNotification(getActivity());
            }
            return true;
        } else if (preference == mShowLocation || preference == mShowSecure) {
            updateNotification();
            return true;
        }
        return false;
    }

    private void updateNotification() {
        WeatherService.startNotificationUpdate(getActivity());
    }
}
