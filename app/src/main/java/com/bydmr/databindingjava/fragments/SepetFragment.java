package com.bydmr.databindingjava.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.FragmentSepetBinding;
import com.bydmr.databindingjava.interfaces.IMainActivity;
import com.bydmr.databindingjava.models.SepetUrun;
import com.bydmr.databindingjava.models.SepetUrunViewModel;
import com.bydmr.databindingjava.utils.Urunler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.fragment.app.Fragment;

public class SepetFragment extends Fragment {

    FragmentSepetBinding fragmentSepetBinding;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        fragmentSepetBinding = FragmentSepetBinding.inflate(inflater);

        fragmentSepetBinding.setMainInterface((IMainActivity) context);

        fragmentSepetBinding.getMainInterface().sepetGorunecekMi(true);

        sepetiGetir();

        return fragmentSepetBinding.getRoot();
    }

    private void sepetiGetir() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        Set<String> seriNumaralari = sharedPreferences.getStringSet("sepet_set", new HashSet<String>());

        SepetUrunViewModel sepetUrunViewModel = new SepetUrunViewModel();

        List<SepetUrun> sepetUrunleri = new ArrayList<>();
        Urunler urunler = new Urunler();

        for (String seriNumarasi : seriNumaralari) {
            int miktar = sharedPreferences.getInt(seriNumarasi, 0);

            SepetUrun eklenecekSepetUrun = new SepetUrun();
            eklenecekSepetUrun.setMiktar(miktar);
            eklenecekSepetUrun.setUrun(urunler.tumUrunlerMap.get(seriNumarasi));

            sepetUrunleri.add(eklenecekSepetUrun);
        }

        sepetUrunViewModel.setSepettekiUrunler(sepetUrunleri);
        fragmentSepetBinding.setSepetUrunViewModel(sepetUrunViewModel);
    }

    @Override
    public void onDestroy() {
        ((IMainActivity)context).sepetGorunecekMi(false);
        super.onDestroy();
    }

    public void sepetListesiniGuncelle() {
        sepetiGetir();
    }
}
