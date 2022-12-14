package com.example.assignment_android_pd05266.course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.assignment_android_pd05266.database.database;
import com.example.assignment_android_pd05266.R;
import com.example.assignment_android_pd05266.model.Subject;

import android.widget.EditText;
import android.widget.Toast;

public class UpdateSubjectActivity extends AppCompatActivity {
    EditText edTitle,edCreadit,edTime,edPlace;
    Button btnupdate;
    com.example.assignment_android_pd05266.database.database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edTitle=findViewById(R.id.ed1);
        edCreadit=findViewById(R.id.ed2);
        edTime=findViewById(R.id.ed3);
        edPlace= findViewById(R.id.ed4);

        btnupdate = findViewById(R.id.btupdate);

        //lay du lieuu
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        int credit = intent.getIntExtra("credit",0);
        String time = intent.getStringExtra("time");
        String place = intent.getStringExtra("place");

        edTitle.setText(title);
        edTime.setText(time);
        edPlace.setText(place);
        edCreadit.setText(credit+"");

        database = new database(this);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogupdate(id);
            }
        });
    }

    private void Dialogupdate(int id) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdate);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes= dialog.findViewById(R.id.buttonYesupdate);
        Button btnNo= dialog.findViewById(R.id.buttonNoupdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  subjecttitle = edTitle.getText().toString().trim();
                String credit = edCreadit.getText().toString().trim();
                String time = edTime.getText().toString().trim();
                String place = edPlace.getText().toString().trim();

                if (subjecttitle.equals("")||credit.equals("")||time.equals("")||place.equals("")){
                    Toast.makeText(UpdateSubjectActivity.this,"Nh???p ?????y ????? th??ng tin",Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject,id);

                    Intent intent = new Intent(UpdateSubjectActivity.this, StudentActivity.class);
                    startActivity(intent);

                    Toast.makeText(UpdateSubjectActivity.this,"Sua thanh cong",Toast.LENGTH_SHORT).show();

                }
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


    // luu du lieu edit cap nhat
    private Subject updatesubject(){
        String  subjecttitle = edTitle.getText().toString().trim();
        int credit =Integer.parseInt(edCreadit.getText().toString().trim());
        String time = edTime.getText().toString().trim();
        String place = edPlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}