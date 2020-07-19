package com.example.oodlesdemoproject.model.repo;

import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;

import java.io.Serializable;
import java.util.ArrayList;

public class CountryListResponse implements Serializable {
    private ArrayList<CountryDetailsResponse> countryLists;

    public ArrayList<CountryDetailsResponse> getCountryLists() {
        return countryLists;
    }

    public void setProfiles(ArrayList<CountryDetailsResponse> countryLists) {
        this.countryLists = countryLists;
    }
}
