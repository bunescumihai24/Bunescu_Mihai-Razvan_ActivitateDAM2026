package com.example.seminar04_1075;

public class MobilaWebItem {
    private String imageUrl;
    private String descriere;
    private String linkWeb;

    public MobilaWebItem(String imageUrl, String descriere, String linkWeb) {
        this.imageUrl = imageUrl;
        this.descriere = descriere;
        this.linkWeb = linkWeb;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getLinkWeb() {
        return linkWeb;
    }
}