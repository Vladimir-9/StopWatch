package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int iteration;
    private boolean beginning;
    private boolean wasBeginning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        if (savedInstanceState != null) {
            iteration = savedInstanceState.getInt("iteration");
            beginning = savedInstanceState.getBoolean("beginning");
            textView.setText(savedInstanceState.getString("textView"));
        }
        begin();
    }

    void begin() {
        final TextView textView = findViewById(R.id.text_view_stopwatch);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.valueOf(iteration));
                if (beginning) {
                    iteration++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view) {
        beginning = true;
        textView.setText(R.string.stopwatch_start);
    }

    public void onClickStop(View view) {
        beginning = false;
        textView.setText(R.string.stopwatch_stop);
    }

    public void onClickReset(View view) {
        textView.setText(R.string.stopwatch_reset);
        beginning = false;
        iteration = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("iteration", iteration);
        outState.putBoolean("beginning", beginning);
        outState.putString("textView", textView.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasBeginning = beginning;
        beginning = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasBeginning) {
            beginning = true;
        }
    }
}
