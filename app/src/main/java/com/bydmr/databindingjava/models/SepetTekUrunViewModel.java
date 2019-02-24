package com.bydmr.databindingjava.models;

import android.content.Context;
import android.util.Log;

import com.bydmr.databindingjava.BR;
import com.bydmr.databindingjava.interfaces.IMainActivity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SepetTekUrunViewModel extends BaseObservable {

    private SepetUrun sepetUrun;

    @Bindable
    public SepetUrun getSepetUrun() {
        return sepetUrun;
    }

    public void setSepetUrun(SepetUrun sepetUrun) {
        this.sepetUrun = sepetUrun;
        notifyPropertyChanged(BR.sepetUrun);
    }

    // SepetUrun'e için
    // set ettin get ile bu field'ı okuyan tüm widgetlara notify gitti
    // hepsinin değeri güncellenir
    public String miktariGetir(SepetUrun sepetUrun) {
        int miktar = sepetUrun.getMiktar();
        return "Miktar: " +miktar;
    }

    public void miktariArtir(Context context) {

        SepetUrun sepetUrun = getSepetUrun();
        sepetUrun.setMiktar(sepetUrun.getMiktar() + 1);
        setSepetUrun(sepetUrun); // notify yayınlanır

        IMainActivity iMainActivity = (IMainActivity) context;
        iMainActivity.sepetiGuncelle(sepetUrun.getUrun(), 1);
    }

    public void miktariAzalt(Context context) {

        IMainActivity iMainActivity = (IMainActivity) context;
        SepetUrun sepetUrun = getSepetUrun();
        if (sepetUrun.getMiktar() > 1) {
            sepetUrun.setMiktar(sepetUrun.getMiktar() - 1);

            setSepetUrun(sepetUrun); // notify yayınlanır

            iMainActivity.sepetiGuncelle(sepetUrun.getUrun(), -1);
        } else if (sepetUrun.getMiktar() == 1) {
            sepetUrun.setMiktar(sepetUrun.getMiktar() - 1);
            setSepetUrun(sepetUrun);
            iMainActivity.urunuSepettenSil(sepetUrun);
        }

    }
}
