package com.puja.accustorelocation;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.puja.accustorelocation.util.Distance;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Loktoko extends AppCompatActivity {

    Intent intent;
    Bundle extras;
    double lat;
    double lng;
    LocationManager locationManager;
    LocationListener locationListener;
    RecyclerView messagesView;
    FirebaseRecyclerOptions<Data> options;
    ArrayList<Data> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        String judul = this.getIntent().getStringExtra("judul");
        getSupportActionBar().setTitle(judul);

        listData = new ArrayList<>();

        messagesView = (RecyclerView) findViewById(R.id.RCView);

        intent = new Intent(this, Infotoko.class);
        extras = new Bundle();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("loktoko");

        options = new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(query, Data.class)
                        .build();
        messagesView.setHasFixedSize(false);
        messagesView.setLayoutManager(new LinearLayoutManager(this));
        // Acquire a reference to the system Location Manager
         locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
         locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                lat = location.getLatitude();
                lng = location.getLongitude();

                locationManager.removeUpdates(locationListener);
                locationManager = null;
                getData();
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private void Ambil() {
        RecyclerView.Adapter<Holder> a = new RecyclerView.Adapter<Holder>() {
            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card, parent, false);

                return new Holder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull Holder viewHolder, final int position) {
                viewHolder.setJudul(listData.get(position).getJudul()+"\n" + "Perkiraan Jarak : " + (new DecimalFormat("#.#")).format(listData.get(position).getDistance()) + " KM"+"\n" + "Estimasi Waktu : " + (new DecimalFormat("#.#").format(listData.get(position).getDistance()/0.30) + " Menit"));
                viewHolder.setIkon(listData.get(position).getIkon());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        extras.putString("judul", listData.get(position).getJudul());
                        extras.putString("alamat", listData.get(position).getAlamat());
                        extras.putString("telp", listData.get(position).getTelp());
                        extras.putString("url", listData.get(position).getUrl());
                        extras.putDouble("lat", listData.get(position).getLat());
                        extras.putDouble("lang", listData.get(position).getLang());
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public int getItemCount() {
                return listData.size();
            }
        };
        messagesView.setAdapter(a);
    }

    private void getData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("loktoko").getRef();
        ChildEventListener b = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Data d = dataSnapshot.getValue(Data.class);
                double distance = Distance.calculate(lat,lng,d.getLat(),d.getLang());
                d.setDistance(distance);
                listData.add(d);
                Collections.sort(listData, new Comparator<Data>() {
                    @Override
                    public int compare(Data o1, Data o2) {
                        int d1 = (int)( o1.getDistance() * 100);

                        int d2 = (int)( o2.getDistance() * 100);
                        return d1 - d2;
                    }
                });
                Ambil();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        ref.addChildEventListener(b);
    }
}