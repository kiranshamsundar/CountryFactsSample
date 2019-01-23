package com.facts.country.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.facts.country.data.model.Country;
import com.facts.country.data.model.CountryFact;
import com.facts.country.data.rest.CountryFactsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class CountryFactsViewModel extends ViewModel {

    private final CountryFactsRepository countryFactsRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<CountryFact>> countryFactsList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<String> countryName = new MutableLiveData<>();

    @Inject
    public CountryFactsViewModel(CountryFactsRepository countryFactsRepository) {
        this.countryFactsRepository = countryFactsRepository;
        disposable = new CompositeDisposable();
        fetchCountryFacts();
    }

    public LiveData<List<CountryFact>> getCountryFactsList() {
        return countryFactsList;
    }

    LiveData<String> getCountryName(){return  countryName;}

    LiveData<Boolean> getError() {
        return loadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    public void fetchCountryFacts() {
        loading.setValue(true);
        disposable.add(countryFactsRepository.getCountryFacts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Country>() {
                    @Override
                    public void onSuccess(Country value) {
                        loadError.setValue(false);
                        countryFactsList.setValue(value.getRows());
                        countryName.setValue(value.getTitle());
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
