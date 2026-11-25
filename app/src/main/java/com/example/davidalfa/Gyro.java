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
    private Sensor gyroscope;
    private final SensorManager sensorManager;
    private final ArrayList<float[]> data;

    TextView gyro;

    public Gyro(Context context, TextView gyro){
        this.gyro = gyro;
        this.rx = 0;
        this.ry = 0;
        this.rz = 0;
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        // Try Gyroscope first, fallback to Accelerometer if unavailable on device/emulator
        this.gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (this.gyroscope == null) {
            this.gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        this.data = new ArrayList<>();

        if (gyro != null) {
            gyro.setText("0.0 0.0 0.0");
        }

        if (gyroscope != null) {
            // SENSOR_DELAY_GAME delivers immediate updates (~20ms latency)
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME);
        } else if (gyro != null) {
            gyro.setText("No sensor found!");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.rx = Math.round(event.values[0] * 100) / 100f;
        this.ry = Math.round(event.values[1] * 100) / 100f;
        this.rz = Math.round(event.values[2] * 100) / 100f;

        if (gyro != null) {
            gyro.setText(rx + " " + ry + " " + rz);
        }
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

    public ArrayList<float[]> getData(){
        return this.data;
    }

    public void cleanData() {
        data.clear();
    }

    public void stamp(){
        float[] temp = {this.rx, this.ry, this.rz};
        this.data.add(temp);
    }
}
