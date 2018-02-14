package com.codenamegradient.ribtimer;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer[] timers;
    private Boolean[] timerStatus;

    private Uri alarmUri;
    private Ringtone alarmRingtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timers = new CountDownTimer[4];
        timerStatus = new Boolean[4];

        createTimers();

        alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        alarmRingtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
    }

    /**
     * Instantiates all the timers
     */
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
                alarmRingtone.play();
                textView.setText(getString(R.string.timer_finished));
            }
        };

        timers[1] = new CountDownTimer(minutesToMilliseconds(60), 1000) {
            TextView textView = findViewById(R.id.display_60);

            @Override
            public void onTick(long l) {
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                alarmRingtone.play();
                textView.setText(getString(R.string.timer_finished));
            }
        };

        timers[2] = new CountDownTimer(minutesToMilliseconds(120), 1000) {
            TextView textView = findViewById(R.id.display_120);

            @Override
            public void onTick(long l) {
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                alarmRingtone.play();
                textView.setText(getString(R.string.timer_finished));
            }
        };

        timers[3] = new CountDownTimer(minutesToMilliseconds(180), 1000) {
            TextView textView = findViewById(R.id.display_180);

            @Override
            public void onTick(long l) {
                textView.setText(millisecondsToString(l));
            }

            @Override
            public void onFinish() {
                alarmRingtone.play();
                textView.setText(getString(R.string.timer_finished));
            }
        };


    }

    /**
     * Toggles the alarm on/off when the test button is tapped
     *
     * @param view
     */
    public void toggleTest(View view) {
        Button button = findViewById(R.id.toggle_test);

        if (alarmRingtone.isPlaying()) {
            button.setText(getString(R.string.timer_start));
            button.setBackgroundColor(getResources().getColor(R.color.button_start));
            alarmRingtone.stop();
        } else {
            button.setText(getString(R.string.timer_stop));
            button.setBackgroundColor(getResources().getColor(R.color.button_stop));
            alarmRingtone.play();
        }

    }

    /**
     * Handles the button actions
     *
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

                    alarmRingtone.stop();
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

                    alarmRingtone.stop();
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

                    alarmRingtone.stop();
                } else {
                    timerStatus[2] = true;
                    timers[2].start();

                    button.setBackgroundColor(getResources().getColor(R.color.button_stop));
                    button.setText(getString(R.string.timer_stop));
                }
                break;

            case R.id.toggle_180:
                if (timerStatus[3]) {
                    timerStatus[3] = false;
                    timers[3].cancel();

                    textView = findViewById(R.id.display_180);
                    textView.setText(getString(R.string.initial_180));

                    button.setBackgroundColor(getResources().getColor(R.color.button_start));
                    button.setText(getString(R.string.timer_start));

                    alarmRingtone.stop();
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
     *
     * @param minutes
     * @return long milliseconds
     */
    private long minutesToMilliseconds(int minutes) {
        return minutes * 60000;
    }

    /**
     * Converts milliseconds to a human readable string
     *
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
