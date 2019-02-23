package com.bydmr.databindingjava.datas;

import android.content.Context;
import android.content.SharedPreferences;

import com.bydmr.databindingjava.models.Urun;

import java.util.HashSet;
import java.util.Set;

public class MyPreference {

    private static MyPreference myPreference;
    private SharedPreferences sharedPreferences;

    private MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("myPreference", Context.MODE_PRIVATE);
    }

    public static MyPreference getInstance (Context context) {
        if (myPreference == null) {
            myPreference = new MyPreference(context);
        }
        return myPreference;
    }

    public void setSepet(Urun urun, int miktar) {
        // Set oluşturuyorum daha önce eklenmiş olan varsa eklenmesin, var olan alınsın diye getStringSet diyerek alıyorum
        Set<String> seriNumaralari = sharedPreferences.getStringSet("sepet_set", new HashSet<String>());
        seriNumaralari.add(String.valueOf(urun.getSeriNumarasi()));

        // aynı değere sahip birden fazla değeri saklamaz (set mantığı)
        sharedPreferences.edit().putStringSet("sepet_set", seriNumaralari).apply();

        // seri numarası key-value olarak sakladım buradan veriyi alip üzerine yazıyorum
        int suankiMiktar = sharedPreferences.getInt(String.valueOf(urun.getSeriNumarasi()), 0);
        sharedPreferences.edit().putInt(String.valueOf(urun.getSeriNumarasi()), (suankiMiktar + miktar)).apply();
    }
}
