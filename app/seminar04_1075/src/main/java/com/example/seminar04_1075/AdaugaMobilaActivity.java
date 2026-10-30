package com.example.seminar04_1075;


import android.content.Intent;
import android.os.Bundle;
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

public class AdaugaMobilaActivity extends AppCompatActivity {

    private EditText etNume;
    private CheckBox cbAsamblata;
    private RadioGroup rgMaterial;
    private Spinner spCategorie;
    private RatingBar rbCalitate;
    private Switch swStoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_adauga_mobila);

        etNume = findViewById(R.id.etNume);
        cbAsamblata = findViewById(R.id.cbAsamblata);
        rgMaterial = findViewById(R.id.rgMaterial);
        spCategorie = findViewById(R.id.spCategorie);
        rbCalitate = findViewById(R.id.rbCalitate);
        swStoc = findViewById(R.id.swStoc);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(v -> {
            String nume = etNume.getText().toString().trim();
            if (nume.isEmpty()) {
                Toast.makeText(this, "Introduceți un nume!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean esteAsamblata = cbAsamblata.isChecked();

            int selectedId = rgMaterial.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedId);
            String material = selectedRb != null ? selectedRb.getText().toString() : "Nespecificat";

            String categorie = spCategorie.getSelectedItem().toString();
            float calitate = rbCalitate.getRating();
            boolean inStoc = swStoc.isChecked();


            Mobila mobila = new Mobila(nume, esteAsamblata, material, categorie, calitate, inStoc);


            Intent intentResult = new Intent();
            intentResult.putExtra("OBIECT_MOBILA", mobila);
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }
}