package com.bydmr.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.bydmr.databindingjava.databinding.ActivityMainBinding;
import com.bydmr.databindingjava.fragments.MainFragment;
import com.bydmr.databindingjava.fragments.MiktarDialogFragment;
import com.bydmr.databindingjava.fragments.UrunDetayFragment;
import com.bydmr.databindingjava.interfaces.IMainActivity;
import com.bydmr.databindingjava.models.Urun;

public class MainActivity extends AppCompatActivity implements IMainActivity {

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

        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.anaContainer, mainFragment, "mainFragment");
        transaction.commit();
    }

    @Override
    public void secilenUruneGit(Urun urun) {
        Log.i("etiket", urun.getBaslik());

        UrunDetayFragment urunDetayFragment = new UrunDetayFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("secilen_urun", urun);

        urunDetayFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.anaContainer, urunDetayFragment, "urunDetayFragment");
        transaction.addToBackStack("urunDetayFragment");
        transaction.commit();
    }

    @Override
    public void miktarDialogBaslat() {

        MiktarDialogFragment miktarDialogFragment = new MiktarDialogFragment();
        miktarDialogFragment.show(getSupportFragmentManager(), "miktarDialogFragment");
    }

    @Override
    public void setMiktar(int miktar) {

        UrunDetayFragment urunDetayFragment = (UrunDetayFragment) getSupportFragmentManager().findFragmentByTag("urunDetayFragment");

        if (urunDetayFragment != null) {

            urunDetayFragment.fragmentUrunDetayBinding.getUrunViewModel().setMiktar(miktar);
        }
    }
}
