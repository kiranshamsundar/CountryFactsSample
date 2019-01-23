package com.facts.country.di.module;

import com.facts.country.view.CountryFactsFragmentBindingModule;
import com.facts.country.view.CountryFactsHomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {CountryFactsFragmentBindingModule.class})
    abstract CountryFactsHomeActivity bindHomeActivity();
}
