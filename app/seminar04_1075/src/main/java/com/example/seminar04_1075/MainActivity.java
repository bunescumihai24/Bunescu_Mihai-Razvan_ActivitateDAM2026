package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;


    private ArrayList<Mobila> listaMobila = new ArrayList<>();

    private final ActivityResultLauncher<Intent> addMobilaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Mobila mobilaNoua = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        mobilaNoua = result.getData().getParcelableExtra("OBIECT_MOBILA", Mobila.class);
                    } else {
                        mobilaNoua = (Mobila) result.getData().getParcelableExtra("OBIECT_MOBILA");
                    }

                    if (mobilaNoua != null) {

                        listaMobila.add(mobilaNoua);

                        tvRezultat.setText("Ultimul obiect adăugat:\n" + mobilaNoua.toString());
                        Toast.makeText(this, "Adăugat în listă cu succes!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnAdaugaAI = findViewById(R.id.btnAdaugaAI);
        Button btnVeziLista = findViewById(R.id.btnVeziLista);
        tvRezultat = findViewById(R.id.tvRezultat);

        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugaMobilaActivity.class);
            addMobilaLauncher.launch(intent);
        });

        btnAdaugaAI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugaMobilaActivity_AI.class);
            addMobilaLauncher.launch(intent);
        });

        //Butonul care trimite lista către a 3-a activitate
        btnVeziLista.setOnClickListener(v -> {
            if(listaMobila.isEmpty()) {
                Toast.makeText(this, "Lista este goală! Adaugă mobilă mai întâi.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intentLista = new Intent(MainActivity.this, ListaMobilaActivity.class);
            intentLista.putParcelableArrayListExtra("LISTA_MOBILA", listaMobila);
            startActivity(intentLista);
        });
    }
}