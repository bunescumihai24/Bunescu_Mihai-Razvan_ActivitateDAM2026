package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GalerieMobilaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_mobila);

        ListView lvGalerie = findViewById(R.id.lvGalerie);

        // Populăm lista cu minim 5 obiecte (Imagine URL, Descriere, Link detalii)
        List<MobilaWebItem> listaItems = new ArrayList<>();

        listaItems.add(new MobilaWebItem(
                "https://images.unsplash.com/photo-1503602642458-232111445657?w=500",
                "Scaun Ergonomic de Birou",
                "https://ro.wikipedia.org/wiki/Scaun"
        ));

        listaItems.add(new MobilaWebItem(
                "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=500",
                "Canapea Elegantă din Piele",
                "https://ro.wikipedia.org/wiki/Canapea"
        ));

        listaItems.add(new MobilaWebItem(
                "https://images.unsplash.com/photo-1530018607912-eff2daa1bac4?w=500",
                "Masă din Lemn Masiv",
                "https://ro.wikipedia.org/wiki/Mas%C4%83_(mobil%C4%83)"
        ));

        listaItems.add(new MobilaWebItem(
                "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=500",
                "Pat Matrimonial Modern",
                "https://ro.wikipedia.org/wiki/Pat"
        ));

        listaItems.add(new MobilaWebItem(
                "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=500",
                "Dulap Outfit & Dressing",
                "https://ro.wikipedia.org/wiki/Dulap"
        ));

        MobilaWebAdapter adapter = new MobilaWebAdapter(this, listaItems);
        lvGalerie.setAdapter(adapter);

        // La click pe oricare item, deschidem WebViewActivity cu link-ul specific
        lvGalerie.setOnItemClickListener((parent, view, position, id) -> {
            MobilaWebItem itemSelectat = listaItems.get(position);
            Intent intent = new Intent(GalerieMobilaActivity.this, WebViewActivity.class);
            intent.putExtra("LINK_WEB", itemSelectat.getLinkWeb());
            startActivity(intent);
        });
    }
}