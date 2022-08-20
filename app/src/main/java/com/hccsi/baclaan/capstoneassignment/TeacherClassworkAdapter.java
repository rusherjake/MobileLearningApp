package com.hccsi.baclaan.capstoneassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;

public class TeacherClassworkAdapter extends FirebaseRecyclerAdapter<
            ClassworkModel, TeacherClassworkAdapter.TeacherClassworkViewholder> {

        public TeacherClassworkAdapter(
                @NonNull FirebaseRecyclerOptions<ClassworkModel> options) {
            super(options);
        }
        @Override
    protected void
    onBindViewHolder(@NonNull TeacherClassworkViewholder holder,
                     int position, @NonNull ClassworkModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.details.setText(model.getdetails());
        holder.duedate.setText(model.getduedate());
        holder.dlink.setText(model.getlink());
            Linkify.addLinks(holder.dlink, Linkify.ALL);

            holder.workdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(holder.details.getContext());
                    builder.setTitle("Delete Panel");
                    builder.setMessage("Are you sure you want to delete this classwork??");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseDatabase.getInstance().getReference().child("Classwork")
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



        holder.workresponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentClassworkView.class);

                String intentclassworkid = model.getclassworkid().toString();
                intent.putExtra("intentclassworkid", intentclassworkid);

                v.getContext().startActivity(intent);
            }
        });





    }

    @NonNull
    @Override
    public TeacherClassworkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.classwork, parent, false);
        return new TeacherClassworkAdapter.TeacherClassworkViewholder(view);
    }

    class TeacherClassworkViewholder
            extends RecyclerView.ViewHolder {

        TextView details;
        TextView duedate;
        TextView dlink;
        Button workedit, workdelete, workresponse;

        public TeacherClassworkViewholder(@NonNull View itemView)
        {
            super(itemView);

            details= itemView.findViewById(R.id.details_text);
            duedate = itemView.findViewById(R.id.duedate_text);
            dlink = itemView.findViewById(R.id.link_text);



            workdelete = itemView.findViewById(R.id.workdelete);
            workedit=itemView.findViewById(R.id.workedit);
            workresponse=itemView.findViewById(R.id.workresponse);


        }
    }
}