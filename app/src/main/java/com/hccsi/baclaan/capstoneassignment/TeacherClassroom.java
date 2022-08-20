package com.hccsi.baclaan.capstoneassignment;

import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherClassroom extends Fragment {

    TeacherClassroomAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    RecyclerView rec;

    public TeacherClassroom() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teacher_classroom, container, false);
        Toolbar tool = v.findViewById(R.id.toolbarr);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Classroom");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        SharedPreferences pref = getActivity().getSharedPreferences("com.hccsi.baclaan.capstoneassignment_login_status", MODE_PRIVATE);
        final String save = pref.getString("idNumberr", null);

        Query mbase = FirebaseDatabase.getInstance().getReference().child("Classroom").orderByChild("teacherid").equalTo(save);
        rec=(RecyclerView)v.findViewById(R.id.recview);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<ClassroomModel> options
                = new FirebaseRecyclerOptions.Builder<ClassroomModel>()
                .setQuery(mbase, ClassroomModel.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new TeacherClassroomAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        rec.setAdapter(adapter);
        //Toast.makeText(v.getContext(), Integer.toString(options.getSnapshots().size()) , Toast.LENGTH_SHORT).show();

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
    // data from database on stopping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}
