package com.hccsi.baclaan.capstoneassignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminProfile extends Fragment {

    Button logout, changepass;

    public AdminProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        Toolbar tool = v.findViewById(R.id.toolbarr);

        logout = v.findViewById(R.id.admin_logout_button);
        changepass = v.findViewById(R.id.admin_change_password_button);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePass.class);
                startActivity(intent);
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutt();
            }
        });

        return v;
    }


    public void logoutt(){                   //logout button
        new AlertDialog.Builder(getActivity())
                .setTitle("Warning!")
                .setMessage("Are you sure you want to logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        SharedPreferences pref = getActivity().getSharedPreferences("com.hccsi.baclaan.capstoneassignment_status", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("login_status", "off");
                        editor.commit();
                        Intent intent1 = new Intent(getContext(), LoginForm.class);
                        startActivity(intent1);
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}