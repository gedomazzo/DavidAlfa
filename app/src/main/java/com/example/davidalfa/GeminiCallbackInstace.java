package com.example.davidalfa;

import android.util.Log;
import android.widget.TextView;

public class GeminiCallbackInstace implements GeminiCallback {

    private static final String TAG = "AI REPORT";

    TextView tv;
    public GeminiCallbackInstace(TextView tv){
        this.tv = tv;
    }

    @Override
    public void onSuccess(String result) {
        Log.d(TAG, "Gemini Success: " + result);
        tv.setText(result);
    }

    @Override
    public void onFailure(Throwable error) {
        Log.e(TAG, "Gemini Failure: " + error.getMessage(), error);
        tv.setText(error.getMessage());
    }


}
