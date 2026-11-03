package com.example.seminar04_1075;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class MobilaAdapter extends ArrayAdapter<Mobila> {

    public MobilaAdapter(Context context, List<Mobila> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_mobila, parent, false);
        }

        Mobila mobilaCurenta = getItem(position);

        TextView tvNume = convertView.findViewById(R.id.tvItemNume);
        TextView tvCategorie = convertView.findViewById(R.id.tvItemCategorie);
        TextView tvMaterial = convertView.findViewById(R.id.tvItemMaterial);
        RatingBar rbCalitate = convertView.findViewById(R.id.rbItemCalitate);

        if (mobilaCurenta != null) {

            tvNume.setText(mobilaCurenta.getNume());
            tvCategorie.setText("Categorie: " + mobilaCurenta.getCategorie());
            tvMaterial.setText("Material: " + mobilaCurenta.getMaterial());
            rbCalitate.setRating(mobilaCurenta.getCalitate());
        }

        return convertView;
    }
}