package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.ChiTietDonDatDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

public class ChiTietDonDatDAO {

    SQLiteDatabase database;
    public ChiTietDonDatDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public boolean KiemTraMonTonTai(int madondat, int mamon){
        String query = "SELECT * FROM " + SQLite.TBL_CHITIETDONDAT+ " WHERE " + SQLite.TBL_CHITIETDONDAT_MAMON+
                " = " +mamon+ " AND " + SQLite.TBL_CHITIETDONDAT_MADONDAT+ " = "+madondat;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    public int LaySLMonTheoMaDon(int madondat, int mamon){
        int soluong = 0;
        String query = "SELECT * FROM " + SQLite.TBL_CHITIETDONDAT+ " WHERE " + SQLite.TBL_CHITIETDONDAT_MAMON+
                " = " +mamon+ " AND " + SQLite.TBL_CHITIETDONDAT_MADONDAT+ " = "+madondat;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            soluong = cursor.getInt(cursor.getColumnIndex(SQLite.TBL_CHITIETDONDAT_SOLUONG));
            cursor.moveToNext();
        }
        return soluong;
    }

    public boolean CapNhatSL(ChiTietDonDatDTO chiTietDonDatDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_CHITIETDONDAT_SOLUONG, chiTietDonDatDTO.getSoLuong());

        long ktra = database.update(SQLite.TBL_CHITIETDONDAT,contentValues, SQLite.TBL_CHITIETDONDAT_MADONDAT+ " = "
                +chiTietDonDatDTO.getMaDonDat()+ " AND " + SQLite.TBL_CHITIETDONDAT_MAMON+ " = "
                +chiTietDonDatDTO.getMaMon(),null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

    public boolean ThemChiTietDonDat(ChiTietDonDatDTO chiTietDonDatDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_CHITIETDONDAT_SOLUONG,chiTietDonDatDTO.getSoLuong());
        contentValues.put(SQLite.TBL_CHITIETDONDAT_MADONDAT,chiTietDonDatDTO.getMaDonDat());
        contentValues.put(SQLite.TBL_CHITIETDONDAT_MAMON,chiTietDonDatDTO.getMaMon());

        long ktra = database.insert(SQLite.TBL_CHITIETDONDAT,null,contentValues);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

}
