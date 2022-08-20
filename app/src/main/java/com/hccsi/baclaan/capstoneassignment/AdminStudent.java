package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminStudent extends Fragment {

    FloatingActionButton fb;

    StudentAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    RecyclerView rec;

    public AdminStudent() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_student, container, false);
        Toolbar tool = v.findViewById(R.id.toolbarr);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Query mbase = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("status").equalTo("student");
        rec = (RecyclerView) v.findViewById(R.id.recview);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<StudentModel> options
                = new FirebaseRecyclerOptions.Builder<StudentModel>()
                .setQuery(mbase, StudentModel.class)
                .build();

// Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new StudentAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        rec.setAdapter(adapter);
        //Toast.makeText(v.getContext(), Integer.toString(options.getSnapshots().size()) , Toast.LENGTH_SHORT).show();

        fb = (FloatingActionButton) v.findViewById(R.id.studentadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(v.getContext(), AddStudent.class);
                startActivity(intent1);
            }
        });

        return v;
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
    // data from database on stoping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}