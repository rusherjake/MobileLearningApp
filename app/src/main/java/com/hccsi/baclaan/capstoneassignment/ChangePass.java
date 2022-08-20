package com.hccsi.baclaan.capstoneassignment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePass extends AppCompatActivity {

    Button confirm;
    EditText pass1, pass2;
    DatabaseReference ref;
    String save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        confirm = findViewById(R.id.confirm);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);

        SharedPreferences pref = getSharedPreferences("com.hccsi.baclaan.capstoneassignment_login_status", MODE_PRIVATE);
        save = pref.getString("idNumberr", null);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Warning!")
                        .setMessage("Do you really want to change password?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if(pass1.getText().toString().equals(pass2.getText().toString())){
                                    ref = FirebaseDatabase.getInstance().getReference().child("Users").child(save);
                                    ref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            ref.child("password").setValue(pass1.getText().toString());

                                            finish();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(getBaseContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(getBaseContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}
