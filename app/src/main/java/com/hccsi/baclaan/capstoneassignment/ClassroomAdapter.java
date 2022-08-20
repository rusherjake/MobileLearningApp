package com.hccsi.baclaan.capstoneassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ClassroomAdapter extends FirebaseRecyclerAdapter<
        ClassroomModel, ClassroomAdapter.ClassroomViewholder> {

    public ClassroomAdapter(
            @NonNull FirebaseRecyclerOptions<ClassroomModel> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull ClassroomViewholder holder,
                     int position, @NonNull ClassroomModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.cclassid.setText(model.getclassid());
        holder.cclassname.setText(model.getclassname());
        holder.cteacherid.setText(model.getteacherid());
        holder.cteachername.setText(model.getteachername());


        holder.cedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.cclassid.getContext())
                        .setContentHolder(new ViewHolder(R.layout.classroomdialogcontent))
                        .create();

                View myview=dialogPlus.getHolderView();

                final EditText cclassid  = myview.findViewById(R.id.classid);
                final EditText cclassname  = myview.findViewById(R.id.classname);
                final EditText cteacherid  = myview.findViewById(R.id.teacherid);

                Button submit = myview.findViewById(R.id.cusubmit);

                cclassid.setText(model.getclassid());
                cclassname.setText(model.getclassname());
                cteacherid.setText(model.getteacherid());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> myMap=new HashMap<>();
                        //Map<Integer,Object> map = new HashMap<>();

                        myMap.put("classid",cclassid.getText().toString());
                        myMap.put("classname",cclassname.getText().toString());
                        myMap.put("teacherid",cteacherid.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Classroom")
                                .child(getRef(position).getKey()).updateChildren(myMap)
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

                Button viewstudents = myview.findViewById(R.id.viewclassstudent);

                viewstudents.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //v.getContext().startActivity(new Intent(v.getContext(), ViewClassroomStudent.class));
                        Intent intent = new Intent(v.getContext(), ViewClassroomStudent.class);
                        String viewclassid = cclassid.getText().toString();
                        String viewclassname = cclassname.getText().toString();
                        intent.putExtra("intentclassid", viewclassid);
                        intent.putExtra("intentclassname", viewclassname);
                        v.getContext().startActivity(intent);

                    }
                });


            }
        });
        holder.cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.cclassid.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure you want to delete this classroom??");

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
    public ClassroomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.classroomview, parent, false);
        return new ClassroomAdapter.ClassroomViewholder(view);
    }

    class ClassroomViewholder
            extends RecyclerView.ViewHolder {

        TextView cclassid;
        TextView cclassname;
        TextView cteacherid;
        TextView cteachername;
        ImageView cdelete;
        ImageView cedit;

        public ClassroomViewholder(@NonNull View itemView)
        {
            super(itemView);

            cclassid = itemView.findViewById(R.id.classid_text);
            cclassname = itemView.findViewById(R.id.classname_text);
            cteacherid = itemView.findViewById(R.id.teacherid_text);
            cteachername = itemView.findViewById(R.id.teachername_text);

            cdelete = itemView.findViewById(R.id.cdeleteicon);
            cedit=itemView.findViewById(R.id.cediticon);

        }
    }
}
