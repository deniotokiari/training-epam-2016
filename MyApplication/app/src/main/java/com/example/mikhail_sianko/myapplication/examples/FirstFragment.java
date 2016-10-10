package com.example.mikhail_sianko.myapplication.examples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mikhail_sianko.myapplication.R;

public class FirstFragment extends Fragment {

    public static final String KEY = "key";
    private Object object;

    public static Fragment newInstance(final boolean isRed) {
        final FirstFragment fragment = new FirstFragment();
        final Bundle arguments = new Bundle();
        arguments.putBoolean("color", isRed);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle arguments = getArguments();
        final boolean color = arguments.getBoolean("color");
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.getBoolean(KEY);
        arguments.putString(KEY, getString(R.string.some_string_value));
        if (color) {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        }
    }
}
