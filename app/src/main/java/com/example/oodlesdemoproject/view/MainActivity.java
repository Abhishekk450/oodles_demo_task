package com.example.oodlesdemoproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oodlesdemoproject.R;
import com.example.oodlesdemoproject.databinding.ActivityMainBinding;
import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;
import com.example.oodlesdemoproject.utils.ConnectionDetector;
import com.example.oodlesdemoproject.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    ActivityMainBinding activityMainBinding;
    MainActivityAdapter mainActivityAdapter;

    ArrayList<CountryDetailsResponse> countrydetails = new ArrayList<>();
    String isoCode;

    RecyclerView recyclerView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.edit_search_view);
        button = findViewById(R.id.search_button);

        editText.addTextChangedListener(textWatcher);

        if(!ConnectionDetector.isNetworkConnected(MainActivity.this)) {
            Toast.makeText(MainActivity.this, R.string.internet_connection_check, Toast.LENGTH_LONG).show();
        } else {
            mainActivityViewModel.getData().observe(this, new Observer<ArrayList<CountryDetailsResponse>>() {
                @Override
                public void onChanged(ArrayList<CountryDetailsResponse> countryLists) {
                    setUpRecyclerView(countryLists);
                }
            });
        }
    }

    private void setUpRecyclerView(ArrayList<CountryDetailsResponse> countryLists) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainActivityAdapter = new MainActivityAdapter(this, countryLists);
        recyclerView.setAdapter(mainActivityAdapter);
    }

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.toString().isEmpty()) {
                getCountryList();
            }
            getCountryInfo(s.toString());
        }
    };

    private void getCountryList() {
        mainActivityViewModel.getData().observe(this, new Observer<ArrayList<CountryDetailsResponse>>() {
            @Override
            public void onChanged(ArrayList<CountryDetailsResponse> countryLists) {
                setUpRecyclerView(countryLists);
            }
        });
    }

    private void getCountryInfo(String text) {
        mainActivityViewModel.getCountryDetails(text).observe(MainActivity.this, new Observer<CountryDetailsResponse>() {
            @Override
            public void onChanged(CountryDetailsResponse countryListResponse) {
                ArrayList<CountryDetailsResponse> countryDetailsResponses = new ArrayList<>();
                countryDetailsResponses.add(countryListResponse);
                setCountryDetailsList(countryDetailsResponses);

            }
        });
    }

    private void setCountryDetailsList(ArrayList<CountryDetailsResponse> countryDetailsResponses) {
        countrydetails.clear();
        countrydetails.addAll(countryDetailsResponses);
        mainActivityAdapter.updateLists(countryDetailsResponses);
    }
}