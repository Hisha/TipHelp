package com.HishaTech.android.TipHelp;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class preferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}