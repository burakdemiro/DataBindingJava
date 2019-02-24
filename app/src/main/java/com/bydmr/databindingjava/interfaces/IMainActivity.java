package com.bydmr.databindingjava.interfaces;

import com.bydmr.databindingjava.models.SepetUrun;
import com.bydmr.databindingjava.models.Urun;

public interface IMainActivity {

    void secilenUruneGit(Urun urun);

    void miktarDialogBaslat();

    void setMiktar(int miktar);

    void sepeteUrunEkle(Urun urun, int miktar);

    void sepetGorunecekMi(boolean gorunurluk);

    void sepetiGuncelle(Urun urun, int miktar);

    void urunuSepettenSil(SepetUrun sepetUrun);
}
