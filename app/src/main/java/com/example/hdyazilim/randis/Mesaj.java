package com.example.hdyazilim.randis;

public class Mesaj {

    String mesajText;
    String gönderici;
    String zaman;

    public Mesaj() {
    }

    public Mesaj(String mesajText, String gönderici, String zaman) {
        this.mesajText = mesajText;
        this.gönderici = gönderici;
        this.zaman = zaman;
    }

    public String getMesajText() {
        return mesajText;
    }

    public void setMesajText(String mesajText) {
        this.mesajText = mesajText;
    }

    public String getGönderici() {
        return gönderici;
    }

    public void setGönderici(String gönderici) {
        this.gönderici = gönderici;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }
}
