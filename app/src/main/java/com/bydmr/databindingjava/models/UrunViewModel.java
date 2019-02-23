package com.bydmr.databindingjava.models;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bydmr.databindingjava.BR;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

// Observer sayesinde değişkende yapılmış bir değişiklik direkt olarak UI'a yansır
public class UrunViewModel extends BaseObservable {

    private Urun urun;
    private int miktar;
    private boolean resimYuklendiMi;



    // getter'lara @Bindable annotation koy
    @Bindable
    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
        notifyPropertyChanged(BR.urun); // bu property değişirse notify et
    }

    @Bindable
    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
        notifyPropertyChanged(BR.miktar);
    }

    @Bindable
    public boolean getResimYuklendiMi() {
        return resimYuklendiMi;
    }

    public void setResimYuklendiMi(boolean resimYuklendiMi) {
        this.resimYuklendiMi = resimYuklendiMi;
        notifyPropertyChanged(BR.resimYuklendiMi);
    }

    // xml üzerinden urunViewModel.requestListener şeklinde ulaşabilirsin çünkü bu bir getter method
    // kotlin gibi çağırmak istediğinde get atama yapmak istediğinde set
    public RequestListener getRequestListener() {

        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                setResimYuklendiMi(false);
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                setResimYuklendiMi(true);
                return false;
            }
        };
    }
}
