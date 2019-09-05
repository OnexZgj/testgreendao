package com.inspur.greendao.circleview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.inspur.greendao.CircleClockView;
import com.inspur.greendao.R;

import java.util.Timer;
import java.util.TimerTask;

public class CircleViewActivity extends AppCompatActivity {

    private CircleClockView view;

    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_view);

        view = findViewById(R.id.ccv_view);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            view.doInvalidate();
                            Log.d(TAG, "run: 调用了");
                        }
                    });
                }
            }
        }).start();
    }
}
