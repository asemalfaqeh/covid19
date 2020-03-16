package com.af.covid19.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.af.covid19.R;
import com.af.covid19.model.DataResponse;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Viewholder> {

    private Context context;
    private ArrayList<DataResponse.CountriesStat> dataResponses;

     DataAdapter(Context context, ArrayList<DataResponse.CountriesStat> dataResponses){
        this.context = context;
        this.dataResponses = dataResponses;
    }

    @NonNull
    @Override
    public DataAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_adapter, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DataAdapter.Viewholder holder, int position) {

        DataResponse.CountriesStat casesResponse = dataResponses.get(position);

        Log.e("NewDeaths: " , casesResponse.getNewDeaths() + "");

        holder.critical.setText(casesResponse.getSeriousCritical() + "");
        holder.deathstoday.setText(casesResponse.getNewDeaths()+ "");
        holder.recovered.setText(casesResponse.getTotalRecovered() + "");
        holder.casestoday.setText(casesResponse.getNewCases() + "");
        holder.deaths.setText(casesResponse.getDeaths() + "");
        holder.country.setText(casesResponse.getCountryName() + "");
        holder.cases.setText(casesResponse.getCases() + "");

    }

    @Override
    public int getItemCount() {
        return dataResponses.size();
    }

     class Viewholder extends RecyclerView.ViewHolder {

        TextView cases, deaths, casestoday, deathstoday, recovered, critical, country;

         Viewholder(@NonNull View itemView) {
            super(itemView);

            cases = itemView.findViewById(R.id.cases_name);
            deaths = itemView.findViewById(R.id.deaths);
            country = itemView.findViewById(R.id.country_name);
            casestoday = itemView.findViewById(R.id.today_cases);
            recovered = itemView.findViewById(R.id.recovered);
            deathstoday = itemView.findViewById(R.id.today_deaths);
            critical = itemView.findViewById(R.id.critical);


        }
    }
}
