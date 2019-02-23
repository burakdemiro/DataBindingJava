package com.bydmr.databindingjava.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentUrunDetayBinding;
import com.bydmr.databindingjava.interfaces.IMainActivity;
import com.bydmr.databindingjava.models.Urun;
import com.bydmr.databindingjava.models.UrunViewModel;
import com.bydmr.databindingjava.utils.Urunler;

import androidx.fragment.app.Fragment;

public class UrunDetayFragment extends Fragment {

    public FragmentUrunDetayBinding fragmentUrunDetayBinding;
    Urun gelenUrun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentUrunDetayBinding = FragmentUrunDetayBinding.inflate(inflater);

        if (getArguments() != null) {
            gelenUrun = getArguments().getParcelable("secilen_urun");

            UrunViewModel urunViewModel = new UrunViewModel();
            urunViewModel.setUrun(gelenUrun);
            urunViewModel.setMiktar(1);
            urunViewModel.setResimYuklendiMi(false);

            fragmentUrunDetayBinding.setUrunViewModel(urunViewModel);
            fragmentUrunDetayBinding.setMainInterface((IMainActivity) getActivity());
        }

        return fragmentUrunDetayBinding.getRoot(); // zaten o view öğesini aynı şekilde geri döndürmüş oluyor
    }

}
