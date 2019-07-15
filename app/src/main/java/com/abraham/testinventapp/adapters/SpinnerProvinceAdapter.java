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
import com.abraham.testinventapp.models.ProvinceModel;

import java.util.List;

public class SpinnerProvinceAdapter extends ArrayAdapter<ProvinceModel.ProvinceValue> {
    private Context context;
    private List<ProvinceModel.ProvinceValue> provinceValueList;

    public SpinnerProvinceAdapter(@NonNull Context context, int textViewResourceId, List<ProvinceModel.ProvinceValue> provinceValueList) {
        super(context, textViewResourceId, provinceValueList);
        this.context = context;
        this.provinceValueList = provinceValueList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_row, parent, false);
        TextView textProvinceName = rowView.findViewById(R.id.spinner_text_view);
        textProvinceName.setText(provinceValueList.get(position).getProvinceName());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_row, parent, false);
        TextView textProvinceName = rowView.findViewById(R.id.spinner_text_view);
        textProvinceName.setText(provinceValueList.get(position).getProvinceName());
        return rowView;
    }
}
