package com.example.mikhail_sianko.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikhail_sianko.myapplication.R;

/**
 * Created by Alex Dzeshko on 30-Sep-16.
 */

public class FirstFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean color = getArguments().getBoolean("color");
        if (color) {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }
    }

    public static Fragment newInstance(boolean isRed) {
        FirstFragment fragment = new FirstFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean("color", isRed);
        fragment.setArguments(arguments);
        return fragment;
    }
}
