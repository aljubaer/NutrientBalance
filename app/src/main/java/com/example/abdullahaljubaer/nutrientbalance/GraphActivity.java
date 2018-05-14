package com.example.abdullahaljubaer.nutrientbalance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    BarChart chart ;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> barEntryLabels;
    BarDataSet barDataSet;
    BarData barData;
    double data[] = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            data = extras.getDoubleArray("data");
        }

        chart = (BarChart) findViewById(R.id.barchart);

        barEntries = new ArrayList<>();

        barEntryLabels = new ArrayList<String>();

        addValuesToBarEntry();

        addValuesToBarEntryLabels();

        barDataSet = new BarDataSet(barEntries, "Nutrient");

        barData = new BarData(barEntryLabels, barDataSet);

        barDataSet.setColors(new int[]{Color.rgb(42, 153, 55),
                Color.rgb(198, 59, 143), Color.rgb(89, 79, 226)});

        chart.setData(barData);

        chart.animateY(1000);


    }

    public void addValuesToBarEntry(){

        barEntries.add(new BarEntry((float) data[0], 0));
        barEntries.add(new BarEntry((float) data[1], 1));
        barEntries.add(new BarEntry((float) data[2], 2));

    }

    public void addValuesToBarEntryLabels(){

        barEntryLabels.add("N");
        barEntryLabels.add("P");
        barEntryLabels.add("K");

    }
}
