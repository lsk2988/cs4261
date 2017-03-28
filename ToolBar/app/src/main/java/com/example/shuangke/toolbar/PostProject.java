package com.example.shuangke.toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class PostProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Post a new project");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




    }
    public void gotoHomePage(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void gotoUserProjectInfor(View view){

        String project_title = "";
        String project_description = "";
        int teamSize = 0;
        String fromDate = "";
        String toDate = "";
        ArrayList categories = new ArrayList();
        double rewards = 0;
        ArrayList contactInfo = new ArrayList();

        EditText projectTitleET = (EditText)findViewById(R.id.project_title);
        EditText projectDescriptionET = (EditText)findViewById(R.id.project_description);
        EditText teamSizeET = (EditText)findViewById(R.id.team_size);
        EditText fromDateET = (EditText)findViewById(R.id.editText5);
        EditText toDateET = (EditText)findViewById(R.id.editText6);
        EditText rewardsET = (EditText)findViewById(R.id.project_reward);
        EditText emailET = (EditText)findViewById(R.id.email_address);
        EditText phoneET = (EditText)findViewById(R.id.contact_phoneNumber);
        EditText addressET = (EditText)findViewById(R.id.contact_adress);

        project_title = projectTitleET.getText().toString();//var store project title
        project_description =projectDescriptionET.getText().toString();//var store project description
        String teamSizeStr = teamSizeET.getText().toString();
        if(teamSizeStr.equals("")){
            teamSize = 0;
        }
        else{
            teamSize = Integer.parseInt(teamSizeET.getText().toString());
        }


        fromDate = fromDateET.getText().toString();
        toDate = toDateET.getText().toString();
        String rewardstr = rewardsET.getText().toString();
        if(rewardstr.equals("")){
            rewards = 0;
        }
        else{
            rewards = Double.parseDouble(rewardsET.getText().toString());
        }


        String email = emailET.getText().toString();
        String phone = phoneET.getText().toString();
        String address = addressET.getText().toString();

        if(email.length()!= 0 && email != null){
            contactInfo.add(email);
        }
        if(phone.length()!= 0 && phone != null){
            contactInfo.add(phone);
        }
        if(address.length()!= 0 && address != null){
            contactInfo.add(address);
        }

        CheckBox healthCK = (CheckBox)findViewById(R.id.health_checkBox);
        CheckBox musicCK = (CheckBox)findViewById(R.id.MusicCk1);
        CheckBox csCK = (CheckBox)findViewById(R.id.csCK1);
        CheckBox sportsCK = (CheckBox)findViewById(R.id.sportsCK1);
        CheckBox businessCK = (CheckBox)findViewById(R.id.businessCK1);
        CheckBox artCK = (CheckBox)findViewById(R.id.ArtsCK1);

        if(healthCK.isChecked()){
            categories.add("health");
        }
        if(musicCK.isChecked()){
            categories.add("music");
        }
        if(csCK.isChecked()){
            categories.add("CS");
        }
        if(sportsCK.isChecked()){
            categories.add("sports");
        }
        if(businessCK.isChecked()){
            categories.add("business");
        }
        if(artCK.isChecked()){
            categories.add("arts");
        }


        System.out.println("project title is: " + project_title +"\n"+project_description + "\n" + teamSize + "\n" + fromDate + "\n" + toDate + "\n" + categories + "\n" + contactInfo);
        Intent intent = new Intent(this,userProjectInfor.class);
        startActivity(intent);
    }

}
