package com.codenamegradient.ribtimer;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer[] timers;
    private Boolean[] timerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timers = new CountDownTimer[4];
        timerStatus = new Boolean[4];

        createTimers();
    }

    private void createTimers() {
        timerStatus[0] = false;
        timerStatus[1] = false;
        timerStatus[2] = false;
        timerStatus[3] = false;

        timers[0] = new CountDownTimer(minutesToMilliseconds(20), 1000) {
            TextView textView = findViewById(R.id.display_20);

            @Override
            public void onTick(long l) {

                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                // todo: make noise
                textView.setText(getString(R.string.timer_finished));
            }
        };

        timers[1] = new CountDownTimer(minutesToMilliseconds(60), 1000) {
            @Override
            public void onTick(long l) {
                TextView textView = findViewById(R.id.display_60);
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                // todo: make noise
            }
        };

        timers[2] = new CountDownTimer(minutesToMilliseconds(120), 1000) {
            @Override
            public void onTick(long l) {
                TextView textView = findViewById(R.id.display_120);
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                // todo: make noise
            }
        };

        timers[3] = new CountDownTimer(10000, 1000) {
            TextView textView = findViewById(R.id.display_10s);

            @Override
            public void onTick(long l) {
                TextView textView = findViewById(R.id.display_10s);
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                // todo: make noise
                textView.setText(getString(R.string.timer_finished));
            }
        };
    }

    /**
     * Handles the button actions
     * @param view
     */
    public void toggleTimer(View view) {
        TextView textView;
        int buttonId = view.getId();
        Button button = findViewById(buttonId);

        switch (buttonId) {
            case R.id.toggle_20:
                if (timerStatus[0]) {
                    timerStatus[0] = false;
                    timers[0].cancel();

                    textView = findViewById(R.id.display_20);
                    textView.setText(getString(R.string.initial_20));

                    button.setBackgroundColor(getResources().getColor(R.color.button_start));
                    button.setText(getString(R.string.timer_start));

                } else {
                    timerStatus[0] = true;
                    timers[0].start();

                    button.setBackgroundColor(getResources().getColor(R.color.button_stop));
                    button.setText(getString(R.string.timer_stop));


                }
                break;

            case R.id.toggle_60:
                if (timerStatus[1]) {
                    timerStatus[1] = false;
                    timers[1].cancel();

                    textView = findViewById(R.id.display_60);
                    textView.setText(getString(R.string.initial_60));

                    button.setBackgroundColor(getResources().getColor(R.color.button_start));
                    button.setText(getString(R.string.timer_start));

                } else {
                    timerStatus[1] = true;
                    timers[1].start();

                    button.setBackgroundColor(getResources().getColor(R.color.button_stop));
                    button.setText(getString(R.string.timer_stop));
                }
                break;

            case R.id.toggle_120:
                if (timerStatus[2]) {
                    timerStatus[2] = false;
                    timers[2].cancel();

                    textView = findViewById(R.id.display_120);
                    textView.setText(getString(R.string.initial_120));

                    button.setBackgroundColor(getResources().getColor(R.color.button_start));
                    button.setText(getString(R.string.timer_start));

                } else {
                    timerStatus[2] = true;
                    timers[2].start();

                    button.setBackgroundColor(getResources().getColor(R.color.button_stop));
                    button.setText(getString(R.string.timer_stop));
                }
                break;

            case R.id.toggle_10s:
                if (timerStatus[3]) {
                    timerStatus[3] = false;
                    timers[3].cancel();

                    textView = findViewById(R.id.display_10s);
                    textView.setText(getString(R.string.initial_10s));

                    button.setBackgroundColor(getResources().getColor(R.color.button_start));
                    button.setText(getString(R.string.timer_start));

                } else {
                    timerStatus[3] = true;
                    timers[3].start();

                    button.setBackgroundColor(getResources().getColor(R.color.button_stop));
                    button.setText(getString(R.string.timer_stop));
                }
                break;

        }
    }

    /**
     * Converts minutes to milliseconds
     * @param minutes
     * @return long milliseconds
     */
    private long minutesToMilliseconds(int minutes) {
        return minutes * 60000;
    }

    /**
     * Converts milliseconds to a human readable string
     * @param milliseconds
     * @return string string
     */
    private String millisecondsToString(long milliseconds) {
        String result = "";

        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;

        if (hours > 0) {
            result = hours + "h ";
        }
        if (minutes > 0) {
            result = result + minutes + "m ";
        }
        if (seconds > 0) {
            result = result + seconds + "s ";
        }

        return result;
    }
}
