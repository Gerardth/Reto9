package com.example.gerardth.reto9;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by Gerardth on 12/10/2016.
 */

public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        final EditTextPreference distance = (EditTextPreference) findPreference("distance_places");
        String distanceString = prefs.getString("distance_places", getResources().getString(R.string.distance));
        distance.setSummary(distanceString);

        distance.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                distance.setSummary(newValue.toString());

                // Since we are handling the pref, we must save it
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("distance_places", newValue.toString());
                ed.commit();
                return true;
            }
        });
    }

}
