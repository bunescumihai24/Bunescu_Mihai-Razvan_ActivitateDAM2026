package com.example.seminar02_1075;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class NouaActivitate extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> activitateTertiaraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String mesajPrimit = result.getData().getStringExtra("MESAJ_INTOARCERE");
                    int sumaCalculata = result.getData().getIntExtra("SUMA", 0);
                    Toast.makeText(this, "Primit: " + mesajPrimit + " | Suma: " + sumaCalculata, Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noua_activitate);

        Button btnDeschide = findViewById(R.id.btnDeschideAplicatie);

        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(NouaActivitate.this, ActivitateRezultat.class);

            Bundle bundle = new Bundle();
            bundle.putString("MESAJ", "Salutare din ActivitateSecundara!");
            bundle.putInt("VAL_1", 15);
            bundle.putInt("VAL_2", 25);
            intent.putExtras(bundle);

            activitateTertiaraLauncher.launch(intent);
        });
    }
}