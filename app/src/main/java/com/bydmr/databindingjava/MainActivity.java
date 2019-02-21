package com.bydmr.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.bydmr.databindingjava.databinding.ActivityMainBinding;
import com.bydmr.databindingjava.fragments.MainFragment;
import com.bydmr.databindingjava.fragments.UrunDetayFragment;

public class MainActivity extends AppCompatActivity {

    // Pascal versiyon adlandırma
    // activity_urunler -> ActivityUrunlerBinding üretilir
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {

        /*UrunDetayFragment urunDetayFragment = new UrunDetayFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.anaContainer, urunDetayFragment, "urunDetayFragment");
        transaction.commit();*/

        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.anaContainer, mainFragment, "mainFragment");
        transaction.commit();
    }
}
