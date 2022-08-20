package com.hccsi.baclaan.capstoneassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewClassroomStudentAdapter extends FirebaseRecyclerAdapter<
        ClassroomModel, ViewClassroomStudentAdapter.ViewClassroomStudentViewholder> {

    public ViewClassroomStudentAdapter(
            @NonNull FirebaseRecyclerOptions<ClassroomModel> options) {
        super(options);
    }
        @Override
        protected void
        onBindViewHolder(@NonNull ViewClassroomStudentViewholder holder,
        int position, @NonNull ClassroomModel model) {

            // Add name from model class (here
            // "person.class")to appropriate view in Card
            // view (here "person.xml")


            holder.cstudentid.setText(model.getstudentid());
            holder.cstudentname.setText(model.getstudentname());

            holder.cdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.cstudentname.getContext());
                    builder.setTitle("Delete Panel");
                    builder.setMessage("Are you sure you want to remove this student from this class?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseDatabase.getInstance().getReference().child("Classroom")
                                    .child(getRef(position).getKey()).removeValue();
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.show();
                }
            });


        }

        @NonNull
        @Override
        public ViewClassroomStudentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view
                    = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.viewclassroomstudent, parent, false);
            return new ViewClassroomStudentAdapter.ViewClassroomStudentViewholder(view);
        }

        class ViewClassroomStudentViewholder
                extends RecyclerView.ViewHolder {

            TextView cstudentname;
            TextView cstudentid;
            ImageView cdelete;

            public ViewClassroomStudentViewholder(@NonNull View itemView)
            {
                super(itemView);

                cstudentname = itemView.findViewById(R.id.cstudentname_text);
                cstudentid = itemView.findViewById(R.id.cstudentid_text);

                cdelete = itemView.findViewById(R.id.cvdeleteicon);

            }
        }
    }
