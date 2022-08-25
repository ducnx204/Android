package fpoly.edu.grocerymanager.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import fpoly.edu.grocerymanager.database.DbHelper;
import fpoly.edu.grocerymanager.model.Hang;

public class HangDAO {
    private SQLiteDatabase db;

    public HangDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void INSERT_SP(String tenHang, Integer gia, Integer maLoai, byte[] hinhAnh){
        String sql = "INSERT INTO Hang VALUES(null, ?, ? , ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tenHang);
        statement.bindDouble(2, gia);
        statement.bindDouble(3, maLoai);
        statement.bindBlob(4, hinhAnh);

        statement.executeInsert();

    }
    public void UPDATE_SP(Integer maHang, String tenHang, Integer gia, Integer maLoai, byte[] hinhAnh){
        String sql = "UPDATE Hang SET tenHang=?, gia=?, maLoai=?, hinhAnh=? WHERE maHang=?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, tenHang);
        statement.bindDouble(2, gia);
        statement.bindDouble(3, maLoai);
        statement.bindBlob(4, hinhAnh);
        statement.bindDouble(5, maHang);

        statement.executeUpdateDelete();

    }

    public int delete(String id){
        return db.delete("Hang","maHang=?", new String[]{id});
    }

    //get data nhiều tham số
    @SuppressLint("Range")
    public List<Hang> getData(String sql, String...selectionArgs){
        List<Hang> list = new ArrayList<Hang>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            list.add(new Hang(Integer.valueOf(c.getInt(0)),
                    c.getString(1),
                    Integer.parseInt(String.valueOf(c.getInt(2))),
                    Integer.valueOf(c.getString(3)),
                    c.getBlob(4)));
        }
        return list;
    }
    // get tất cả data
    public List<Hang> getAll(){
        String sql ="SELECT * FROM Hang";
        return getData(sql);
    }
    //get data theo tên
    public List<Hang> getTimKiem(String name){
        String sql = "SELECT * FROM Hang WHERE tenHang=?";
        List<Hang> list = getData(sql, name);
        return list;
    }
    //get data theo id
    public Hang getID(String id){
        String sql = "SELECT * FROM Hang WHERE maHang=?";
        List<Hang> list = getData(sql,id);
        return list.get(0);
    }
}
