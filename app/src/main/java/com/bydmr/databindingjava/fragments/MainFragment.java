package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentMainBinding;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    FragmentMainBinding mainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainBinding = FragmentMainBinding.inflate(inflater);

        return mainBinding.getRoot();
    }

}
