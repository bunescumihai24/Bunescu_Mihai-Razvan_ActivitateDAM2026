package com.example.seminar04_1075;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaMobilaActivity extends AppCompatActivity {

    private ArrayList<Mobila> listaMobila;
    private ArrayAdapter<Mobila> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mobila);

        ListView lvMobila = findViewById(R.id.lvMobila);


        listaMobila = getIntent().getParcelableArrayListExtra("LISTA_MOBILA");
        if (listaMobila == null) {
            listaMobila = new ArrayList<>();
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMobila);
        lvMobila.setAdapter(adapter);


        lvMobila.setOnItemClickListener((parent, view, position, id) -> {
            Mobila mobilaSelectata = listaMobila.get(position);
            Toast.makeText(ListaMobilaActivity.this, "Ai selectat:\n" + mobilaSelectata.toString(), Toast.LENGTH_SHORT).show();
        });


        lvMobila.setOnItemLongClickListener((parent, view, position, id) -> {

            listaMobila.remove(position);
            adapter.notifyDataSetChanged();

            Toast.makeText(ListaMobilaActivity.this, "Obiectul a fost șters!", Toast.LENGTH_SHORT).show();


            return true;
        });
    }
}