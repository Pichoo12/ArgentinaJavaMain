package com.example.argentinajava;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startStopButton;
    private boolean isTimerRunning = false;
    private int seconds = 0;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;

            String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
            timerTextView.setText(time);

            if (isTimerRunning) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startStopButton = findViewById(R.id.startStopButton);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        isTimerRunning = true;
        startStopButton.setText("Stop");
        handler.post(runnable);
    }

    private void stopTimer() {
        isTimerRunning = false;
        startStopButton.setText("Start");
        handler.removeCallbacks(runnable);
    }
}
