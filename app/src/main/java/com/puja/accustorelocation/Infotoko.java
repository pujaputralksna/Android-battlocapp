package com.puja.accustorelocation;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Infotoko extends AppCompatActivity {
    Bundle extrass;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infotoko);

        extras = this.getIntent().getExtras();
        extrass = new Bundle();

        Button btnMap = (Button) findViewById(R.id.btmap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Infotoko.this, MapsActivity.class);
                extrass.putDouble("lat", extras.getDouble("lat"));
                extrass.putDouble("lang", extras.getDouble("lang"));
                extrass.putString("judul", extras.getString("judul"));
                intent.putExtras(extrass);
                startActivity(intent);
            }
        });

        Button btnList = findViewById(R.id.btlist);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Infotoko.this, Spek.class);
                startActivity(intent);
            }
        });


        String judul = extras.getString("judul");
        String alamat = extras.getString("alamat");
        String telp = extras.getString("telp");
        String url = extras.getString("url");
        TextView tvJudul = (TextView)findViewById(R.id.textView3);
        TextView tvAlamat = (TextView)findViewById(R.id.alamat);
        TextView tvTelp = (TextView)findViewById(R.id.notelp);
        ImageView imgView = (ImageView)findViewById(R.id.gbtoko);
        tvJudul.setText(judul);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
        Picasso.get().load(url).into(imgView);
    }
}
