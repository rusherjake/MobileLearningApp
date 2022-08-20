package com.hccsi.baclaan.capstoneassignment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentActivity extends AppCompatActivity {

    long back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        BottomNavigationView btmnav = findViewById(R.id.bottom_nav);
        btmnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.student_classroom){
                    StudentClassroom studentClassroom = new StudentClassroom();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, studentClassroom);
                    fragmentTransaction.commit();
                }
                if (id == R.id.student_profile){
                    StudentProfile studentProfile = new StudentProfile();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, studentProfile);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        btmnav.setSelectedItemId(R.id.student_classroom);
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