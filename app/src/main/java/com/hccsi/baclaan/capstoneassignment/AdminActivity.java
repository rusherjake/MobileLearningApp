package com.hccsi.baclaan.capstoneassignment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AdminActivity extends AppCompatActivity {

    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView btmnav = findViewById(R.id.bottom_nav);
        btmnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.admin_teach){
                    AdminTeach adminTeach = new AdminTeach();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, adminTeach);
                    fragmentTransaction.commit();
                }
                if (id == R.id.admin_student){
                    AdminStudent adminStudent = new AdminStudent();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, adminStudent);
                    fragmentTransaction.commit();
                }
                if (id == R.id.admin_classroom){
                    AdminClassroom adminClassroom = new AdminClassroom();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, adminClassroom);
                    fragmentTransaction.commit();
                }
                if (id == R.id.admin_profile){
                    AdminProfile adminProfile = new AdminProfile();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, adminProfile);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        btmnav.setSelectedItemId(R.id.admin_teach);
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