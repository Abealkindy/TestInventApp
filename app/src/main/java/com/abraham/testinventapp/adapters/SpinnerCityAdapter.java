package com.abraham.testinventapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abraham.testinventapp.R;
import com.abraham.testinventapp.models.CityModel;

import java.util.List;

public class SpinnerCityAdapter extends ArrayAdapter<CityModel.CityValue> {
    private Context context;
    private List<CityModel.CityValue> cityValueList;

    public SpinnerCityAdapter(@NonNull Context context, int textViewResourceId, List<CityModel.CityValue> cityValueList) {
        super(context, textViewResourceId, cityValueList);
        this.context = context;
        this.cityValueList = cityValueList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_row, parent, false);
        TextView textCityName = rowView.findViewById(R.id.spinner_text_view);
        textCityName.setText(cityValueList.get(position).getCityName());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_row, parent, false);
        TextView textCityName = rowView.findViewById(R.id.spinner_text_view);
        textCityName.setText(cityValueList.get(position).getCityName());
        return rowView;
    }
}
