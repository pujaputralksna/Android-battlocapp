package com.puja.accustorelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class IsiArtikel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_artikel);

        Bundle extras = this.getIntent().getExtras();

        String judul = extras.getString("judul");
        String isi = extras.getString("isi");
        String url = extras.getString("url");
        TextView tvJudul = (TextView)findViewById(R.id.textView3);
        TextView tvIsi = (TextView)findViewById(R.id.textView4);
        ImageView imgView = (ImageView)findViewById(R.id.gambar1);

        Button btListtk = findViewById(R.id.btlisttk);
        btListtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IsiArtikel.this, Loktoko.class);
                startActivity(intent);
            }
        });

        tvJudul.setText(judul);
        tvIsi.setText(isi);

        Picasso.get().load(url).into(imgView);
    }
}
