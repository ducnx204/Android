package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.NhanVienDTO;
import com.sinhvien.orderdrinkapp.Database.SQLite;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    SQLiteDatabase database;
    public NhanVienDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_NHANVIEN_HOTENNV,nhanVienDTO.getHOTENNV());
        contentValues.put(SQLite.TBL_NHANVIEN_TENDN,nhanVienDTO.getTENDN());
        contentValues.put(SQLite.TBL_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(SQLite.TBL_NHANVIEN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(SQLite.TBL_NHANVIEN_SDT,nhanVienDTO.getSDT());
        contentValues.put(SQLite.TBL_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(SQLite.TBL_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        contentValues.put(SQLite.TBL_NHANVIEN_MAQUYEN,nhanVienDTO.getMAQUYEN());

        long ktra = database.insert(SQLite.TBL_NHANVIEN,null,contentValues);
        return ktra;
    }

    public long SuaNhanVien(NhanVienDTO nhanVienDTO,int manv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_NHANVIEN_HOTENNV,nhanVienDTO.getHOTENNV());
        contentValues.put(SQLite.TBL_NHANVIEN_TENDN,nhanVienDTO.getTENDN());
        contentValues.put(SQLite.TBL_NHANVIEN_MATKHAU,nhanVienDTO.getMATKHAU());
        contentValues.put(SQLite.TBL_NHANVIEN_EMAIL,nhanVienDTO.getEMAIL());
        contentValues.put(SQLite.TBL_NHANVIEN_SDT,nhanVienDTO.getSDT());
        contentValues.put(SQLite.TBL_NHANVIEN_GIOITINH,nhanVienDTO.getGIOITINH());
        contentValues.put(SQLite.TBL_NHANVIEN_NGAYSINH,nhanVienDTO.getNGAYSINH());
        contentValues.put(SQLite.TBL_NHANVIEN_MAQUYEN,nhanVienDTO.getMAQUYEN());

        long ktra = database.update(SQLite.TBL_NHANVIEN,contentValues,
                SQLite.TBL_NHANVIEN_MANV+" = "+manv,null);
        return ktra;
    }

    public int KiemTraDN(String tenDN, String matKhau){
        String query = "SELECT * FROM " + SQLite.TBL_NHANVIEN+ " WHERE "
                + SQLite.TBL_NHANVIEN_TENDN +" = '"+ tenDN+"' AND "+ SQLite.TBL_NHANVIEN_MATKHAU +" = '" +matKhau +"'";
        int manv = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            manv = cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MANV)) ;
            cursor.moveToNext();
        }
        return manv;
    }

    public boolean KtraTonTaiNV(){
        String query = "SELECT * FROM "+ SQLite.TBL_NHANVIEN;
        Cursor cursor =database.rawQuery(query,null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }

    public List<NhanVienDTO> LayDSNV(){
        List<NhanVienDTO> nhanVienDTOS = new ArrayList<NhanVienDTO>();
        String query = "SELECT * FROM "+ SQLite.TBL_NHANVIEN;

        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setHOTENNV(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_HOTENNV)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_EMAIL)));
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_NGAYSINH)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_SDT)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_TENDN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MATKHAU)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MANV)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MAQUYEN)));

            nhanVienDTOS.add(nhanVienDTO);
            cursor.moveToNext();
        }
        return nhanVienDTOS;
    }

    public boolean XoaNV(int manv){
        long ktra = database.delete(SQLite.TBL_NHANVIEN, SQLite.TBL_NHANVIEN_MANV+ " = " +manv
                ,null);
        if(ktra !=0 ){
            return true;
        }else {
            return false;
        }
    }

    public NhanVienDTO LayNVTheoMa(int manv){
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        String query = "SELECT * FROM "+ SQLite.TBL_NHANVIEN+" WHERE "+ SQLite.TBL_NHANVIEN_MANV+" = "+manv;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            nhanVienDTO.setHOTENNV(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_HOTENNV)));
            nhanVienDTO.setEMAIL(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_EMAIL)));
            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_GIOITINH)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_NGAYSINH)));
            nhanVienDTO.setSDT(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_SDT)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_TENDN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MATKHAU)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MANV)));
            nhanVienDTO.setMAQUYEN(cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MAQUYEN)));

            cursor.moveToNext();
        }
        return nhanVienDTO;
    }

    public int LayQuyenNV(int manv){
        int maquyen = 0;
        String query = "SELECT * FROM "+ SQLite.TBL_NHANVIEN+" WHERE "+ SQLite.TBL_NHANVIEN_MANV+" = "+manv;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maquyen = cursor.getInt(cursor.getColumnIndex(SQLite.TBL_NHANVIEN_MAQUYEN));

            cursor.moveToNext();
        }
        return maquyen;
    }


}
