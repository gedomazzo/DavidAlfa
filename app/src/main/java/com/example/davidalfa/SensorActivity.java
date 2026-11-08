package com.example.davidalfa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SensorActivity extends AppCompatActivity {

    TextView gyro;
    Button start;
    Button finish;
    ListView data;
    ArrayList<float[]> Sdata;
    Gyro sensor;
    ArrayAdapter<float[]> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        gyro = findViewById(R.id.gyro);
        start = findViewById(R.id.start);
        finish = findViewById(R.id.finish);
        data = findViewById(R.id.data);

        sensor = new Gyro(this, gyro);

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Sdata
        );
        data.setAdapter(adapter);
    }

    boolean state;
    public void Start(View view) {
            state = true;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    sensor.stamp();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            thread.start();

            Sdata = sensor.getData();
            sensor.cleanData();
            adapter.notifyDataSetChanged();
            state = false;

    }

    public void Finish(View view) {state = false; }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(1 , 1, 100, "Graph");
        menu.add(1, 1, 200, "AI");
        menu.add(1, 1, 300, "IM");
        menu.add(1 , 1, 400, "JSON");
        //menu.add(1, 1, 500, "Sensor");
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