package com.example.seminar04_1075;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdaugaMobilaActivity_AI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_adauga_mobila_ai);


        EditText etNume = findViewById(R.id.etNume);
        Spinner spinner = findViewById(R.id.spCategorie);
        CheckBox cbAsamblata = findViewById(R.id.cbAsamblata);
        RadioGroup rgMaterial = findViewById(R.id.rgMaterial);
        RatingBar ratingBar = findViewById(R.id.rbCalitate);
        Switch switchStoc = findViewById(R.id.swStoc);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);



        String[] categorii = {"Living", "Bucătărie", "Dormitor", "Baie"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorii);
        spinner.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String nume = etNume.getText().toString();

            if(nume.isEmpty()){
                Toast.makeText(this, "Introdu denumirea produsului!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Preluare Categorie
            String categorie = spinner.getSelectedItem() != null ? spinner.getSelectedItem().toString() : "Nedefinit";

            // Preluare CheckBox
            boolean esteAsamblata = cbAsamblata.isChecked();

            // Preluare RadioButton
            int selectedId = rgMaterial.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String material = selectedRb != null ? selectedRb.getText().toString() : "Necunoscut";

            // Preluare RatingBar și Switch
            float rating = ratingBar.getRating();
            boolean inStoc = switchStoc.isChecked();


            Mobila mobila = new Mobila(nume, esteAsamblata, material,categorie, rating, inStoc);

            // Transmiterea datelor înapoi către MainActivity
            Intent intentResult = new Intent();
            intentResult.putExtra("OBIECT_MOBILA", mobila);
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }
}