package com.example.oodlesdemoproject.datamanage;

import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;
import com.example.oodlesdemoproject.model.repo.CountryListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("all")
    Call<ArrayList<CountryDetailsResponse>>getCountryList();

    @GET("alpha/{code}")
    Call<CountryDetailsResponse> getCountyDetails(@Path("code") String isoCode);
}
