package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.LoaiMonDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonDAO {

    SQLiteDatabase database;
    public LoaiMonDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public boolean ThemLoaiMon(LoaiMonDTO loaiMonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_LOAIMON_TENLOAI,loaiMonDTO.getTenLoai());
        contentValues.put(SQLite.TBL_LOAIMON_HINHANH,loaiMonDTO.getHinhAnh());
        long ktra = database.insert(SQLite.TBL_LOAIMON,null,contentValues);

        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean XoaLoaiMon(int maloai){
        long ktra = database.delete(SQLite.TBL_LOAIMON, SQLite.TBL_LOAIMON_MALOAI+ " = " +maloai
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    public boolean SuaLoaiMon(LoaiMonDTO loaiMonDTO,int maloai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_LOAIMON_TENLOAI,loaiMonDTO.getTenLoai());
        contentValues.put(SQLite.TBL_LOAIMON_HINHANH,loaiMonDTO.getHinhAnh());
        long ktra = database.update(SQLite.TBL_LOAIMON,contentValues
                , SQLite.TBL_LOAIMON_MALOAI+" = "+maloai,null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<LoaiMonDTO> LayDSLoaiMon(){
        List<LoaiMonDTO> loaiMonDTOList = new ArrayList<LoaiMonDTO>();
        String query = "SELECT * FROM " + SQLite.TBL_LOAIMON;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            LoaiMonDTO loaiMonDTO = new LoaiMonDTO();
            loaiMonDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_LOAIMON_MALOAI)));
            loaiMonDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(SQLite.TBL_LOAIMON_TENLOAI)));
            loaiMonDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(SQLite.TBL_LOAIMON_HINHANH)));
            loaiMonDTOList.add(loaiMonDTO);

            cursor.moveToNext();
        }
        return loaiMonDTOList;
    }

    public LoaiMonDTO LayLoaiMonTheoMa(int maloai){
        LoaiMonDTO loaiMonDTO = new LoaiMonDTO();
        String query = "SELECT * FROM " + SQLite.TBL_LOAIMON+" WHERE "+ SQLite.TBL_LOAIMON_MALOAI+" = "+maloai;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            loaiMonDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_LOAIMON_MALOAI)));
            loaiMonDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(SQLite.TBL_LOAIMON_TENLOAI)));
            loaiMonDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(SQLite.TBL_LOAIMON_HINHANH)));

            cursor.moveToNext();
        }
        return loaiMonDTO;
    }

}
