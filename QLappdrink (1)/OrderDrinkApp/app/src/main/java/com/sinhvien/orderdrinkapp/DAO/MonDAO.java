package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.MonDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

import java.util.ArrayList;
import java.util.List;

public class MonDAO {
    SQLiteDatabase database;
    public MonDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public boolean ThemMon(MonDTO monDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_MON_MALOAI,monDTO.getMaLoai());
        contentValues.put(SQLite.TBL_MON_TENMON,monDTO.getTenMon());
        contentValues.put(SQLite.TBL_MON_GIATIEN,monDTO.getGiaTien());
        contentValues.put(SQLite.TBL_MON_HINHANH,monDTO.getHinhAnh());
        contentValues.put(SQLite.TBL_MON_TINHTRANG,"true");

        long ktra = database.insert(SQLite.TBL_MON,null,contentValues);

        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaMon(int mamon){
        long ktra = database.delete(SQLite.TBL_MON, SQLite.TBL_MON_MAMON+ " = " +mamon
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean SuaMon(MonDTO monDTO,int mamon){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_MON_MALOAI,monDTO.getMaLoai());
        contentValues.put(SQLite.TBL_MON_TENMON,monDTO.getTenMon());
        contentValues.put(SQLite.TBL_MON_GIATIEN,monDTO.getGiaTien());
        contentValues.put(SQLite.TBL_MON_HINHANH,monDTO.getHinhAnh());
        contentValues.put(SQLite.TBL_MON_TINHTRANG,monDTO.getTinhTrang());

        long ktra = database.update(SQLite.TBL_MON,contentValues,
                SQLite.TBL_MON_MAMON+" = "+mamon,null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

    public List<MonDTO> LayDSMonTheoLoai(int maloai){
        List<MonDTO> monDTOList = new ArrayList<MonDTO>();
        String query = "SELECT * FROM " + SQLite.TBL_MON+ " WHERE " + SQLite.TBL_MON_MALOAI+ " = '" +maloai+ "' ";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonDTO monDTO = new MonDTO();
            monDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(SQLite.TBL_MON_HINHANH)));
            monDTO.setTenMon(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_TENMON)));
            monDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_MON_MALOAI)));
            monDTO.setMaMon(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_MON_MAMON)));
            monDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_GIATIEN)));
            monDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_TINHTRANG)));
            monDTOList.add(monDTO);

            cursor.moveToNext();
        }
        return monDTOList;
    }

    public MonDTO LayMonTheoMa(int mamon){
        MonDTO monDTO = new MonDTO();
        String query = "SELECT * FROM "+ SQLite.TBL_MON+" WHERE "+ SQLite.TBL_MON_MAMON+" = "+mamon;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            monDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(SQLite.TBL_MON_HINHANH)));
            monDTO.setTenMon(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_TENMON)));
            monDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_MON_MALOAI)));
            monDTO.setMaMon(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_MON_MAMON)));
            monDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_GIATIEN)));
            monDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(SQLite.TBL_MON_TINHTRANG)));

            cursor.moveToNext();
        }
        return monDTO;
    }

}
