package com.hccsi.baclaan.capstoneassignment;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TeacherActivity extends AppCompatActivity {

    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        BottomNavigationView btmnav = findViewById(R.id.bottom_nav);
        btmnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.teacher_classroom){
                    TeacherClassroom teacherClassroom = new TeacherClassroom();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, teacherClassroom);
                    fragmentTransaction.commit();
                }
                if (id == R.id.teacher_profile){
                    TeacherProfile teacherProfile = new TeacherProfile();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, teacherProfile);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        btmnav.setSelectedItemId(R.id.teacher_classroom);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            System.exit(0);
        }
        else{
            Toast.makeText(getBaseContext(),"Press once again to exit!", Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}