package com.example.mikhail_sianko.myapplication.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mikhail_sianko.myapplication.R;
import com.example.mikhail_sianko.myapplication.model.gson.TwitterSearchStatuses;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<TwitterSearchStatuses> {


    public ListViewAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ListViewAdapter(Context context, int resource, List<TwitterSearchStatuses> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        } else {
            view = convertView;
        }
        bindView(position, view);
        return view;
    }

    private void bindView(int position, View view) {
        TwitterSearchStatuses status = getItem(position);
        ((TextView) view.findViewById(R.id.user)).setText(status.getTwitterUser().getName());
        ((TextView) view.findViewById(R.id.value)).setText(status.getText());
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
