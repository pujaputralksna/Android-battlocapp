package com.puja.accustorelocation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Artikel extends AppCompatActivity {

    public FirebaseRecyclerAdapter<Data, Holder> mAdapter;
    Intent intent;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        RecyclerView messagesView = (RecyclerView) findViewById(R.id.RCView);

        intent = new Intent(this, IsiArtikel.class);
        extras = new Bundle();

        String tipe = this.getIntent().getStringExtra("tipe");

        if (tipe.equals("spek")) {
            String judul = this.getIntent().getStringExtra("judul");
            getSupportActionBar().setTitle(judul);
        }
        else if (tipe.equals("speks")) {
            String judul = this.getIntent().getStringExtra("judul");
            getSupportActionBar().setTitle(judul);
        }
        else {
            getSupportActionBar().setTitle("Artikel");
        }

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(tipe);

        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(query, Data.class)
                        .build();
        messagesView.setHasFixedSize(false);
        messagesView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FirebaseRecyclerAdapter<Data, Holder>(options) {

            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card, parent, false);

                return new Holder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull Holder viewHolder, int position, @NonNull final Data model) {
                viewHolder.setJudul(model.getJudul());
                viewHolder.setIkon(model.getIkon());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        extras.putString("judul", model.getJudul());
                        extras.putString("isi", model.getIsi().replace("_b","\n"));
                        extras.putString("url", model.getUrl());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
        };
        messagesView.setAdapter(mAdapter);
        mAdapter.startListening();
    }
}