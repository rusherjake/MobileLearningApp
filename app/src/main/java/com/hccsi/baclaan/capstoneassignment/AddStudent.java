package com.hccsi.baclaan.capstoneassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {

    EditText firstname, lastname, idnumber, pass, course, year;
    Button done, adminback;
    DatabaseReference ref;
    StudentMember studentMember;
    String status = "student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        idnumber = findViewById(R.id.id_number_input1);
        firstname = findViewById(R.id.first_name_input1);
        lastname = findViewById(R.id.last_name_input1);
        course = findViewById(R.id.course_input1);
        year = findViewById(R.id.year_input1);
        done = findViewById(R.id.done_button);
        pass = findViewById(R.id.password_txt);

        studentMember = new StudentMember();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        done.setOnClickListener(new View.OnClickListener() {
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
                                int id = Integer.parseInt(idnumber.getText().toString());
                                String name = firstname.getText().toString() + " " + lastname.getText().toString();

                                studentMember.setIdNumber(id);
                                studentMember.setPassword(pass.getText().toString());
                                studentMember.setName(name);
                                studentMember.setStatus(status);
                                studentMember.setCourse(course.getText().toString());
                                studentMember.setYear(year.getText().toString());

                                ref.child(String.valueOf(id)).setValue(studentMember);
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
