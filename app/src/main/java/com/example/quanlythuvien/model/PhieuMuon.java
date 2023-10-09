package com.example.quanlythuvien.model;

public class PhieuMuon {
    private int mapp;
    private String matt;
    private int matv;
    private int masach;
    private String ngay;
    private int trasach;
    private int tienThue;
    private String tentv;
    private String tentt;
    private String tensach;

    public PhieuMuon(int mapp, String matt, int matv, int masach, String ngay, int trasach, int tienThue, String tentv, String tentt, String tensach) {
        this.mapp = mapp;
        this.matt = matt;
        this.matv = matv;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienThue = tienThue;
        this.tentv = tentv;
        this.tentt = tentt;
        this.tensach = tensach;
    }

    public PhieuMuon( String matt, int matv, int masach, String ngay, int trasach, int tienThue) {
        this.matt = matt;
        this.matv = matv;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienThue = tienThue;
    }

    public int getMapp() {
        return mapp;
    }

    public void setMapp(int mapp) {
        this.mapp = mapp;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTentt() {
        return tentt;
    }

    public void setTentt(String tentt) {
        this.tentt = tentt;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }
}
