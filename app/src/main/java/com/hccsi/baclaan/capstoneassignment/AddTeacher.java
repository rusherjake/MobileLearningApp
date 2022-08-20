package com.hccsi.baclaan.capstoneassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import com.google.firebase.database.*;
import android.content.*;
import java.util.*;

public class AddTeacher extends AppCompatActivity {

    EditText firstname, lastname, idnumber, pass;
    Button done, adminback;
    DatabaseReference ref;
    TeacherMember teacherMember;
    String status = "teacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        idnumber = findViewById(R.id.id_number_input1);
        firstname = findViewById(R.id.first_name_input1);
        lastname = findViewById(R.id.last_name_input1);
        done = findViewById(R.id.done_button);
        pass = findViewById(R.id.password_txt);

        teacherMember = new TeacherMember();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        done.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstCheck = idnumber.getText().toString();
                String secondCheck = firstname.getText().toString();
                String thirdCheck = lastname.getText().toString();
                String fourthCheck = pass.getText().toString();

                if (firstCheck.isEmpty()) {
                    idnumber.setError("Please input value!");
                    return;
                } else {
                    if (secondCheck.isEmpty()) {
                        firstname.setError("Please input value!");
                        return;
                    } else {
                        if (thirdCheck.isEmpty()) {
                            lastname.setError("Please input value!");
                            return;
                        } else {
                            if (fourthCheck.isEmpty()) {
                                pass.setError("Please input value!");
                                return;
                            } else {
                                long id = Long.parseLong(idnumber.getText().toString());
                                String name = firstname.getText().toString() + " " + lastname.getText().toString();

                                teacherMember.setIdNumber(id);
                                teacherMember.setPassword(pass.getText().toString());
                                teacherMember.setName(name);
                                teacherMember.setStatus(status);

                                ref.child(String.valueOf(id)).setValue(teacherMember);
                                Toast.makeText(v.getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                                finish();

//                                Intent intent1 = new Intent(v.getContext(), AdminTeach.class);
//                                startActivity(intent1);

                            }
                        }
                    }

                }

            }
        });
    }


}
