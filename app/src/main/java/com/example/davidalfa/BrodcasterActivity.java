package com.example.davidalfa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrodcasterActivity extends AppCompatActivity {
        private TextView conn;
        private WiFiReciver wifiReceiver;



        private BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("MY_RECEIVER", "Broadcast received!");
                conn.setText("started to charge");
            }
        };
        IntentFilter filter = new IntentFilter();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_brodcaster);

            conn = findViewById(R.id.conn);


            filter.addAction(Intent.ACTION_POWER_CONNECTED);

            registerReceiver(receiver, filter);

        }

        @Override
        protected void onDestroy() {
            unregisterReceiver(receiver);
            super.onDestroy();
        }


    public void Start(View view) {

        registerReceiver(receiver, filter);


    }

    public void Finish(View view) {
        unregisterReceiver(receiver);
    }
}
