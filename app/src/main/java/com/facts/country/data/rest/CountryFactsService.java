package com.facts.country.data.rest;

import com.facts.country.data.model.Country;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountryFactsService {

    @GET("s/2iodh4vg0eortkl/facts.js")
    Single<Country> getCountryFacts();

}
