package com.example.oodlesdemoproject.model.repo;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.oodlesdemoproject.R;
import com.example.oodlesdemoproject.datamanage.ApiInterface;
import com.example.oodlesdemoproject.datamanage.RetrofitService;
import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;
import com.example.oodlesdemoproject.utils.ConnectionDetector;
import com.example.oodlesdemoproject.view.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListRepo {

    private static CountryListRepo countryListRepo;
    private ApiInterface apiInterface;

    public static CountryListRepo getInstance() {
        if (countryListRepo != null) {
            return countryListRepo;
        } else {
            countryListRepo = new CountryListRepo();
            return countryListRepo;
        }
    }

    public CountryListRepo() {
        this.apiInterface = RetrofitService.createService(ApiInterface.class);
    }

    public MutableLiveData<ArrayList<CountryDetailsResponse>> getCountryList() {

        final MutableLiveData<ArrayList<CountryDetailsResponse>> data = new MutableLiveData<>();

        apiInterface.getCountryList().enqueue(new Callback<ArrayList<CountryDetailsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryDetailsResponse>> call, Response<ArrayList<CountryDetailsResponse>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CountryDetailsResponse>> call, Throwable t) {

            }

        });

        return data;
    }

    public MutableLiveData<CountryDetailsResponse> getCountyDetails(String isoCode) {
        final MutableLiveData<CountryDetailsResponse> countrydetails = new MutableLiveData<>();

        apiInterface.getCountyDetails(isoCode).enqueue(new Callback<CountryDetailsResponse>() {


            @Override
            public void onResponse(Call<CountryDetailsResponse> call, Response<CountryDetailsResponse> response) {
                if (response.isSuccessful()) {
                    countrydetails.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CountryDetailsResponse> call, Throwable t) {

            }
        });
        return countrydetails;
    }
}
