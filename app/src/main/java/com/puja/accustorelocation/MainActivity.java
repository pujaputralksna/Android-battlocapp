package com.puja.accustorelocation;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_GET_LOCATIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();

        CardView btnToko = (CardView) findViewById(R.id.loktoko);
        btnToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Loktoko.class);
                intent.putExtra("judul","Toko Aki");
                startActivity(intent);
            }
        });

        CardView btnArtikel = (CardView) findViewById(R.id.btartikel);
        btnArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Artikel.class);
                intent.putExtra("tipe","artikel");
                startActivity(intent);
            }
        });

        CardView btnSpek = (CardView) findViewById(R.id.btspek);
        btnSpek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Spek.class);
                intent.putExtra("judul","Spek dan Harga");
                startActivity(intent);
            }
        });

        CardView btnTentang = (CardView) findViewById(R.id.bttentang);
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Tentang.class);
                intent.putExtra("judul","Tentang");
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apakah anda yakin ingin keluar?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                        quit();
                    }
                }).create().show();
    }


    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(start);
    }

    public void getPermissions() {
        /* Check and Request permission */
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_GET_LOCATIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GET_LOCATIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    } else {
                    Toast.makeText(MainActivity.this, "Akses Lokasi Tidak Diizinkan", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
