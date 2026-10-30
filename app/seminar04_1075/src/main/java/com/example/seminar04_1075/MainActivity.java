package com.example.seminar04_1075;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;


    private final ActivityResultLauncher<Intent> addMobilaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Mobila mobilaNoua = result.getData().getParcelableExtra("OBIECT_MOBILA");

                    if (mobilaNoua != null) {
                        tvRezultat.setText(mobilaNoua.toString());
                        Toast.makeText(this, "Obiect preluat cu succes!", Toast.LENGTH_SHORT).show();
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
        tvRezultat = findViewById(R.id.tvRezultat);


        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugaMobilaActivity.class);
            addMobilaLauncher.launch(intent);
        });

        btnAdaugaAI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugaMobilaActivity_AI.class);
            addMobilaLauncher.launch(intent);
        });
    }
}