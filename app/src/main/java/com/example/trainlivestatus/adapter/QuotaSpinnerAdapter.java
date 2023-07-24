package com.example.trainlivestatus.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trainlivestatus.R;
import com.example.trainlivestatus.model.SpinnerModel;

import java.util.ArrayList;

public class QuotaSpinnerAdapter extends ArrayAdapter<SpinnerModel> {

    Context context;
    ArrayList arrayList;

    public QuotaSpinnerAdapter(@NonNull Context context, ArrayList arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getViewItem(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewItem(position, convertView, parent);
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    public View getViewItem(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.train_spinner_item, null);
        TextView textView = view.findViewById(R.id.tv_train);
        textView.setText(arrayList.get(position).toString());
        textView.setSelected(true);
        return view;

    }
}
