package com.example.assignment_android_pd05266.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignment_android_pd05266.R;

public class StudentInformationActivity extends AppCompatActivity {
    TextView txtName,txtSex,txtCode,txtBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        txtBirthday = findViewById(R.id.txtStudentdateofbirht);
        txtCode = findViewById(R.id.txtStudentCode);
        txtSex = findViewById(R.id.txtStudentSex);
        txtName = findViewById(R.id.txtStudentName);
        Intent intent  = getIntent();

        String name =intent.getStringExtra("name");
        String sex =intent.getStringExtra("sex");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birtday");

        txtName.setText(name);
        txtSex.setText(sex);
        txtCode.setText(code);
        txtBirthday.setText(birthday);
    }

}