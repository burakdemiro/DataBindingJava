package com.bydmr.databindingjava.databindings;

import com.bydmr.databindingjava.adapters.SepetRVAdapter;
import com.bydmr.databindingjava.models.SepetUrun;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SepetFragmentBindingAdapters {

    @BindingAdapter("sepetListesiniAta")
    // 1.parametre: bunu nerede çağırdın
    // 2.parametre: buna parametre olarak ne geçtin?
    public static void setSepetListesi(RecyclerView recyclerView, List<SepetUrun> sepetUrunler) {

        if (sepetUrunler == null)
            return;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext())); // Default Vertical
        }

        SepetRVAdapter adapter = (SepetRVAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new SepetRVAdapter(sepetUrunler, recyclerView.getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.sepetUrunleriniGuncelle(sepetUrunler);
        }
    }
}
