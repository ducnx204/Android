package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.DonDatDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

import java.util.ArrayList;
import java.util.List;

public class DonDatDAO {

    SQLiteDatabase database;
    public DonDatDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public long ThemDonDat(DonDatDTO donDatDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_DONDAT_MABAN,donDatDTO.getMaBan());
        contentValues.put(SQLite.TBL_DONDAT_MANV,donDatDTO.getMaNV());
        contentValues.put(SQLite.TBL_DONDAT_NGAYDAT,donDatDTO.getNgayDat());
        contentValues.put(SQLite.TBL_DONDAT_TINHTRANG,donDatDTO.getTinhTrang());
        contentValues.put(SQLite.TBL_DONDAT_TONGTIEN,donDatDTO.getTongTien());

        long madondat = database.insert(SQLite.TBL_DONDAT,null,contentValues);

        return madondat;
    }

    public List<DonDatDTO> LayDSDonDat(){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM "+ SQLite.TBL_DONDAT;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }

    public List<DonDatDTO> LayDSDonDatNgay(String ngaythang){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM "+ SQLite.TBL_DONDAT+" WHERE "+ SQLite.TBL_DONDAT_NGAYDAT+" like '"+ngaythang+"'";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(SQLite.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }

    public long LayMaDonTheoMaBan(int maban, String tinhtrang){
        String query = "SELECT * FROM " + SQLite.TBL_DONDAT+ " WHERE " + SQLite.TBL_DONDAT_MABAN+ " = '" +maban+ "' AND "
                + SQLite.TBL_DONDAT_TINHTRANG+ " = '" +tinhtrang+ "' ";
        long magoimon = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            magoimon = cursor.getInt(cursor.getColumnIndex(SQLite.TBL_DONDAT_MADONDAT));

            cursor.moveToNext();
        }
        return magoimon;
    }

    public boolean UpdateTongTienDonDat(int madondat,String tongtien){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_DONDAT_TONGTIEN,tongtien);
        long ktra  = database.update(SQLite.TBL_DONDAT,contentValues,
                SQLite.TBL_DONDAT_MADONDAT+" = "+madondat,null);
        if(ktra != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean UpdateTThaiDonTheoMaBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_DONDAT_TINHTRANG,tinhtrang);
        long ktra = database.update(SQLite.TBL_DONDAT,contentValues, SQLite.TBL_DONDAT_MABAN+
                " = '"+maban+"'",null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

}
