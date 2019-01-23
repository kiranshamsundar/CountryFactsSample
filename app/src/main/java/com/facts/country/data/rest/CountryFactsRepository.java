package com.facts.country.data.rest;

import com.facts.country.data.model.Country;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountryFactsRepository {

    private final CountryFactsService countryFactsService;

    @Inject
    public CountryFactsRepository(CountryFactsService countryFactsService) {
        this.countryFactsService = countryFactsService;
    }

    public Single<Country> getCountryFacts() {
        return countryFactsService.getCountryFacts();
    }

}
