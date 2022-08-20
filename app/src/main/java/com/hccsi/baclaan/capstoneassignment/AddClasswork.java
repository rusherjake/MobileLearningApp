package com.hccsi.baclaan.capstoneassignment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddClasswork extends AppCompatActivity {

    public Uri uri, downloadUri;
    EditText details, duedate;
    ImageView imagefile;
    Button done;
    DatabaseReference ref;
    ClassworkModel classworkModel;
    Intent myFileintent;
    TextView filetext;
    StorageReference storef;
    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classwork);

        Intent intent = getIntent();
        String intentclassid = intent.getStringExtra("intentclassid");
        String viewclassid = String.valueOf(intentclassid);





        filetext=findViewById(R.id.filetextfield);
        duedate=findViewById(R.id.duedate_input);
        details = findViewById(R.id.details_input);
        imagefile=findViewById(R.id.imagefile);
        done = findViewById(R.id.done_button);

        duedate.setInputType(InputType.TYPE_NULL);

        duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(duedate);
            }
        });

        classworkModel = new ClassworkModel();
        ref = FirebaseDatabase.getInstance().getReference().child("Classwork");


        imagefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileintent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileintent.setType("*/*");
                startActivityForResult(myFileintent, 1);
            }
        });





//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//            }
//        });
//

//        imagefile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Filechooser();
//            }
//        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getBaseContext().getSharedPreferences("com.hccsi.baclaan.capstoneassignment_login_status", MODE_PRIVATE);
                final String save = pref.getString("idNumberr", null);


//                if (uri != null) {
//                    final StorageReference sref = storef.child(System.currentTimeMillis() + "." + getExtension(uri));
//                    sref.putFile(uri)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    // Get a URL to the uploaded content
//                                    sref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            downloadUri = uri;
//                                            link = downloadUri.toString();
//
//                                            if (downloadUri != null) {
//                                                classworkModel.setlink(downloadUri.toString());
//                                            } else {
//                                                classworkModel.setlink("No file attached");
//                                            }
//                                        }
//                                    });
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception exception) {
//                                    // Handle unsuccessful uploads
//                                    // ...
//                                }
//                            });
//                }else{
//                    Toast.makeText(getBaseContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
//                }
                //uploadToFirebase(uri);

                String det;
                det = details.getText().toString();
                String due;
                due = duedate.getText().toString();
                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                classworkModel.setclassworkid(String.valueOf(System.currentTimeMillis()));
                classworkModel.setduedate(due);
                classworkModel.setclassid(viewclassid);
                classworkModel.setauth(save);
                classworkModel.setdetails(det);
                classworkModel.setdate(mydate);
                classworkModel.setlink("File not attached");




                ref.child(String.valueOf(System.currentTimeMillis())).setValue(classworkModel);
                Toast.makeText(v.getContext(), "Added Successfully", Toast.LENGTH_SHORT).show();

                finish();

//                                Intent intent1 = new Intent(v.getContext(), AdminTeach.class);
//                                startActivity(intent1);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data!= null && data.getData() != null){
            uri = data.getData();
            filetext.setText(uri.getPath());

        }


//        switch (requestCode)
//        {
//            case 10:
//                if (resultCode==RESULT_OK){
//                    String path = data.getData().getPath();
//                    filetext.setText(path);
//
//                }
//
//        }
    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(AddClasswork.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(AddClasswork.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
//    private void Filechooser(){
//        Intent intent = new Intent();
//        intent.setType("*/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 1);
//    }

    private String getExtension(Uri uri){
        ContentResolver cr= getBaseContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadToFirebase(Uri uri){

        final StorageReference fileRef = storef.child(System.currentTimeMillis() + "." + getExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}