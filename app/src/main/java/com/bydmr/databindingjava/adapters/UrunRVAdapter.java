package com.bydmr.databindingjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.TekUrunBinding;
import com.bydmr.databindingjava.interfaces.IMainActivity;
import com.bydmr.databindingjava.models.Urun;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UrunRVAdapter extends RecyclerView.Adapter<UrunRVAdapter.MyViewHolder> {

    private Context context;
    private List<Urun> tumUrunler;
    IMainActivity iMainActivity;

    public UrunRVAdapter(Context context, List<Urun> tumUrunler) {
        this.context = context;
        this.tumUrunler = tumUrunler;

        iMainActivity = (IMainActivity) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TekUrunBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.tek_urun, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.binding.setUrun(tumUrunler.get(position));
        holder.binding.setMainInterface(iMainActivity);
        holder.binding.executePendingBindings(); // bir sonraki satırın genişliğini bilmesi için oluşturur
    }

    @Override
    public int getItemCount() {
        return tumUrunler.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TekUrunBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Daha önceden inflate edilmiş ve veri atamak istenilen şeyler için bind metotu kullanılıyor
            binding = DataBindingUtil.bind(itemView);

        }
    }

    public void listeyiYenile(List<Urun> yeniListe) {
        tumUrunler.clear();
        tumUrunler.addAll(yeniListe);
        notifyDataSetChanged();
    }
}
