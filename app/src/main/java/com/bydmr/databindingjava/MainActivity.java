package com.bydmr.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bydmr.databindingjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Pascal versiyon adlandırma
    // activity_urunler -> ActivityUrunlerBinding üretilir
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
