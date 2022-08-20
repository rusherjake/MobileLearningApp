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

public class StudentClassworkAdapter extends FirebaseRecyclerAdapter<
            ClassworkModel,StudentClassworkAdapter.StudentClassworkViewholder> {

    public StudentClassworkAdapter(
                @NonNull FirebaseRecyclerOptions<ClassworkModel> options) {
            super(options);
        }
        @Override
    protected void
    onBindViewHolder(@NonNull StudentClassworkViewholder holder,
                     int position, @NonNull ClassworkModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.details.setText(model.getdetails());
        holder.duedate.setText(model.getduedate());
        holder.dlink.setText(model.getlink());
        Linkify.addLinks(holder.dlink, Linkify.ALL);

    }

    @NonNull
    @Override
    public StudentClassworkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studentclasswork, parent, false);
        return new StudentClassworkAdapter.StudentClassworkViewholder(view);
    }

    class StudentClassworkViewholder
            extends RecyclerView.ViewHolder {

        TextView details;
        TextView duedate;
        TextView dlink;
        Button workresponse;

        public StudentClassworkViewholder(@NonNull View itemView)
        {
            super(itemView);

            details= itemView.findViewById(R.id.details_text);
            duedate = itemView.findViewById(R.id.duedate_text);
            dlink = itemView.findViewById(R.id.link_text);

            workresponse=itemView.findViewById(R.id.workresponse);


        }
    }
}