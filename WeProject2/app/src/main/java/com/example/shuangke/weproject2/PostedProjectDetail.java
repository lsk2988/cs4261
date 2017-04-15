package com.example.shuangke.weproject2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostedProjectDetail extends AppCompatActivity {
    EditText input;
    TextView addMember;
    String addUserList;// 这个是post project 的人输入的想要加的的用户信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_project_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Posted Project Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a new team member");
        builder.setIcon(R.drawable.addmember);
        builder.setMessage("Please enter the user's email address.");
        input = new EditText(this);
        builder.setView(input);

        //set postitive Button
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addUserList = input.getText().toString();
                Toast.makeText(getApplicationContext(),"add User: " + addUserList,Toast.LENGTH_LONG).show();
            }
        });

        //set Nagative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog ad = builder.create();

        //click to invoke the dialog

        addMember = (TextView)findViewById(R.id.addMember);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });
    }

}
