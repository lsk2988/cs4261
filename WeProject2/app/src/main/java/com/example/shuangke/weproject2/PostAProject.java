package com.example.shuangke.weproject2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class PostAProject extends AppCompatActivity {
    TextView from;
    TextView to;
    int year_y,month_y,day_y;
    int year_x,month_x,day_x;
    static final int DILOG_ID = 0;
    static final int DILOG_ID2 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_aproject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post My Project");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Calendar cal = Calendar.getInstance();
        year_x =cal.get(Calendar.YEAR);
        month_x =cal.get(Calendar.MONTH);
        day_x =cal.get(Calendar.DAY_OF_MONTH);

        year_y =cal.get(Calendar.YEAR);
        month_y =cal.get(Calendar.MONTH);
        day_y =cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

    }

    public void showDialogOnButtonClick(){
        from =(TextView) findViewById(R.id.fromDate);

        from.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID);
                    }
                }
        );

        to=(TextView) findViewById(R.id.toDate);
        to.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID2);
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DILOG_ID){
            return new DatePickerDialog(this,dpickerListenerFrom,year_x,month_x,day_x);
        }
        if(id == DILOG_ID2){
            return new DatePickerDialog(this,dpickerListenerTo,year_y,month_y,day_y);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListenerFrom = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            String formattedDate = year_x +"/"+month_x + "/"+day_x;
            from.setText(formattedDate);
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerListenerTo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_y = year;
            month_y = month + 1;
            day_y = dayOfMonth;
            String formattedDate = year_y +"/"+month_y + "/"+day_y;
            to.setText(formattedDate);
        }
    };

    public void gotoHome(View view) {
        Intent intent = new Intent(PostAProject.this, HomePage.class);
        startActivity(intent);
    }
}
