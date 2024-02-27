package com.example.argentinajava;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView timerTextView;
    private Button startStopButton;
    private Button nextscreenButton;
    private Button clearButton;
    private Button decreaseButton;
    private Button increaseButton;
    private CountDownTimer countDownTimer;
    private boolean Running = false;
    private long timeLeftInMillis = 600000; // 10 minutes

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startStopButton = findViewById(R.id.startStopButton);
        clearButton = findViewById(R.id.clearButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        increaseButton = findViewById(R.id.increaseButton);
        nextscreenButton = findViewById(R.id.nextscreen);

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
        nextscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Calendar.class);
               startActivity(intent);
            }
        });
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseTime();
            }
        });
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseTime();
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
                timeLeftInMillis = 60000;
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
        stopTimer();
        //10 minutes
        timeLeftInMillis = 600000;
        updateTimerText();
    }
//incease and decrease buttons
    private void decreaseTime() {
        timeLeftInMillis -= 60000;
        if (timeLeftInMillis < 0) {
            timeLeftInMillis = 0;
        }
        updateTimerText();
    }

    private void increaseTime() {
        timeLeftInMillis += 60000; // Increase time by 1 minute (60000 milliseconds)
        updateTimerText();
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }
}
