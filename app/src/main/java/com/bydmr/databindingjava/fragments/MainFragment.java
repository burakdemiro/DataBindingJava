package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.adapters.UrunRVAdapter;
import com.bydmr.databindingjava.databinding.FragmentMainBinding;
import com.bydmr.databindingjava.models.Urun;
import com.bydmr.databindingjava.utils.Urunler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    FragmentMainBinding mainBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainBinding = FragmentMainBinding.inflate(inflater);

        mainBinding.swipeRefreshLayout.setOnRefreshListener(this);

        setupUrunlerList();

        return mainBinding.getRoot();
    }

    private void setupUrunlerList() {
        Urunler urunler = new Urunler();
        List<Urun> tumUrunler = new ArrayList<>();
        tumUrunler.addAll(Arrays.asList(urunler.tumUrunlerDizisi));
        mainBinding.setUrunler(tumUrunler);
    }

    @Override
    public void onRefresh() {
        Urunler urunler = new Urunler();
        List<Urun> yeniUrunler = new ArrayList<>();
        yeniUrunler.addAll(Arrays.asList(urunler.tumUrunlerDizisi));
        ((UrunRVAdapter) mainBinding.rvTumUrunler.getAdapter()).listeyiYenile(yeniUrunler);
        mainBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
