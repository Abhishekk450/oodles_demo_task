package com.example.oodlesdemoproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;
import com.example.oodlesdemoproject.model.repo.CountryListRepo;

import java.util.ArrayList;

public class MainActivityViewModel extends ViewModel {

    CountryListRepo countryListRepo;

    MutableLiveData<ArrayList<CountryDetailsResponse>> countryListData;
    MutableLiveData<CountryDetailsResponse> countryDetails;

    public CountryListRepo init() {
        if (countryListRepo != null) return countryListRepo;

        else {
            countryListRepo = CountryListRepo.getInstance();
            return countryListRepo;
        }
    }

    public LiveData<ArrayList<CountryDetailsResponse>> getData() {
        countryListData = countryListRepo.getCountryList();
        return countryListData;
    }

    public LiveData<CountryDetailsResponse> getCountryDetails(String iso2) {
        countryDetails = countryListRepo.getCountyDetails(iso2);
        return countryDetails;
    }
}
