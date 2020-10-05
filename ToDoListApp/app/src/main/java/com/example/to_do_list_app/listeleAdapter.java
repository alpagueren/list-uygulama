package com.example.to_do_list_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listeleAdapter extends RecyclerView.Adapter<listeleAdapter.PostHolder> {

    private ArrayList<String> yapilacakBaslikList;
    private ArrayList<String> yapilacakMetinList;
    private ArrayList<String> yapilacakEpostaList;
    private ArrayList<String> yapilacakOnemList;
    private ArrayList<String> yapilacakTarihList;
    private ArrayList<String> yapilacakSaatList;

    public listeleAdapter(ArrayList<String> yapilacakBaslikList, ArrayList<String> yapilacakMetinList, ArrayList<String> yapilacakEpostaList, ArrayList<String> yapilacakOnemList, ArrayList<String> yapilacakTarihList, ArrayList<String> yapilacakSaatList) {
        this.yapilacakBaslikList = yapilacakBaslikList;
        this.yapilacakMetinList = yapilacakMetinList;
        this.yapilacakEpostaList = yapilacakEpostaList;
        this.yapilacakOnemList = yapilacakOnemList;
        this.yapilacakTarihList = yapilacakTarihList;
        this.yapilacakSaatList = yapilacakSaatList;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_activity,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.baslik.setText(yapilacakBaslikList.get(position));
        holder.metin.setText(yapilacakMetinList.get(position));
        holder.eposta.setText(yapilacakEpostaList.get(position));
        holder.onem.setText(yapilacakOnemList.get(position));
        holder.tarih.setText(yapilacakTarihList.get(position));
        holder.saat.setText(yapilacakSaatList.get(position));

    }

    @Override
    public int getItemCount() {
        return yapilacakBaslikList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView baslik;
        TextView metin;
        TextView eposta;
        TextView onem;
        TextView tarih;
        TextView saat;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.baslik);
            metin = itemView.findViewById(R.id.lmetin);
            eposta = itemView.findViewById(R.id.leposta);
            onem = itemView.findViewById(R.id.lonem);
            tarih = itemView.findViewById(R.id.ltarih);
            saat = itemView.findViewById(R.id.lsaat);

        }
    }
}
