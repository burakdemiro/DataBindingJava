package com.bydmr.databindingjava.models;

import android.util.Log;

import com.bydmr.databindingjava.BR;

import java.util.List;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SepetUrunViewModel extends BaseObservable {

    private List<SepetUrun> sepettekiUrunler;
    private boolean sepetGorunurlugu; // Default value is false

    @Bindable
    public List<SepetUrun> getSepettekiUrunler() {
        return sepettekiUrunler;
    }

    @Bindable
    public boolean isSepetGorunurlugu() {
        return sepetGorunurlugu;
    }

    public void setSepettekiUrunler(List<SepetUrun> sepettekiUrunler) {
        this.sepettekiUrunler = sepettekiUrunler;
        notifyPropertyChanged(BR.sepettekiUrunler);
    }

    public void setSepetGorunurlugu(boolean sepetGorunurlugu) {
        this.sepetGorunurlugu = sepetGorunurlugu;
        notifyPropertyChanged(BR.sepetGorunurlugu);
    }

    public String tumUrunSayisiniBul() {

        int toplamUrunSayisi = 0;

        for (SepetUrun sepetUrun : sepettekiUrunler) {
            toplamUrunSayisi += sepetUrun.getMiktar();
        }

        return "Sepette " + toplamUrunSayisi + " ürün var. Toplam: ";
    }

    public String tumUrunlerinFiyati() {

        double toplamTutar = 0;

        for (SepetUrun sepetUrun : sepettekiUrunler) {
            if (sepetUrun.getUrun().kampanyaliSatisVarmi()) {
                toplamTutar += sepetUrun.getUrun().getKampanyaliFiyat() * sepetUrun.getMiktar();
            } else {
                toplamTutar += sepetUrun.getUrun().getFiyat() * sepetUrun.getMiktar();
            }
        }

        String yeniGosterim = String.format("%.2f", toplamTutar);

        return yeniGosterim + " TL";
    }
}
