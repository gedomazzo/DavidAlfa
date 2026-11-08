package com.example.davidalfa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity{

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //menu.add(1 , 1, 100, "Graph");
        menu.add(1, 1, 200, "AI");
        menu.add(1, 1, 300, "IM");
        menu.add(1 , 1, 400, "JSON");
        menu.add(1, 1, 500, "Sensor");
        menu.add(1, 1, 600, "Broadcast");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        String tit = item.getTitle().toString();

        Intent shaw;

        if (tit.equals("Ai")) {
            //shaw = new Intent(this, Show.class);
        } else if (tit.equals("IM")) {
            //shaw = new Intent(this, Filter.class);
        } else if (tit.equals("JSON")) {
            //shaw = new Intent(this, Filter.class);
        } else if (tit.equals("Sensor")) {
            shaw = new Intent(this, SensorActivity.class);
        } else if (tit.equals("Broadcast")) {
            //shaw = new Intent(this, Filter.class);
        } else {
            shaw = new Intent(this, MainActivity.class);
        }
        //startActivity(shaw);

        return super.onOptionsItemSelected(item);
    }

}
