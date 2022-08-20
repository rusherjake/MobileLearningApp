package com.hccsi.baclaan.capstoneassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassroom extends AppCompatActivity {

    EditText classid, classname, teacherid;
    String type = "classroom";
    Button done;
    DatabaseReference ref;
    ClassroomModel classroomModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);

        classid = findViewById(R.id.classidnum);
        classname = findViewById(R.id.classnameadd);
        teacherid = findViewById(R.id.teacheridnum);
        done = findViewById(R.id.done_button);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = FirebaseDatabase.getInstance().getReference("Users").child(teacherid.getText().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String name = dataSnapshot.child("name").getValue().toString();
                            classroomModel.setcteacherid(teacherid.getText().toString());
                            classroomModel.setcclassid(classid.getText().toString());
                            classroomModel.setcteachername(name);
                            classroomModel.setclassname(classname.getText().toString());


                            ref.child(String.valueOf(classid)).setValue(classroomModel);
                            Toast.makeText(v.getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(v.getContext(), "Teacher ID does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(v.getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }
}