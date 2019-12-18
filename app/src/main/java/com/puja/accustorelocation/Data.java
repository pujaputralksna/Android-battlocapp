package com.puja.accustorelocation;

public class Data {
    private String judul;
    private String isi;
    private String url;
    private String alamat;
    private String telp;
    private String ikon;
    private Double lat;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double distance;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLang() {
        return lang;
    }

    public void setLang(Double lang) {
        this.lang = lang;
    }

    private Double lang;
    public Data() {
    }

    public Data(String judul, String isi, String url,
                String alamat, String telp, String ikon,
                Double lat, Double lang) {
        this.judul = judul;
        this.isi = isi;
        this.url = url;
        this.alamat = alamat;
        this.telp = telp;
        this.lat = lat;
        this.lang = lang;
        this.ikon = ikon;
    }

    public void setJudul(String judul) { this.judul = judul; }
    public String getJudul() { return judul; }

    public void setIsi(String isi) { this.isi = isi; }
    public String getIsi() { return isi; }

    public void setUrl(String url) { this.url = url; }
    public String getUrl() { return url; }

    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getAlamat() { return alamat; }

    public void setTelp(String telp) { this.telp = telp; }
    public String getTelp() { return telp; }

    public void setIkon(String ikon) { this.ikon = ikon; }
    public String getIkon() { return ikon; }
}
