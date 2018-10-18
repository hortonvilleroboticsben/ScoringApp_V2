package com.hortonvillerobotics.scoringapp_v2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class SettingsActivity extends PreferenceActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

    }
}
