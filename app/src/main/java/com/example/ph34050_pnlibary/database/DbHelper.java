package com.example.ph34050_pnlibary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLib";
    private static final int DB_VERSION = 12;

    static final String CREATE_TABLE_THU_THU = "" +
            "CREATE TABLE ThuThu(\n" +
            "maTT text PRIMARY KEY,\n" +
            "hoTen text NOT NULL,\n" +
            "matKhau text NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_THANH_VIEN ="" +
            "CREATE TABLE ThanhVien(\n" +
            "maTV INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "hoTen text NOT NULL,\n" +
            "namSinh text NOT NULL,\n" +
            "cccd text NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_lOAI_SACH ="" +
            "CREATE TABLE LoaiSach(\n" +
            "maLoai Integer PRIMARY KEY AUTOINCREMENT ,\n" +
            "tenLoai text NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_SACH ="" +
            "CREATE TABLE Sach(\n" +
            "maSach Integer PRIMARY KEY AUTOINCREMENT,\n" +
            "tenSach text NOT NULL,\n" +
            "giaThue Integer NOT NULL,\n" +
            "maLoai Integer REFERENCES LoaiSach(maloai)\n" +
            ")";
    static final String CREATE_TABLE_PHIEU_MUON ="" +
            "CREATE TABLE PhieuMuon(\n" +
            "maPM Integer PRIMARY KEY AUTOINCREMENT,\n" +
            "maTT text REFERENCES ThuThu(maTT),\n" +
            "maTV INTEGER REFERENCES ThanhVien(maTV),\n" +
            "maSach Integer REFERENCES Sach(maSach),\n" +
            "tienThue Integer NOT NULL,\n" +
            "ngay date NOT NULL,\n" +
            "traSach Integer NOT NULL)";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_THU_THU);
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        db.execSQL(CREATE_TABLE_lOAI_SACH);
        db.execSQL(CREATE_TABLE_SACH);
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        //insert data
        db.execSQL(Data_SQLite.INSERT_THU_THU);
        db.execSQL(Data_SQLite.INSERT_LOAI_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEU_MUON);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_THANH_VIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropThuThu = "DROP TABLE IF EXISTS ThuThu";
        db.execSQL(dropThuThu);
        String dropThanhVien = "DROP TABLE IF EXISTS ThanhVien";
        db.execSQL(dropThanhVien);
        String dropLoaiSach = "DROP TABLE IF EXISTS LoaiSach";
        db.execSQL(dropLoaiSach);
        String dropSach = "DROP TABLE IF EXISTS Sach";
        db.execSQL(dropSach);
        String dropPhieuMuon = "DROP TABLE IF EXISTS PhieuMuon";
        db.execSQL(dropPhieuMuon);
        onCreate(db);
    }
}
