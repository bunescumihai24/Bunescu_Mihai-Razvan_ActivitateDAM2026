package com.example.seminar04_1075;

import android.os.Parcel;
import android.os.Parcelable;

public class Mobila implements Parcelable {
    private String nume;
    private boolean esteAsamblata;
    private String material;
    private String categorie;
    private float calitate;
    private boolean inStoc;

    public Mobila(String nume, boolean esteAsamblata, String material, String categorie, float calitate, boolean inStoc) {
        this.nume = nume;
        this.esteAsamblata = esteAsamblata;
        this.material = material;
        this.categorie = categorie;
        this.calitate = calitate;
        this.inStoc = inStoc;
    }

    protected Mobila(Parcel in) {
        nume = in.readString();
        esteAsamblata = in.readByte() != 0;
        material = in.readString();
        categorie = in.readString();
        calitate = in.readFloat();
        inStoc = in.readByte() != 0;
    }

    public static final Creator<Mobila> CREATOR = new Creator<Mobila>() {
        @Override
        public Mobila createFromParcel(Parcel in) {
            return new Mobila(in);
        }

        @Override
        public Mobila[] newArray(int size) {
            return new Mobila[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeByte((byte) (esteAsamblata ? 1 : 0));
        dest.writeString(material);
        dest.writeString(categorie);
        dest.writeFloat(calitate);
        dest.writeByte((byte) (inStoc ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Mobila creată:\nNume: " + nume + "\nCategorie: " + categorie +
                "\nMaterial: " + material + "\nAsamblată: " + (esteAsamblata ? "Da" : "Nu") +
                "\nÎn stoc: " + (inStoc ? "Da" : "Nu") + "\nCalitate: " + calitate + "/5 stele";
    }
}
