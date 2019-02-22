package com.bydmr.databindingjava.databindings;

import com.bydmr.databindingjava.adapters.UrunRVAdapter;
import com.bydmr.databindingjava.models.Urun;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Custom Binding Adapter
public class MainFragmentBindingAdapters {

    @BindingAdapter("urunlerListesiniAta") // app:urunlerListesiniAta
    // 1.parametre: bunu nerede çağırdın
    // 2.parametre: buna parametre olarak ne geçtin?
    public static void setUrunlerListesi(RecyclerView recyclerView, List<Urun> urunler) {

        if (urunler == null)
            return;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        }

        UrunRVAdapter adapter = (UrunRVAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new UrunRVAdapter(recyclerView.getContext(), urunler);
            recyclerView.setAdapter(adapter);
        }
    }
}
