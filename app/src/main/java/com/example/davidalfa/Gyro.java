package com.example.davidalfa;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.util.ArrayList;

public class Gyro implements SensorEventListener {
    private float rx, ry, rz;
    private final Sensor Gyroscope;
    private final SensorManager sensorManager;
    private ArrayList<float[]> data;

    TextView gyro;

    public Gyro(Context context, TextView gyro){
        this.gyro = gyro;
        this.rx = 0;
        this.ry = 0;
        this.rz = 0;
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.Gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        this.data = new ArrayList<>();

        if (Gyroscope != null) {
            sensorManager.registerListener(this, Gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        this.rx = Math.round(event.values[0]*100)/100;
        this.ry = Math.round(event.values[1]*100)/100;
        this.rz = Math.round(event.values[2]*100)/100;

        float[] temp = {rx, ry, rz};

        gyro.setText(String.valueOf(rx + " " + ry + " " + rz));



    }

    public float getrX(){
        return this.rx;
    }

    public float getrY(){
        return this.ry;
    }

    public float getrZ(){
        return this.rz;
    }


    public ArrayList<float[]> getData(){return this.data;}


    public void cleanData() {
        data.clear();
    }

    public void stamp(){ float[] temp = {this.rx, this.ry, this.rz}; this.data.add(temp);}


}
