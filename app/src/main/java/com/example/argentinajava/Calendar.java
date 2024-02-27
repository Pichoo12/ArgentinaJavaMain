package com.example.argentinajava;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity {
    private TextView currentDateTimeTextView;
    private Handler handler = new Handler();
    private Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            updateDateTime();
            handler.postDelayed(this, 1000); // Update every second
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        currentDateTimeTextView = findViewById(R.id.currentDateTimeTextView);
        handler.post(updateTimeRunnable);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop updating time and date when activity is destroyed
        handler.removeCallbacks(updateTimeRunnable);
    }

    private void updateDateTime() {
        // Get current time and date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dateTimeString = sdf.format(new Date());

        // Set the current time and date to the TextView
        currentDateTimeTextView.setText(dateTimeString);
    }
}
