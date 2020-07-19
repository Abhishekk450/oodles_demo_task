package com.example.oodlesdemoproject.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oodlesdemoproject.R;
import com.example.oodlesdemoproject.databinding.CountryApapterBinding;
import com.example.oodlesdemoproject.model.countrydetails.CountryDetailsResponse;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {


    LayoutInflater layoutInflater;
    Context context;
    String searchText = "";
    ArrayList<CountryDetailsResponse> countryLists;

    public MainActivityAdapter(Context context, ArrayList<CountryDetailsResponse> countryLists) {
        this.context = context;
        this.countryLists = countryLists;
        layoutInflater = LayoutInflater.from(context);
    }

    public void updateLists(ArrayList<CountryDetailsResponse> list) {
        countryLists = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.country_apapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryApapterBinding.setCountrylist(countryLists.get(position));
        holder.countryApapterBinding.executePendingBindings();
        holder.textView.setText(countryLists.get(position).getCurrencies().get(0).getName());
        GlideToVectorYou.init().with(context).load(Uri.parse(countryLists.get(position).getFlag()), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return countryLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CountryApapterBinding countryApapterBinding;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_flag);
            textView = itemView.findViewById(R.id.text_country_currency);
            countryApapterBinding = CountryApapterBinding.bind(itemView);
        }
    }
}
