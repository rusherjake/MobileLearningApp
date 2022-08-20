package com.hccsi.baclaan.capstoneassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DeleteTeacher extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listshow;
    Button btnUpdate,btnDelete;
    ArrayList <String> arrList= new ArrayList<>();
    ArrayAdapter <String> arrAdp;
    TeacherMember teacherMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_teacher);



    }
}