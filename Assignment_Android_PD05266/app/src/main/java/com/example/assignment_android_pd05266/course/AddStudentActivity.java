package com.example.assignment_android_pd05266.course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.assignment_android_pd05266.R;
import com.example.assignment_android_pd05266.database.database;
import com.example.assignment_android_pd05266.model.Student;

import android.widget.RadioButton;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    Button buttonAddstudent;
    EditText editextStudentName,editextstudentcode,edittextdateofbirth;
    RadioButton radioButtonMale,RadioButtonFeMale;
    database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddstudent = findViewById(R.id.buttonAđdStudent);
        edittextdateofbirth = findViewById(R.id.EditTextStudentBirthday);
        editextstudentcode = findViewById(R.id.EditTextStudentCode);
        editextStudentName = findViewById(R.id.EditTextStudentName);

        radioButtonMale = findViewById(R.id.radiobuttonMale);
        RadioButtonFeMale=findViewById(R.id.radiobuttonFeMale);

        Intent intent = getIntent();
        int id_subject  = intent.getIntExtra("id_subject",0);

        database = new database(this);
        buttonAddstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_subject);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        //khởi tạo hộp thoại
        Dialog dialog = new Dialog(this);
        //hiển thị thông tin layout lên màn hình
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        //truy vấn đến buton yes no
        Button btnyes=dialog.findViewById(R.id.buttonYesstudent);
        Button btnno = dialog.findViewById(R.id.buttonNostudent);

        //khi click button yes
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu người dùng nhập vào
                String name = editextStudentName.getText().toString().trim();
                String code = editextstudentcode.getText().toString().trim();
                String birtday = edittextdateofbirth.getText().toString().trim();
                String sex = "";
                if (radioButtonMale.isChecked()){
                    sex="Nam";
                }else if (RadioButtonFeMale.isChecked()){
                    sex="Nữ";
                }
                //không được để trống dữ liệu
                if (name.equals("")|| code.equals("")||birtday.equals("")||sex.equals("")){
                    Toast.makeText(AddStudentActivity.this,"Bạn phải nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else {
                    //thêm thông tin sinh viên vào database
                    Student student = Createstudent(id_subject);
                    database.Addstudent(student);

                    Intent intent= new Intent(AddStudentActivity.this,StudentActivity.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(AddStudentActivity.this,"Lưu thành công",Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //hiển thị dialog
        dialog.show();
    }
    //lấy dữ liệu nhập vào
    private Student Createstudent(int id_subject){
        String name = editextStudentName.getText().toString().trim();
        String code = editextstudentcode.getText().toString().trim();
        String birtday = edittextdateofbirth.getText().toString().trim();
        String sex = "";
        if (radioButtonMale.isChecked()){
            sex="Nam";
        }else if (RadioButtonFeMale.isChecked()){
            sex="Nữ";
        }
        Student student = new Student(name,sex,code,birtday,id_subject);
        return student;
    }

}