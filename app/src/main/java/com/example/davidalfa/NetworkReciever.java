package com.example.davidalfa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

public class NetworkReciever extends BroadcastReceiver {

    private TextView tv;
    private boolean isLisening;

    public NetworkReciever(TextView tv) {
        this.tv = tv;
        this.isLisening = false;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isLisening) return;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            tv.setText("Connected");
        } else {
            tv.setText("Not connected");
        }
    }

    public void setLisening(boolean lis) {
        this.isLisening = lis;
    }
}
