package com.facts.country.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.facts.country.di.util.ViewModelKey;
import com.facts.country.util.ViewModelFactory;
import com.facts.country.view.CountryFactsViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountryFactsViewModel.class)
    abstract ViewModel bindCountryFragListViewModel(CountryFactsViewModel countryFactsViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
