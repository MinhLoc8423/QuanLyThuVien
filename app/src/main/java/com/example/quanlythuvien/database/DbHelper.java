package com.example.quanlythuvien.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "QUANLYTHUVIEN", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text)";
        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text, namsinh text)";
        String dbLoaiSach = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, hoten text)";
        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue integer, maloai integer references LOAISACH(maloai))";
        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, matt text references THUTHU(matt), matv integer references THANHVIEN(matv), masach integer references SACH(masach), ngay text, trasach integer, tienthue integer)";
        db.execSQL(dbThuThu);
        db.execSQL(dbThanhVien);
        db.execSQL(dbLoaiSach);
        db.execSQL(dbSach);
        db.execSQL(dbPhieuMuon);

        db.execSQL("INSERT INTO THUTHU VALUES ('TT01', 'Thu Thu1', 'abc123'), ('TT02', 'Thu Thu2', 'abc123')");
        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu Nhi'),(2, 'Tình Cảm'),(3, 'Giáo Khoa')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Chú Cuội', 2500, 1),(2, 'Tiếng Việt 1', 1000, 3),(3, 'Toán 1', 1000, 3)");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1, 'Vu Minh Loc', '2003'),(2, 'Vu Ngoc Sang', '2013')");
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1, 'TT01', 1, 1, '19/03/2022', '1', 2500), (2, 'TT01', 2, 3, '22/12/2022', '0', 2500)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
