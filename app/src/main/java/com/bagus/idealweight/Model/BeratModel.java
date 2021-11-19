package com.bagus.idealweight.Model;

import java.util.ArrayList;

public class BeratModel {
    String berat;
    String tinggi;
    String beratIdeal;
    double selisihBerat;
    double selisihKalori;
    String gender;
    String usia;
    String aktivitas;
    String email;
    String namaUser;
    String telp;
    ArrayList<String> exercise;
    String key;

    public BeratModel() {

    }

    public BeratModel(String berat, String tinggi, String beratIdeal, double selisihBerat, double selisihKalori, String gender, String usia, String aktivitas, String email, String namaUser, String telp, ArrayList<String> exercise) {
        this.berat = berat;
        this.tinggi = tinggi;
        this.beratIdeal = beratIdeal;
        this.selisihBerat = selisihBerat;
        this.selisihKalori = selisihKalori;
        this.gender = gender;
        this.usia = usia;
        this.aktivitas = aktivitas;
        this.email = email;
        this.namaUser = namaUser;
        this.telp = telp;
        this.exercise = exercise;
    }

    public ArrayList<String> getExercise() {
        return exercise;
    }

    public void setExercise(ArrayList<String> exercise) {
        this.exercise = exercise;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getBeratIdeal() {
        return beratIdeal;
    }

    public void setBeratIdeal(String beratIdeal) {
        this.beratIdeal = beratIdeal;
    }

    public double getSelisihBerat() {
        return selisihBerat;
    }

    public void setSelisihBerat(double selisihBerat) {
        this.selisihBerat = selisihBerat;
    }

    public double getSelisihKalori() {
        return selisihKalori;
    }

    public void setSelisihKalori(double selisihKalori) {
        this.selisihKalori = selisihKalori;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
