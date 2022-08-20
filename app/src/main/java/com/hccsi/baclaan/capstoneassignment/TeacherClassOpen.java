package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherClassOpen extends AppCompatActivity {

    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_open);


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
                if (id == R.id.teacher_classwork){
                    TeacherClasswork teacherClasswork = new TeacherClasswork();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    teacherClasswork.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, teacherClasswork);
                    fragmentTransaction.commit();
                }
                if (id == R.id.teacher_discussion){
                    TeacherDiscussion teacherDiscussion = new TeacherDiscussion();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    teacherDiscussion.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, teacherDiscussion);
                    fragmentTransaction.commit();
                }
                if (id == R.id.teacher_view){
                    TeacherViewClass teacherViewClass = new TeacherViewClass();

                    Bundle bundle = new Bundle();
                    bundle.putString("vclassid", vclassid);
                    teacherViewClass.setArguments(bundle);//Here pass your data

                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.framelay, teacherViewClass);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        btmnav.setSelectedItemId(R.id.teacher_classwork);
    }

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