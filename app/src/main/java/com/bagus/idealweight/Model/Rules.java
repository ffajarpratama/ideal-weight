package com.bagus.idealweight.Model;

public class Rules {
    String Aktivitas, Gender, atas, bawah, durasiEasy, durasiMedium, durasiHard, Umur;

    public Rules() {
    }

    public Rules(String aktivitas, String gender, String atas, String bawah, String durasiEasy, String durasiMedium, String durasiHard, String umur) {
        Aktivitas = aktivitas;
        Gender = gender;
        this.atas = atas;
        this.bawah = bawah;
        this.durasiEasy = durasiEasy;
        this.durasiMedium = durasiMedium;
        this.durasiHard = durasiHard;
        Umur = umur;
    }

    public String getAktivitas() {
        return Aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        Aktivitas = aktivitas;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAtas() {
        return atas;
    }

    public void setAtas(String atas) {
        this.atas = atas;
    }

    public String getBawah() {
        return bawah;
    }

    public void setBawah(String bawah) {
        this.bawah = bawah;
    }

    public String getDurasiEasy() {
        return durasiEasy;
    }

    public void setDurasiEasy(String durasiEasy) {
        this.durasiEasy = durasiEasy;
    }

    public String getDurasiMedium() {
        return durasiMedium;
    }

    public void setDurasiMedium(String durasiMedium) {
        this.durasiMedium = durasiMedium;
    }

    public String getDurasiHard() {
        return durasiHard;
    }

    public void setDurasiHard(String durasiHard) {
        this.durasiHard = durasiHard;
    }

    public String getUmur() {
        return Umur;
    }

    public void setUmur(String umur) {
        Umur = umur;
    }
}
