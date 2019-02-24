package com.bydmr.databindingjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bydmr.databindingjava.databinding.ActivityMainBinding;
import com.bydmr.databindingjava.datas.MyPreference;
import com.bydmr.databindingjava.fragments.MainFragment;
import com.bydmr.databindingjava.fragments.MiktarDialogFragment;
import com.bydmr.databindingjava.fragments.SepetFragment;
import com.bydmr.databindingjava.fragments.UrunDetayFragment;
import com.bydmr.databindingjava.interfaces.IMainActivity;
import com.bydmr.databindingjava.models.SepetUrun;
import com.bydmr.databindingjava.models.SepetUrunViewModel;
import com.bydmr.databindingjava.models.Urun;
import com.bydmr.databindingjava.utils.Urunler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    // Pascal versiyon adlandırma
    // activity_urunler -> ActivityUrunlerBinding üretilir
    ActivityMainBinding mainBinding;
    boolean cikmakIcinTikla;

    MyPreference myPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();

        sepetBilgileriniGuncelle();
    }

    private void init() {

        myPreference = MyPreference.getInstance(this);

        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.anaContainer, mainFragment, "mainFragment");
        transaction.commit();

        mainBinding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SepetFragment sepetFragment = (SepetFragment) getSupportFragmentManager().findFragmentByTag("sepetFragment");
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();

                if (sepetFragment == null) {
                    sepetFragment = new SepetFragment();
                    transaction1.replace(R.id.anaContainer, sepetFragment, "sepetFragment");
                    transaction1.addToBackStack("sepetFragment");
                    transaction1.commit();
                }
            }
        });

        mainBinding.btnTamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBinding.progressBar.setVisibility(View.VISIBLE);
                new CountDownTimer(2000, 2000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        mainBinding.progressBar.setVisibility(View.INVISIBLE);
                        sepettekiUrunleriSil();
                    }
                }.start();
            }
        });
    }

    private void sepettekiUrunleriSil() {

        SharedPreferences sharedPreferences = this.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        Set<String> seriNumaralari = sharedPreferences.getStringSet("sepet_set", new HashSet<String>());

        for (String seriNumarasi : seriNumaralari) {
            sharedPreferences.edit().remove(seriNumarasi).apply();
        }
        sharedPreferences.edit().remove("sepet_set").apply();

        Toast.makeText(this, "Teşekkürler", Toast.LENGTH_SHORT).show();

        anaSayfayaGit();
        sepetBilgileriniGuncelle();
    }

    private void anaSayfayaGit() {

        getSupportFragmentManager().popBackStack(); // geri gelir
        SepetFragment sepetFragment = (SepetFragment) getSupportFragmentManager().findFragmentByTag("sepetFragment");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (sepetFragment != null) {
            transaction.remove(sepetFragment); // geri gelmek yetmez yığından kaldırır
        }

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

    @Override
    public void sepeteUrunEkle(Urun urun, int miktar) {
        myPreference.setSepet(urun, miktar);
        setMiktar(1);
        sepetBilgileriniGuncelle();
    }

    @Override
    public void sepetGorunecekMi(boolean gorunurluk) {

        mainBinding.getSepetUrunViewModel().setSepetGorunurlugu(gorunurluk);
    }

    @Override
    public void sepetiGuncelle(Urun urun, int miktar) {

        SharedPreferences sharedPreferences = this.getSharedPreferences("myPreference", Context.MODE_PRIVATE);

        int suankiMiktar = sharedPreferences.getInt(String.valueOf(urun.getSeriNumarasi()), 0);
        sharedPreferences.edit().putInt(String.valueOf(urun.getSeriNumarasi()), suankiMiktar + miktar).apply();
        sepetBilgileriniGuncelle();
    }

    @Override
    public void urunuSepettenSil(SepetUrun sepetUrun) {

        SharedPreferences sharedPreferences = this.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(String.valueOf(sepetUrun.getUrun().getSeriNumarasi())).apply();

        Set<String> seriNumaralari = sharedPreferences.getStringSet("sepet_set", new HashSet<String>());

        if (seriNumaralari.size() == 1) {
            sharedPreferences.edit().remove("sepet_set").apply();
        } else {
            seriNumaralari.remove(String.valueOf(sepetUrun.getUrun().getSeriNumarasi()));
            sharedPreferences.edit().putStringSet("sepet_set", seriNumaralari).apply();
        }
        sepetBilgileriniGuncelle();

        SepetFragment sepetFragment = (SepetFragment) getSupportFragmentManager().findFragmentByTag("sepetFragment");

        if (sepetFragment != null) {

            sepetFragment.sepetListesiniGuncelle();
        }
    }

    public void sepetBilgileriniGuncelle() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        Set<String> seriNumaralari = sharedPreferences.getStringSet("sepet_set", new HashSet<String>());

        boolean sepetAcikMiydi = false;
        try {
            sepetAcikMiydi = mainBinding.getSepetUrunViewModel().isSepetGorunurlugu();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SepetUrunViewModel sepetUrunViewModel = new SepetUrunViewModel();
        sepetUrunViewModel.setSepetGorunurlugu(sepetAcikMiydi);

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
        mainBinding.setSepetUrunViewModel(sepetUrunViewModel);
    }

    @Override
    public void onBackPressed() {

        int backStackFraSayisi = getSupportFragmentManager().getBackStackEntryCount();

        if (backStackFraSayisi == 0 && cikmakIcinTikla) {
            super.onBackPressed();
        }

        if (backStackFraSayisi == 0 && !cikmakIcinTikla) {
            Toast.makeText(this, "Çıkmak için tekrar tıklayın.", Toast.LENGTH_SHORT).show();
            cikmakIcinTikla = true;
        } else {
            cikmakIcinTikla = false;
            super.onBackPressed();
        }
    }
}
