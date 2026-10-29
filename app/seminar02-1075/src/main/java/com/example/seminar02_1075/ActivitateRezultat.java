package com.example.seminar02_1075;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ActivitateRezultat extends AppCompatActivity {

    private int sumaCalculata = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitate_rezultat);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mesaj = extras.getString("MESAJ");
            int val1 = extras.getInt("VAL_1");
            int val2 = extras.getInt("VAL_2");

            sumaCalculata = val1 + val2;
            Toast.makeText(this, "Mesaj: " + mesaj + "\nVal1: " + val1 + ", Val2: " + val2, Toast.LENGTH_LONG).show();
        }

        Button btnInapoi = findViewById(R.id.btnTrimiteInapoi);
        btnInapoi.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("MESAJ_INTOARCERE", "Calcul finalizat");
            resultIntent.putExtra("SUMA", sumaCalculata);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}