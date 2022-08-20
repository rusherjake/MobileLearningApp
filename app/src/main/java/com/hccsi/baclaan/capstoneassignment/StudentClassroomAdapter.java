package com.hccsi.baclaan.capstoneassignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class StudentClassroomAdapter extends FirebaseRecyclerAdapter<
        ClassroomModel, StudentClassroomAdapter.StudentClassroomViewholder> {

    public StudentClassroomAdapter(
            @NonNull FirebaseRecyclerOptions<ClassroomModel> options) {
        super(options);
    }

     @Override
    protected void
    onBindViewHolder(@NonNull StudentClassroomAdapter.StudentClassroomViewholder holder,
                     int position, @NonNull ClassroomModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.classname.setText(model.getclassname());
        holder.classid.setText(model.getclassid());

        holder.viewclassbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentClassOpen.class);

                String viewclassid = holder.classid.getText().toString();
                intent.putExtra("intentclassid", viewclassid);
                String viewclassname = holder.classname.getText().toString();
                intent.putExtra("intentclassname", viewclassname);
                v.getContext().startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public StudentClassroomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacherclassroomview, parent, false);
        return new StudentClassroomAdapter.StudentClassroomViewholder(view);
    }

    class StudentClassroomViewholder
            extends RecyclerView.ViewHolder {

        TextView classid;
        TextView classname;
        Button viewclassbutton;

        public StudentClassroomViewholder(@NonNull View itemView)
        {
            super(itemView);

            classid= itemView.findViewById(R.id.classid_view);
            classname = itemView.findViewById(R.id.classname_view);

            viewclassbutton = itemView.findViewById(R.id.viewclassbutton);

        }
    }
}

