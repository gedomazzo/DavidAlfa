package com.example.davidalfa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

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

        Sdata = sensor.getData();
        adapter = new ArrayAdapter<float[]>(
                this,
                android.R.layout.simple_list_item_1,
                Sdata
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                float[] item = getItem(position);
                if (item != null && item.length >= 3) {
                    textView.setText("X: " + item[0] + " | Y: " + item[1] + " | Z: " + item[2]);
                } else if (item != null) {
                    textView.setText(Arrays.toString(item));
                }
                return view;
            }
        };
        data.setAdapter(adapter);
    }

    boolean state = false;

    public void Start(View view) {
        if (state) return;
        state = true;
        sensor.cleanData();


        Thread thread = new Thread() {
            @Override
            public void run() {
                while (state) {
                    sensor.stamp();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        thread.start();
    }

    public void Finish(View view) {
        state = false;
        adapter.notifyDataSetChanged();
    }

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
            shaw = new Intent(this, AIActivity.class);
        } else if (tit.equals("JSON")) {
            //shaw = new Intent(this, Filter.class);
        } else if (tit.equals("Sensor")) {
            shaw = new Intent(this, SensorActivity.class);
        } else if (tit.equals("Broadcast")) {
            shaw = new Intent(this, BrodcasterActivity.class);
            startActivity(shaw);
        } else {
            shaw = new Intent(this, MainActivity.class);
        }
        //startActivity(shaw);

        return super.onOptionsItemSelected(item);
    }
}
