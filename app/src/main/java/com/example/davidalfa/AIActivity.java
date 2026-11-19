package com.example.davidalfa;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AIActivity extends AppCompatActivity {

    TextView TV;
    private ActivityResultLauncher<String> videoPickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiactivity);
        TV = findViewById(R.id.TV);

        videoPickerLauncher = createVideoPickerLauncher();
    }

    private ActivityResultLauncher<String> createVideoPickerLauncher() {
        return registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        processSelectedUri(uri);
                    }
                }
        );
    }

    private void processSelectedUri(Uri uri) {
        TV.setText("Reading video file...");


        new Thread(() -> {
            try (InputStream inputStream = getContentResolver().openInputStream(uri);
                 ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[8192];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteBuffer.write(buffer, 0, len);
                }

                byte[] videoBytes = byteBuffer.toByteArray();
                String mimeType = getContentResolver().getType(uri);
                if (mimeType == null) mimeType = "video/mp4";

                runOnUiThread(() -> TV.setText("Uploading video to Gemini (" + (videoBytes.length / (1024 * 1024)) + " MB)..."));

                GeminiCallbackInstace callback = new GeminiCallbackInstace(TV);
                GeminiManager.getInstance().analyzePhysicsVideo(videoBytes, mimeType, callback);

            } catch (Exception e) {
                Log.e("fds", "Error reading video file", e);
                runOnUiThread(() -> TV.setText("Error reading video: " + e.getMessage()));
            }
        }).start();


    }

    public void lanch(View view) {
        videoPickerLauncher.launch("video/*");
    }
}
