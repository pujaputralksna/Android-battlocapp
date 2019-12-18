package com.puja.accustorelocation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Holder extends RecyclerView.ViewHolder {
    public TextView tvJudul;
    public ImageView imgView;
    public View mView;

    public Holder(View v) {
        super(v);
        tvJudul = (TextView)itemView.findViewById(R.id.txtArtikel);
        imgView = (ImageView)itemView.findViewById(R.id.gbArtikel);
        mView = v;
    }
    public void setJudul(String judul) { tvJudul.setText(judul);}
    public void setIkon(String ikon) { Picasso.get().load(ikon).into(imgView);}
}
