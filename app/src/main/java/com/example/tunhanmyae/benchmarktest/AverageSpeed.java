package com.example.tunhanmyae.benchmarktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tunhanmyae.benchmarktest.Database.Database;
import com.example.tunhanmyae.benchmarktest.common.Common;
import com.example.tunhanmyae.benchmarktest.model.Speed;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class AverageSpeed extends AppCompatActivity {
    List<Speed> averageSpeed = new ArrayList<Speed>();
    String date;
    String average[]={"Average Upload","Average Download","Average Ping"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_speed);

        if (getIntent() != null) {
            date = getIntent().getStringExtra("date");
            averageSpeed = new Database(getBaseContext()).getSpeedwithDate(date);

        }
        if (!date.isEmpty() && date != null) {
            pieChart();

        }



    }

    private void pieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();

        for (Speed speed:averageSpeed)
        {
            Common.uploadTotal+=speed.getUpload();
            Common.downloadTotal+=speed.getDownload();
            Common.pingTotal+=speed.getPing();

        }
        Common.uploadAverage=Common.uploadTotal/averageSpeed.size();
        Common.downloadAverage=Common.downloadTotal/averageSpeed.size();
        Common.pingAverage=Common.pingTotal/averageSpeed.size();
        pieEntries.add(new PieEntry((float) Common.uploadAverage,average[0]));
        pieEntries.add(new PieEntry((float) Common.downloadAverage,average[1]));
        pieEntries.add(new PieEntry((float) Common.pingAverage,average[2]));

        PieDataSet dataSet = new PieDataSet(pieEntries,"Benchmark Test");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.invalidate();








    }
}
