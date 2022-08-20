package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ViewClassroomStudent extends AppCompatActivity {

    FloatingActionButton fb;

    ViewClassroomStudentAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    RecyclerView rec;

    public ViewClassroomStudent() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom_student);
        //Toolbar tool = findViewById(R.id.toolbarr);

        Intent intent = getIntent();
        String viewclassid = intent.getStringExtra("intentclassid");
        String vclassid = String.valueOf(viewclassid);

        String viewclassname = intent.getStringExtra("intentclassname");
        String vclassname = String.valueOf(viewclassname);

        TextView classidtextview = findViewById(R.id.classstudentviewtext);
        classidtextview.setText(vclassid + " - " + vclassname);


        //tool.setTitle(classidref);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Classroom");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query mbase = FirebaseDatabase.getInstance().getReference().child("Classroom").orderByChild("classid").equalTo(vclassid);
        rec=(RecyclerView)findViewById(R.id.recview);
        rec.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<ClassroomModel> options
                = new FirebaseRecyclerOptions.Builder<ClassroomModel>()
                .setQuery(mbase, ClassroomModel.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new ViewClassroomStudentAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        rec.setAdapter(adapter);
        //Toast.makeText(view.getContext(), Integer.toString(options.getSnapshots().size()) , Toast.LENGTH_SHORT).show();

        fb=(FloatingActionButton)findViewById(R.id.classroomstudentadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), AddClassroom.class);
                startActivity(intent1);
            }
        });

        return;
    }
    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}