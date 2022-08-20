package com.hccsi.baclaan.capstoneassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class StudentAdapter extends FirebaseRecyclerAdapter<
        StudentModel, StudentAdapter.StudentViewholder> {

    public StudentAdapter(
            @NonNull FirebaseRecyclerOptions<StudentModel> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull StudentViewholder holder,
                     int position, @NonNull StudentModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.sname.setText(model.getName());
        holder.sidNumber.setText(Integer.toString(model.getidNumber()));
        holder.spassword.setText(model.getPassword());
        holder.scourse.setText(model.getCourse());
        holder.syear.setText(model.getYear());

        holder.sedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.sname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.studentdialogcontent))
                        .create();

                View myview=dialogPlus.getHolderView();

                final EditText sname  = myview.findViewById(R.id.suname);
                final EditText sidNumber  = myview.findViewById(R.id.suidNumber);
                final EditText spassword=myview.findViewById(R.id.supassword);
                final EditText scourse=myview.findViewById(R.id.sucourse);
                final EditText syear=myview.findViewById(R.id.suyear);

                Button submit = myview.findViewById(R.id.susubmit);

                sname.setText(model.getName());
                sidNumber.setText(Integer.toString(model.getidNumber()));
                spassword.setText(model.getPassword());
                scourse.setText(model.getCourse());
                syear.setText(model.getYear());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();

                        map.put("name",sname.getText().toString());
                        map.put("password",spassword.getText().toString());
                        map.put("course",scourse.getText().toString());
                        map.put("year",syear.getText().toString());

//                        Map<Integer,Object> map2=new HashMap<>();
//
//                        map.put("idNumber",tidNumber.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });
        holder.sdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.sname.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure you want to delete this student??");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Users")
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
    public StudentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.studentview, parent, false);
        return new StudentAdapter.StudentViewholder(view);
    }

    class StudentViewholder
            extends RecyclerView.ViewHolder {

        TextView sname;
        TextView sidNumber;
        TextView spassword;
        TextView scourse;
        TextView syear;
        ImageView sdelete;
        ImageView sedit;

        public StudentViewholder(@NonNull View itemView)
        {
            super(itemView);

            sname= itemView.findViewById(R.id.sname_text2);
            sidNumber = itemView.findViewById(R.id.sidNumber_text2);
            spassword = itemView.findViewById(R.id.spassword_text2);
            scourse = itemView.findViewById(R.id.scourse_text2);
            syear = itemView.findViewById(R.id.syear_text2);

            sdelete = itemView.findViewById(R.id.sdeleteicon);
            sedit=itemView.findViewById(R.id.sediticon);

        }
    }
}
