package com.facts.country.data.model;

import android.support.annotation.NonNull;

public class CountryFact {

    public String title;

    public String description;

    public String imageHref;

    public CountryFact(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(@NonNull String imageHref) {
        this.imageHref = imageHref;
    }
}
