package com.example.assignment_android_pd05266.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.assignment_android_pd05266.R;
import com.example.assignment_android_pd05266.adapter.AdapterStudent;
import com.example.assignment_android_pd05266.database.database;
import com.example.assignment_android_pd05266.model.Student;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    com.example.assignment_android_pd05266.database.database database;
    com.example.assignment_android_pd05266.adapter.AdapterStudent adapterstudent;

    int id_subject = 0;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarstudent);
        listViewstudent = findViewById(R.id.listviewstudent);

        Intent intent = getIntent();

        id_subject = intent.getIntExtra("id_subject",0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();


        //
        Cursor cursor = database.getDataStudent(id_subject);
        //đọc dữ liệu tiếp theo
        while (cursor.moveToNext()){
            //đọc dữ liệu theo cột
            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            String code = cursor.getString(3);
            String birthday  =cursor.getColumnName(4);
            //thêm dữ liệu vào Arraylist
            ArrayListStudent.add(new Student(id,name,sex,code,birthday,id_sub));

        }
        adapterstudent = new AdapterStudent(StudentActivity.this,ArrayListStudent);
        //Hiển thị thông tin đã thêm vào lên listview
        listViewstudent.setAdapter(adapterstudent);
        cursor.moveToFirst();
        cursor.close();

    }
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuaddStudent:
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
                break;

            default:
                Intent intent1 = new Intent(StudentActivity.this,SubjectActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        counter++;
        if (counter >=1 ){
            Intent intent = new Intent(this,SubjectActivity.class);
            startActivity(intent);
            finish();
        }
    }
    // hiển thi thông tin
    public void information(final int pos){
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                Intent intent = new Intent(StudentActivity.this, StudentInformationActivity.class);
                intent.putExtra("id",pos);

                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birtday",birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    ///phương thức update student

    public void update(final int id_student){
        Cursor cursor =database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            //con trỏ đến vị trí cột thứ 0
            int id = cursor.getInt(0);
            //nếu id = với id_student
            if (id == id_student){
                Intent intent = new Intent(StudentActivity.this, UpdateStudentActivity.class);
                intent.putExtra("id",id_student);

                //lấy dữ liệu
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int id_subject = cursor.getInt(5);

                //sửa dữ liệu
                intent.putExtra("name",name);
                intent.putExtra("sex",sex);
                intent.putExtra("code",code);
                intent.putExtra("birtday",birthday);
                intent.putExtra("id_subject",id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }
    public void delete(final int id_student){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletestudent);

        Button btnYes =dialog. findViewById(R.id.StudentYesdele);
        Button btnNo = dialog.findViewById(R.id.StudentNodele);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa khoi database
                database.DeleteStudent(id_student);
                //xoa va mo lai
                Intent intent = new Intent(StudentActivity.this,StudentActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}