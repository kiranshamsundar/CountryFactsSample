package com.facts.country.view;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CountryFactsFragmentBindingModule {

    @ContributesAndroidInjector
    abstract CountryFactsListFragment provideListFragment();


}
