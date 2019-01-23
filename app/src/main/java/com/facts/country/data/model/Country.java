package com.facts.country.data.model;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private String title;

    private ArrayList<CountryFact> rows;

    public Country(String title, ArrayList<CountryFact> rows) {
        this.title = title;
        this.rows = rows;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }


    public List<CountryFact> getRows() {
        return rows;
    }

    public void setRows( ArrayList<CountryFact> rows) {
        this.rows = rows;
    }
}
