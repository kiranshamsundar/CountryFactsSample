package com.facts.country.view;

import android.os.Bundle;

import com.facts.country.R;
import com.facts.country.base.BaseActivity;


public class CountryFactsHomeActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.country_facts_activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new CountryFactsListFragment()).commit();
    }
}
