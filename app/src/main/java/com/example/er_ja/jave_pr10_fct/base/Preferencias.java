package com.example.er_ja.jave_pr10_fct.base;

import android.os.Bundle;

import com.example.er_ja.jave_pr10_fct.R;

import androidx.preference.PreferenceFragmentCompat;

public class Preferencias extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
