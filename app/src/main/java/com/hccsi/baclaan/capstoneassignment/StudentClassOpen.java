package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentClassOpen extends AppCompatActivity {

    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_open);


        Intent intent = getIntent();
        String viewclassid = intent.getStringExtra("intentclassid");
        String vclassid = String.valueOf(viewclassid);

        String viewclassname = intent.getStringExtra("intentclassname");
        String vclassname = String.valueOf(viewclassname);

        String classidname = vclassid + " - " + viewclassname;

        //TextView classidtextview = findViewById(R.id.textView11);
//        Toolbar tool = findViewById(R.id.toolbarr);
//        setSupportActionBar(tool);
//        getSupportActionBar().setTitle(classidname);

        BottomNavigationView btmnav = findViewById(R.id.bottom_nav);
        btmnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.student_classwork){
                    TeacherClasswork studentClasswork = new TeacherClasswork();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    studentClasswork.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, studentClasswork);
                    fragmentTransaction.commit();
                }
                if (id == R.id.student_discussion){
                    StudentDiscussion studentDiscussion = new StudentDiscussion();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    studentDiscussion.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, studentDiscussion);
                    fragmentTransaction.commit();
                }
                if (id == R.id.student_view){
                    StudentView studentViewClass = new StudentView();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    studentViewClass.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, studentViewClass);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        btmnav.setSelectedItemId(R.id.student_classwork);
    }
//
//    @Override
//    public void onBackPressed() {
//        if (back_pressed + 1000 > System.currentTimeMillis()){
//            System.exit(0);
//        }
//        else{
//            Toast.makeText(getBaseContext(),"Press once again to exit!", Toast.LENGTH_SHORT).show();
//        }
//        back_pressed = System.currentTimeMillis();
//    }
}