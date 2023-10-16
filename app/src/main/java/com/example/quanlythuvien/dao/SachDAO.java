package com.example.quanlythuvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlythuvien.database.DbHelper;
import com.example.quanlythuvien.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, sc.giathue, sc.maloai, ls.hoten FROM SACH sc, LOAISACH ls WHERE sc.maloai = ls.maloai", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSachMoi(String tenSach, int giaTien, int maLoai){
        SQLiteDatabase  sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", tenSach);
        contentValues.put("giathue", giaTien);
        contentValues.put("maloai", maLoai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1) {
            return false;
        }
        return true;
    }

    public boolean capNhatThongTinSach(int masach, String tenSach, int giaThue, int maLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", tenSach);
        contentValues.put("giathue", giaThue);
        contentValues.put("maloai", maLoai);
        long check = sqLiteDatabase.update("SACH", contentValues, "masach = ?", new String[]{String.valueOf(masach)});
        if (check == -1) {
            return false;
        }
        return true;
    }

    public int xoaSach(int maSach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE masach = ?", new String[]{String.valueOf(maSach)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("SACH", "masach = ?", new String[]{String.valueOf(maSach)});
        if (check == -1) {
            return 0;
        }
        return 1;

    }

}
