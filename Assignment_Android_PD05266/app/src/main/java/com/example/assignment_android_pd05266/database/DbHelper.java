package com.example.assignment_android_pd05266.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QUAN_LY_THU_VIEN";
    private static final int DB_VERSION = 1;

    static final String CREATE_TABLE_THU_THU =
            "create table ThuThu (maTT TEXT PRIMARY KEY," +
                    "hoTen   TEXT NOT NULL," +
                    "matKhau TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_THANH_VIEN =
            "create table ThanhVien ( " +
                    "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "hoTen   TEXT NOT NULL, " +
                    "namSinh TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_LOAI_SACH =
            "create table LoaiSach (" +
                    "maLoai  INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tenLoai TEXT NOT NULL)";
    //
    static final String CREATE_TABLE_SACH =
            "create table Sach (" +
                    "maSach  INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "tenSach TEXT    NOT NULL,"     +
                    "giaThue INTEGER NOT NULL,"    +
                    "maLoai  INTEGER REFERENCES LoaiSach (maloai)) ";
    //
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon ("  +
                    "maPM   INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "maTT   TEXT    REFERENCES ThuThu (maTT), "+
                    "maTV   TEXT    REFERENCES ThanhVien (matv), "+
                    "masach  INTEGER REFERENCES Sach (masach), "+
                    "tienThue INTEGER NOT NULL,  "+
                    "ngay  DATE NOT NULL,"+
                    "traSach  INTEGER NOT NULL  )";
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //T???o b???ng Th??? th??
        db.execSQL(CREATE_TABLE_THU_THU);
        //T???o b???ng th??nh vi??n
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        //T???o b???ng lo???i s??ch
        db.execSQL(CREATE_TABLE_LOAI_SACH);
        //T???o b???ng s??ch
        db.execSQL(CREATE_TABLE_SACH);
        //T???o b???ng phi???u m?????n
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
//        //Th??m d??? li???u Th??? th??
//      db.execSQL(com.example.assignment_android_pd05266.database.Data_SQLite.INSERT_THU_THU);
//        //Th??m d??? li???u Th??nh vi??n
//        db.execSQL(com.example.pnlib.database.Data_SQLite.INSERT_THANH_VIEN);
//        //Th??m d??? li???u Lo???i s??ch
//        db.execSQL(com.example.pnlib.database.Data_SQLite.INSERT_LOAI_SACH);
//        //Th??m d??? li???u S??ch
//        db.execSQL(com.example.pnlib.database.Data_SQLite.INSERT_SACH);
//        //Th??m d??? li???u Phi???u m?????n
//       db.execSQL(com.example.pnlib.database.Data_SQLite.INSERT_PHIEU_MUON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableLoaiThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableLoaiThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);

    }
}
