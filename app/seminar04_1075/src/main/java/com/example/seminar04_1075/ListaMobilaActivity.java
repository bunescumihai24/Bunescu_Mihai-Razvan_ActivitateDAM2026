package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaMobilaActivity extends AppCompatActivity {

    private ArrayList<Mobila> listaMobila;
    private MobilaAdapter adapter;


    private final ActivityResultLauncher<Intent> editMobilaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Mobila mobilaActualizata = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        mobilaActualizata = result.getData().getParcelableExtra("OBIECT_MOBILA", Mobila.class);
                    } else {
                        mobilaActualizata = (Mobila) result.getData().getParcelableExtra("OBIECT_MOBILA");
                    }


                    int pozitie = result.getData().getIntExtra("POZITIE_EDIT", -1);

                    if (mobilaActualizata != null && pozitie != -1) {

                        listaMobila.set(pozitie, mobilaActualizata);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Obiect modificat cu succes!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mobila);

        ListView lvMobila = findViewById(R.id.lvMobila);

        listaMobila = getIntent().getParcelableArrayListExtra("LISTA_MOBILA");
        if (listaMobila == null) {
            listaMobila = new ArrayList<>();
        }

        // Setăm noul Custom Adapter
        adapter = new MobilaAdapter(this, listaMobila);
        lvMobila.setAdapter(adapter);


        lvMobila.setOnItemClickListener((parent, view, position, id) -> {
            Mobila mobilaSelectata = listaMobila.get(position);

            // Trimitem obiectul către formularul de adăugare/editare

            Intent intent = new Intent(ListaMobilaActivity.this, AdaugaMobilaActivity.class);
            intent.putExtra("OBIECT_MOBILA_EDIT", mobilaSelectata);
            intent.putExtra("POZITIE_EDIT", position); // Trimitem și poziția

            editMobilaLauncher.launch(intent);
        });


        lvMobila.setOnItemLongClickListener((parent, view, position, id) -> {
            listaMobila.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Obiect șters!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}