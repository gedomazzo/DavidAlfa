package com.example.davidalfa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
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


        NetworkReciever receiver;

        IntentFilter filter = new IntentFilter();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_brodcaster);

            conn = findViewById(R.id.conn);
            receiver = new NetworkReciever(conn);

            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        }

        @Override
        protected void onDestroy() {
            unregisterReceiver(receiver);
            super.onDestroy();
        }


    public void Start(View view) {
        registerReceiver(receiver, filter);
        receiver.setLisening(true);
    }

    public void Finish(View view) {
        unregisterReceiver(receiver);
        receiver.setLisening(false);
    }
}
