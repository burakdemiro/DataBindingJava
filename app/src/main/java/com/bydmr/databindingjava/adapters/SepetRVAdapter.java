package com.bydmr.databindingjava.adapters;

import android.content.Context;
import android.database.DatabaseUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bydmr.databindingjava.R;
import com.bydmr.databindingjava.databinding.TekSatirSepetUrunBinding;
import com.bydmr.databindingjava.models.SepetTekUrunViewModel;
import com.bydmr.databindingjava.models.SepetUrun;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SepetRVAdapter extends RecyclerView.Adapter<SepetRVAdapter.MyViewHolder> {

    List<SepetUrun> sepetUrunleri;
    Context context;

    public SepetRVAdapter(List<SepetUrun> sepetUrunleri, Context context) {
        this.sepetUrunleri = sepetUrunleri;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TekSatirSepetUrunBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.tek_satir_sepet_urun, parent, false);

        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SepetTekUrunViewModel sepetTekUrunViewModel = new SepetTekUrunViewModel();
        sepetTekUrunViewModel.setSepetUrun(sepetUrunleri.get(position));

        holder.binding.setTekSepetUrun(sepetTekUrunViewModel);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return sepetUrunleri.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TekSatirSepetUrunBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void sepetUrunleriniGuncelle(List<SepetUrun> yeniListe) {
        sepetUrunleri.clear();
        sepetUrunleri.addAll(yeniListe);
        notifyDataSetChanged();
    }
}
