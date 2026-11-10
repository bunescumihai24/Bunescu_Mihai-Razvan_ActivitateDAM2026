package com.example.seminar04_1075;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MobilaWebAdapter extends ArrayAdapter<MobilaWebItem> {

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public MobilaWebAdapter(Context context, List<MobilaWebItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_galerie, parent, false);
        }

        MobilaWebItem item = getItem(position);

        ImageView ivMobila = convertView.findViewById(R.id.ivMobila);
        TextView tvDescriere = convertView.findViewById(R.id.tvDescriere);

        if (item != null) {
            tvDescriere.setText(item.getDescriere());
            ivMobila.setImageResource(android.R.drawable.ic_menu_gallery); // Imagine temporară de încărcare

            // Descărcăm imaginea în fundal prin URL
            executorService.execute(() -> {
                Bitmap bitmap = incarcaImagineURL(item.getImageUrl());
                if (bitmap != null) {
                    mainHandler.post(() -> ivMobila.setImageBitmap(bitmap));
                }
            });
        }

        return convertView;
    }

    private Bitmap incarcaImagineURL(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}