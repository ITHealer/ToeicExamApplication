package com.example.toeicexamapplication.grammar;

public class Grammar{
    private String KhangDinh;
    private String PhuDinh;
    private String NghiVan;
    private String ChuThich;
    private String DauHieu;
    private String Dinhnghia;
    private String TenThi;
    private String ViDu;

    public Grammar() {
    }

    public Grammar(String khangDinh, String phuDinh, String nghiVan, String chuThich, String dauHieu, String dinhnghia, String tenThi, String viDu) {
        KhangDinh = khangDinh;
        PhuDinh = phuDinh;
        NghiVan = nghiVan;
        ChuThich = chuThich;
        DauHieu = dauHieu;
        Dinhnghia = dinhnghia;
        TenThi = tenThi;
        ViDu = viDu;
    }

    public String getKhangDinh() {
        return KhangDinh;
    }

    public void setKhangDinh(String khangDinh) {
        KhangDinh = khangDinh;
    }

    public String getPhuDinh() {
        return PhuDinh;
    }

    public void setPhuDinh(String phuDinh) {
        PhuDinh = phuDinh;
    }

    public String getNghiVan() {
        return NghiVan;
    }

    public void setNghiVan(String nghiVan) {
        NghiVan = nghiVan;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String chuThich) {
        ChuThich = chuThich;
    }

    public String getDauHieu() {
        return DauHieu;
    }

    public void setDauHieu(String dauHieu) {
        DauHieu = dauHieu;
    }

    public String getDinhnghia() {
        return Dinhnghia;
    }

    public void setDinhnghia(String dinhnghia) {
        Dinhnghia = dinhnghia;
    }

    public String getTenThi() {
        return TenThi;
    }

    public void setTenThi(String tenThi) {
        TenThi = tenThi;
    }

    public String getViDu() {
        return ViDu;
    }

    public void setViDu(String viDu) {
        ViDu = viDu;
    }
}
