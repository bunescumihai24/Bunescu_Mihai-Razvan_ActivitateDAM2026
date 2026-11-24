package com.example.seminar09_1075;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {

    private final String API_KEY = "APy";
    private EditText etOras;
    private Spinner spZile;
    private Button btnCautaVreme;
    private TextView tvRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vreme);

        etOras = findViewById(R.id.etOras);
        spZile = findViewById(R.id.spZile);
        btnCautaVreme = findViewById(R.id.btnCautaVreme);
        tvRezultat = findViewById(R.id.tvRezultat);

        String[] optiuni = {"1 zi", "5 zile"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, optiuni);
        spZile.setAdapter(adapter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        btnCautaVreme.setOnClickListener(v -> {
            String oras = etOras.getText().toString().trim();
            if (oras.isEmpty()) {
                Toast.makeText(this, "Introdu un oras", Toast.LENGTH_SHORT).show();
                return;
            }

            int pozitieSpinner = spZile.getSelectedItemPosition();
            String tipPrognoza = (pozitieSpinner == 0) ? "1day" : "5day";

            executor.execute(() -> {
                try {
                    String orasCodat = URLEncoder.encode(oras, "UTF-8");
                    String linkOras = "https://dataservice.accuweather.com/locations/v1/cities/search?q=" + orasCodat;

                    String raspunsOras = citesteDeLaUrl(linkOras);
                    JSONArray vectorOrase = new JSONArray(raspunsOras);
                    JSONObject primulOras = vectorOrase.getJSONObject(0);
                    String cheieOras = primulOras.getString("Key");

                    String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/" + tipPrognoza + "/" + cheieOras + "?metric=true";
                    String raspunsVreme = citesteDeLaUrl(linkVreme);

                    JSONObject obiectVreme = new JSONObject(raspunsVreme);
                    JSONArray dailyForecasts = obiectVreme.getJSONArray("DailyForecasts");

                    StringBuilder textFinal = new StringBuilder();
                    textFinal.append("Cod oras (Key): ").append(cheieOras).append("\n\n");

                    for (int i = 0; i < dailyForecasts.length(); i++) {
                        JSONObject zi = dailyForecasts.getJSONObject(i);
                        String data = zi.getString("Date").substring(0, 10);
                        JSONObject temp = zi.getJSONObject("Temperature");
                        double min = temp.getJSONObject("Minimum").getDouble("Value");
                        double max = temp.getJSONObject("Maximum").getDouble("Value");

                        textFinal.append("Data: ").append(data)
                                .append("\nMin: ").append(min).append(" C | Max: ").append(max).append(" C\n\n");
                    }

                    handler.post(() -> tvRezultat.setText(textFinal.toString()));

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(() -> tvRezultat.setText("Eroare la preluarea datelor: " + e.getMessage()));
                }
            });
        });
    }

    private String citesteDeLaUrl(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int status = conn.getResponseCode();
        BufferedReader reader;
        if (status >= 200 && status < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder builder = new StringBuilder();
        String linie;
        while ((linie = reader.readLine()) != null) {
            builder.append(linie);
        }
        reader.close();
        conn.disconnect();

        if (status < 200 || status >= 300) {
            throw new Exception("HTTP " + status + ": " + builder.toString());
        }
        return builder.toString();
    }
}