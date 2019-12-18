package com.puja.accustorelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Spek extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spek);
        String judul = this.getIntent().getStringExtra("judul");
        getSupportActionBar().setTitle(judul);

        CardView btnArtikel = (CardView) findViewById(R.id.mobil);
        btnArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Spek.this, Artikel.class);
                intent.putExtra("tipe","spek");
                intent.putExtra("judul","Aki Mobil");
                startActivity(intent);
            }
        });

        CardView btnSpek = (CardView) findViewById(R.id.motor);
        btnSpek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Spek.this, Artikel.class);
                intent.putExtra("tipe","speks");
                intent.putExtra("judul","Aki Motor");
                startActivity(intent);
            }
        });
    }
}
