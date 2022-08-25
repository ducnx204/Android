package com.sinhvien.orderdrinkapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.ThanhToanDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanDAO {

    SQLiteDatabase database;
    public ThanhToanDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public List<ThanhToanDTO> LayDSMonTheoMaDon(int madondat){
        List<ThanhToanDTO> thanhToanDTOS = new ArrayList<ThanhToanDTO>();
        String query = "SELECT * FROM "+ SQLite.TBL_CHITIETDONDAT+" ctdd,"+ SQLite.TBL_MON+" mon WHERE "
                +"ctdd."+ SQLite.TBL_CHITIETDONDAT_MAMON+" = mon."+ SQLite.TBL_MON_MAMON+" AND "
                + SQLite.TBL_CHITIETDONDAT_MADONDAT+" = '"+madondat+"'";

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.setSoLuong(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_CHITIETDONDAT_SOLUONG)));
            thanhToanDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_MON_GIATIEN)));
            thanhToanDTO.setTenMon(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_TENMON)));
            thanhToanDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(SQLite.TBL_MON_HINHANH)));
            thanhToanDTOS.add(thanhToanDTO);

            cursor.moveToNext();
        }

        return thanhToanDTOS;
    }
}
