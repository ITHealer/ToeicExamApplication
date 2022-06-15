package com.example.toeicexamapplication.grammar;

public class Tense {
    String TenThi;

    public Tense() {
    }

    public Tense(String tense_Name) {
        this.TenThi = tense_Name;
    }

    public String getTense_Name() {
        return TenThi;
    }

    public void setTense_Name(String tense_Name) {
        this.TenThi = tense_Name;
    }
}
