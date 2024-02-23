package com.example.argentinajava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startStopButton;
    private Button clearButton;
    private CountDownTimer countDownTimer;
    private boolean Running = false;
    private long timeLeftInMillis = 600000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startStopButton = findViewById(R.id.startStopButton);
        clearButton = findViewById(R.id.clearButton);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Running) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateTimerText();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                Running = false;
                startStopButton.setText("Start");
                timeLeftInMillis = 60000; // Reset to 1 minute
                updateTimerText();
            }
        }.start();
        Running = true;
        startStopButton.setText("Paused");
    }

    private void stopTimer() {
        countDownTimer.cancel();
        Running = false;
        startStopButton.setText("Start");
    }

    private void resetTimer() {
        stopTimer(); // Stop the timer if running
        timeLeftInMillis = 600000; // Reset timer to 5 minutes
        updateTimerText();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }
}
