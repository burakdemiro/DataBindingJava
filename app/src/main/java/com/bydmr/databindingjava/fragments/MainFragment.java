package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentMainBinding;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    FragmentMainBinding mainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainBinding = FragmentMainBinding.inflate(inflater);

        mainBinding.swipeRefreshLayout.setOnRefreshListener(this);

        return mainBinding.getRoot();
    }

    @Override
    public void onRefresh() {

    }
}
