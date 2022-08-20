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


public class MyAdapter extends FirebaseRecyclerAdapter<
        TeacherModel, MyAdapter.TeacherViewholder> {

    public MyAdapter(
            @NonNull FirebaseRecyclerOptions<TeacherModel> options) {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull TeacherViewholder holder,
                     int position, @NonNull TeacherModel model) {

        // Add name from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")

        holder.tname.setText(model.getName());
        holder.tidNumber.setText(Integer.toString(model.getidNumber()));
        holder.tpassword.setText(model.getpassword());

        holder.tedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.tname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .create();

                View myview=dialogPlus.getHolderView();

                final EditText tname  = myview.findViewById(R.id.uname);
                final EditText tidNumber  = myview.findViewById(R.id.uidNumber);
                final EditText tpassword=myview.findViewById(R.id.upassword);

                Button submit=myview.findViewById(R.id.usubmit);

                tname.setText(model.getName());
                tidNumber.setText(Integer.toString(model.getidNumber()));
                tpassword.setText(model.getpassword());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();

                        map.put("name",tname.getText().toString());

                        map.put("password",tpassword.getText().toString());

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
        holder.tdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.tname.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure you want to delete this teacher?");

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
    public TeacherViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow, parent, false);
        return new MyAdapter.TeacherViewholder(view);
    }

    class TeacherViewholder
            extends RecyclerView.ViewHolder {

        TextView tname;
        TextView tidNumber;
        TextView tpassword;
        ImageView tdelete;
        ImageView tedit;

        public TeacherViewholder(@NonNull View itemView)
        {
            super(itemView);

            tname= itemView.findViewById(R.id.name_text2);
            tidNumber = itemView.findViewById(R.id.idNumber_text2);
            tpassword = itemView.findViewById(R.id.password_text2);

            tdelete = itemView.findViewById(R.id.deleteicon);
            tedit=itemView.findViewById(R.id.editicon);

        }
    }
}