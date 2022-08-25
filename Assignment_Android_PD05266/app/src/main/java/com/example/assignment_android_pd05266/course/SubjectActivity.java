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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.assignment_android_pd05266.MainActivity;
import com.example.assignment_android_pd05266.model.Subject;
import com.example.assignment_android_pd05266.database.database;
import com.example.assignment_android_pd05266.adapter.AdapterSubject;
import com.example.assignment_android_pd05266.R;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    database database;
    AdapterSubject adaptersubject;

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject =  findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String title = cursor.getString(1);
            int credit =  cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id,title,credit,time,place));
        }
        adaptersubject = new AdapterSubject(SubjectActivity.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);

        cursor.moveToFirst();
        cursor.close();

        //khi nhấp vào khoảng trống listview
        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //chuyển đến StudentActivity
                Intent intent = new Intent(SubjectActivity.this,StudentActivity.class);
                int id_subject = ArrayListSubject.get(i).getId();
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuadd:
                Intent intent1 = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent1);
                break;
            default:
                Intent intent = new Intent(SubjectActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //thiết lập sự kiện khi nhấn vào nút back
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent = new Intent(SubjectActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //phương thức show thông tin
    public void information(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id==pos){
                Intent intent = new Intent(SubjectActivity.this, SubjectInformationActivity.class);

                intent.putExtra("id",id);
                String title =cursor.getString(1);
                int credit =cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);

                startActivity(intent);
            }
        }
    }

    //Phương thức xoá
    public void delete(final int position){
        Dialog dialog = new Dialog(this);
        //nap layout
        dialog.setContentView(R.layout.dialogdelete);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.StudentYesdele);
        Button btnNo = dialog.findViewById(R.id.StudentNodele);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                database = new database(ActivitySubject.this);

                database.DeleteSubject(position);
                //xoa student
                database.DeleteSubjectStuden(position);
                Intent intent = new Intent(SubjectActivity.this,SubjectActivity.class);
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

    //phương thức sửa
    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == pos){
                Intent intent = new Intent(SubjectActivity.this, UpdateSubjectActivity.class);


                String title=cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                //gui du lieu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);
            }
        }
    }
}