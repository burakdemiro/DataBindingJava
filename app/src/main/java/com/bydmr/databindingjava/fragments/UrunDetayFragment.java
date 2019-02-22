package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentUrunDetayBinding;
import com.bydmr.databindingjava.models.Urun;
import com.bydmr.databindingjava.utils.Urunler;

import androidx.fragment.app.Fragment;

public class UrunDetayFragment extends Fragment {

    FragmentUrunDetayBinding mBinding;
    Urun gelenUrun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = FragmentUrunDetayBinding.inflate(inflater);

        if (getArguments() != null) {
            gelenUrun = getArguments().getParcelable("secilen_urun");
            mBinding.setUrun(gelenUrun);
            mBinding.setMiktar(1);
        }

        return mBinding.getRoot(); // zaten o view öğesini aynı şekilde geri döndürmüş oluyor
    }

}
