package com.hccsi.baclaan.capstoneassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClassroomStudent extends AppCompatActivity {

    EditText idnumber;
    Button done, adminback;
    DatabaseReference ref;
    StudentMember studentMember;
    String status = "student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom_student);
        idnumber = findViewById(R.id.studentidinput);
        done = findViewById(R.id.done_button);

        studentMember = new StudentMember();
        ref = FirebaseDatabase.getInstance().getReference().child("Classroom");
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstCheck = idnumber.getText().toString();

                if (firstCheck.isEmpty()) {
                    idnumber.setError("Please input value!");
                    return;
                            } else {
                                int id = Integer.parseInt(idnumber.getText().toString());
                                studentMember.setIdNumber(id);

                                ref.child(String.valueOf(id)).setValue(studentMember);
                                Toast.makeText(v.getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                                finish();

//                                Intent intent1 = new Intent(v.getContext(), AdminTeach.class);
//                                startActivity(intent1);

                            }
                        }
        });
    }


}
