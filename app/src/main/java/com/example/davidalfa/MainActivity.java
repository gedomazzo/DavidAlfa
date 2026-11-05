package com.example.davidalfa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        graph = findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(produceChart());
        graph.addSeries(series);

        graph.getGridLabelRenderer().setHorizontalAxisTitle("Time (s)");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Voltage (V)");


    }

    public static DataPoint[] produceChart() {
        DataPoint[] points = new DataPoint[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new DataPoint(i, Math.cos(i) / 4.0);
        }
        return points;
    }
}
