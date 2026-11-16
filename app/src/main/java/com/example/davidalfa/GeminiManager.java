package com.example.davidalfa;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.BlobPart;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.Part;
import com.google.ai.client.generativeai.type.TextPart;

import java.util.ArrayList;
import java.util.List;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

public class GeminiManager {
    private static GeminiManager instance;
    private GenerativeModel gemini;

    private GeminiManager(){
        gemini = new GenerativeModel("gemini-3.5-flash", BuildConfig.Gemini_API_Key);
    }


    public static GeminiManager getInstance(){
        if (instance == null){
            instance = new GeminiManager();
        }
        return instance;
    }


    public void sendTextPrompt(String prompt, GeminiCallback callback) {
        gemini.generateContent(prompt,
                new Continuation<GenerateContentResponse>() {
                    @NonNull
                    @Override
                    public CoroutineContext getContext() {
                        return Dispatchers.getIO();
                    }

                    @Override
                    @SuppressWarnings("all")
                    public void resumeWith(@NonNull Object result) {
                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        if (result instanceof Result.Failure) {
                            Throwable error = ((Result.Failure) result).exception;
                            mainHandler.post(() -> callback.onFailure(error));
                        } else if (result instanceof GenerateContentResponse) {
                            String text = ((GenerateContentResponse) result).getText();
                            mainHandler.post(() -> callback.onSuccess(text));
                        } else {
                            mainHandler.post(() -> callback.onFailure(new Exception("Unknown result: " + result)));
                        }
                    }
                }
        );
    }


    public void sendTextWithFilePrompt(String prompt, byte[] bytes, String mimeType, GeminiCallback callback){
        List<Part> parts = new ArrayList<>();
        parts.add(new TextPart(prompt));
        parts.add(new BlobPart(mimeType, bytes));

        Content[] content = new Content[1];
        content[0] = new Content(parts);

        gemini.generateContent(content,
                new Continuation<GenerateContentResponse>() {
                    @NonNull
                    @Override
                    public CoroutineContext getContext() {
                        return Dispatchers.getIO();
                    }

                    @Override
                    @SuppressWarnings("all")
                    public void resumeWith(@NonNull Object result) {
                        Handler mainHandler = new Handler(Looper.getMainLooper());
                        if (result instanceof Result.Failure) {
                            Throwable error = ((Result.Failure) result).exception;
                            mainHandler.post(() -> callback.onFailure(error));
                        } else if (result instanceof GenerateContentResponse) {
                            String text = ((GenerateContentResponse) result).getText();
                            mainHandler.post(() -> callback.onSuccess(text));
                        } else {
                            mainHandler.post(() -> callback.onFailure(new Exception("Unknown result: " + result)));
                        }
                    }
                }
        );
    }

}
