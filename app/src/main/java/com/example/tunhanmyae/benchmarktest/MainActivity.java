package com.example.tunhanmyae.benchmarktest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tunhanmyae.benchmarktest.Core.SpeedTest;
import com.example.tunhanmyae.benchmarktest.Database.Database;
import com.example.tunhanmyae.benchmarktest.model.Speed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    double speed;
    Speed test;
    private static final String TAG = "MainActivity";

    Button averageSpeed;
    LinearLayout container;
    private DatePickerDialog.OnDateSetListener mDateSetListener;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        averageSpeed = (Button) findViewById(R.id.showButton);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                hourly();

                handler.postDelayed(this, 10000);
            }
        }, 20000);






        averageSpeed.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.show();


            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener()
        {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month+1;
                StringBuilder s = new StringBuilder(20);
                s.append(year);
                s.append("-");
                s.append(month);
                s.append("-");
                s.append(dayOfMonth);

                Intent intent = new Intent(getApplicationContext(),AverageSpeed.class);
                intent.putExtra("date",s.toString());
                startActivity(intent);






            }
        };





    }

    private void hourly() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Log.d("DAte","Date" + date);

        test = new Speed();
        test.setUpload(SpeedTest.UploadSpeed());
        test.setDownload(SpeedTest.DownloadSpeed());
        test.setPing(SpeedTest.Ping());
        test.setDate(date);

        new Database(getBaseContext()).save(new Speed(
                test.getUpload(),
                test.getDownload(),
                test.getPing(),
                test.getDate()
        ));
    }


}
