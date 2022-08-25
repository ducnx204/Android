package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.Database.SQLite;

public class QuyenDAO {

    SQLiteDatabase database;
    public QuyenDAO(Context context){
        SQLite createDatabase = new SQLite(context);
        database = createDatabase.open();
    }

    public void ThemQuyen(String tenquyen){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLite.TBL_QUYEN_TENQUYEN,tenquyen);
        database.insert(SQLite.TBL_QUYEN,null,contentValues);
    }
    

    public String LayTenQuyenTheoMa(int maquyen){
        String tenquyen ="";
        String query = "SELECT * FROM "+ SQLite.TBL_QUYEN+" WHERE "+ SQLite.TBL_QUYEN_MAQUYEN+" = "+maquyen;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tenquyen = cursor.getString(cursor.getColumnIndex(SQLite.TBL_QUYEN_TENQUYEN));
            cursor.moveToNext();
        }
        return tenquyen;
    }
}
