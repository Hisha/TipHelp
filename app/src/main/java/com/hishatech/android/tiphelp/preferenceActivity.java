package com.hishatech.android.tiphelp;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class preferenceActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

}