package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StudentClassworkView extends AppCompatActivity {

    StudentClassworkViewAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    RecyclerView rec;

    public StudentClassworkView() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_classwork_view);
        Intent intent = getIntent();
        String intentclassworkid = intent.getStringExtra("intentclassworkid");
        String viewclassworkid = String.valueOf(intentclassworkid);


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

        Query mbase = FirebaseDatabase.getInstance().getReference().child("StudentClasswork").orderByChild(viewclassworkid);
        rec=(RecyclerView)findViewById(R.id.recview);
        rec.setLayoutManager(new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<ClassworkModel> options
                = new FirebaseRecyclerOptions.Builder<ClassworkModel>()
                .setQuery(mbase, ClassworkModel.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new StudentClassworkViewAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        rec.setAdapter(adapter);
        //Toast.makeText(view.getContext(), Integer.toString(options.getSnapshots().size()) , Toast.LENGTH_SHORT).show();


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