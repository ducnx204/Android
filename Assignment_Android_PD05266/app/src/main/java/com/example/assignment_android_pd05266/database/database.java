package com.example.assignment_android_pd05266.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.assignment_android_pd05266.model.Student;
import com.example.assignment_android_pd05266.model.Subject;
import com.example.assignment_android_pd05266.model.Subject;

public class database extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studentmanagement";
    //Bảng môn học
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String SEX = "sex";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";


    //Tạo bảng môn học
    private String SQLQuery = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT) ";

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +SEX+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";
    public database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    //tạo database cho 2 bảng
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddSubject (Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //thêm dữ liệu nhập vào vào database
        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREDITS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());
        //thêm dữ liệu vào bảng
        db.insert(TABLE_SUBJECTS,null,values);
        //đóng
        db.close();
    }

    public boolean UpdateSubject(Subject subject,int id){
        //gọi hàm đễ cập nhật dữ liệu
        SQLiteDatabase db = this.getWritableDatabase();
        //Khởi tạo dữ liệu mời và chèn vào
        ContentValues values = new ContentValues();
        //lấy dữ liệu khi người dùng nhập thêm vào database
        values.put(SUBJECT_TITLE,subject.getSubject_title());
        values.put(CREDITS,subject.getNumber_of_credit());
        values.put(TIME,subject.getTime());
        values.put(PLACE,subject.getPlace());
        db.update(TABLE_SUBJECTS,values,ID_SUBJECTS+" = "+id,null);
        return true;
    }
//lay du lieu lop hoc
    public Cursor getDataSubject(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_SUBJECTS,null);
        return cursor;
    }
//xoá khoá học
    public int DeleteSubject(int  i){
        SQLiteDatabase db =this.getWritableDatabase();

        int res = db.delete(TABLE_SUBJECTS,ID_SUBJECTS+" = "+i,null);
        return res;
    }
//xoá lớp học
    public int DeleteSubjectStuden(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+" = "+i,null);
        return res;
    }
    //Thêm sinh viên
    public void Addstudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(STUDENT_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());
        values.put(ID_SUBJECTS,student.getId_subject());

        db.insert(TABLE_STUDENT,null,values);
        db.close();
    }
    //lấy dữ liệu sinh viên
    public Cursor getDataStudent(int id_subject){
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_STUDENT+" WHERE "+ID_SUBJECTS+" = "+id_subject,null);
        return res;
    }
    //xoá sinh viên
    public int DeleteStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_STUDENT+" = "+i,null);
        return res;
    }
    //cap nhat sv

    public boolean updateStudent(Student student,int id){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(SEX,student.getSex());
        values.put(STUDENT_CODE,student.getStudent_code());
        values.put(DATE_OF_BIRTH,student.getDate_of_birth());

        db.update(TABLE_STUDENT,values,ID_STUDENT+" = "+id,null );
        return true;
    }
}
