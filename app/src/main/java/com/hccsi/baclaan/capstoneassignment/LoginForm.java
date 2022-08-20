package com.hccsi.baclaan.capstoneassignment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

public class LoginForm extends AppCompatActivity {

    Button login;
    EditText idNumber, pass;
    String password="admin", id="0000", statuss;
    DatabaseReference ref;
    public static String idd, coursee, name;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        /**FirebaseDatabase.getInstance().setPersistenceEnabled(true);**/

        idNumber = findViewById(R.id.username_input1);
        pass = findViewById(R.id.password_txt);


        login = findViewById(R.id.login_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.loading_popup);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                idd = idNumber.getText().toString();


//                if ((id.equals(idNumber.getText().toString())) && (password.equals(pass.getText().toString()))){
//                    Intent intent1 = new Intent(v.getContext(), AddForm.class);
//                    startActivity(intent1);
//                    finish();
//                }
//                else{
                    ref = FirebaseDatabase.getInstance().getReference("Users").child(idNumber.getText().toString());
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                String passWord = dataSnapshot.child("password").getValue().toString();
                                name = dataSnapshot.child("name").getValue().toString();
                                String statussss = dataSnapshot.child("status").getValue().toString();
                                statuss = dataSnapshot.child("status").getValue().toString();
                                if (passWord.equals(pass.getText().toString())){
                                    if (dataSnapshot.child("status").getValue(String.class).equals("admin")){
                                        Intent intent1 = new Intent(v.getContext(), AdminActivity.class);
                                        dialog.dismiss();
                                        Store();
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else if (dataSnapshot.child("status").getValue(String.class).equals("student")){
                                        coursee = dataSnapshot.child("course").getValue().toString();
                                        Intent intent1 = new Intent(v.getContext(), StudentActivity.class);
                                        dialog.dismiss();
                                        Store();
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else if (dataSnapshot.child("status").getValue(String.class).equals("teacher")){
                                        Intent intent1 = new Intent(v.getContext(), TeacherActivity.class);
                                        dialog.dismiss();
                                        Store();
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else {
                                        dialog.dismiss();
                                        Toast.makeText(v.getContext(), "Error Unknown", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    dialog.dismiss();
                                    Toast.makeText(v.getContext(), "Invalid ID or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(v.getContext(), "Invalid ID or Password; Error 101", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            dialog.dismiss();
                            Toast.makeText(v.getContext(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                        }
                    });
                //}
            }
        });
    }

    public void Store(){
        SharedPreferences pref = getSharedPreferences("com.hccsi.baclaan.capstoneassignment_login_status", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("login_status", "on");
        editor.putString("idNumberr", idd);
        //editor.putString("coursesave", coursee);
        editor.putString("status", statuss);
        editor.putString("name", name);
        editor.commit();
    }
}
