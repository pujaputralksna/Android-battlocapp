package com.puja.accustorelocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tentang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);
        String judul = this.getIntent().getStringExtra("judul");
        getSupportActionBar().setTitle(judul);
    }
}
