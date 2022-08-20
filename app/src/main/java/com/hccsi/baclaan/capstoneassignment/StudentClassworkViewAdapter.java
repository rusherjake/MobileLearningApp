package com.hccsi.baclaan.capstoneassignment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StudentClassworkViewAdapter extends FirebaseRecyclerAdapter<
            ClassworkModel, StudentClassworkViewAdapter.StudentClassworkViewholder> {

        public StudentClassworkViewAdapter(
                @NonNull FirebaseRecyclerOptions<ClassworkModel> options) {
            super(options);
        }
        @Override
        protected void
        onBindViewHolder(@NonNull StudentClassworkViewAdapter.StudentClassworkViewholder holder,
                         int position, @NonNull ClassworkModel model) {

            // Add name from model class (here
            // "person.class")to appropriate view in Card
            // view (here "person.xml")
//
//            holder.details.setText(model.getdetails());
//            holder.dlink.setText(model.getlink());
//            holder.duedate.setText(model.getduedate());
//            holder.grade.setText(model.getgrade());
//            holder.studentname.setText(model.getstudentname());

//
//            holder.workresponse.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });





        }

        @NonNull
        @Override
        public StudentClassworkViewAdapter.StudentClassworkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view
                    = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.studentclassworkview, parent, false);
            return new StudentClassworkViewAdapter.StudentClassworkViewholder(view);
        }

        class StudentClassworkViewholder
                extends RecyclerView.ViewHolder {

            TextView details;
            TextView grade;
            TextView studentname;
            TextView duedate;
            TextView dlink;
            Button gradebutton;

            public StudentClassworkViewholder(@NonNull View itemView)
            {
                super(itemView);

                details= itemView.findViewById(R.id.details_text);
                dlink = itemView.findViewById(R.id.link_text);
                duedate = itemView.findViewById(R.id.statusdue);
                grade = itemView.findViewById(R.id.gradework);
                studentname  = itemView.findViewById(R.id.author_text);
                Linkify.addLinks(dlink, Linkify.ALL);

                gradebutton = itemView.findViewById(R.id.gradebutton);


            }
        }
    }