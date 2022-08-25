package com.example.assignment_android_pd05266.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.assignment_android_pd05266.R;

import com.example.assignment_android_pd05266.course.StudentActivity;
import com.example.assignment_android_pd05266.model.Student;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter {
    private StudentActivity context;
    private ArrayList<Student> ArraylistStudent;

    public AdapterStudent(StudentActivity context, ArrayList<Student> arraylistStudent) {
        this.context = context;
        ArraylistStudent = arraylistStudent;
    }

    @Override
    public int getCount() {
        return ArraylistStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return ArraylistStudent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.liststudent,null);

        TextView txtName = view.findViewById(R.id.tvstudentName);
        TextView txtcode = view.findViewById(R.id.tvcode);


        ImageButton imgdele = view.findViewById(R.id.studentdelete);
        ImageButton imginformation = view.findViewById(R.id.studentinformation);
        ImageButton imgupdate = view .findViewById( R.id.studentupdate);

        Student student = ArraylistStudent.get(i);

        txtName.setText(student.getStudent_name());
        txtcode.setText(student.getStudent_code());

        int id = student.getId_student();
        //sự kiện xoá
        imgdele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });
        // sự kiện sửa
        imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });
        //sự kiện show thông tin
        imginformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });
        return view;
    }
}
