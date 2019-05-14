package com.example.handlerapp;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button buttonStartThread;
private Handler mainHandler = new Handler();
private volatile boolean stopThread=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {
        stopThread=false;
        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();
       /* new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();*/
    }

    public void stopThread(View view) {
    stopThread=true;
    }
    class ExampleThread extends Thread{
      int seconds;
      ExampleThread(int seconds){
          this.seconds=seconds;
      }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Log.d("start thread: ", "startThread: startThread"+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class ExampleRunnable implements Runnable {
        int seconds;

        ExampleRunnable(int seconds) {
            this.seconds = seconds;
        }


        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                if(stopThread)
                    return;
                if (i == 5) {
            /*     Handler threadHandler = new Handler(Looper.getMainLooper());
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                buttonStartThread.setText("50%");
            }
        });
            buttonStartThread.post(new Runnable() {
                @Override
                public void run() {
                    buttonStartThread.setText("50%");
                }
            })
             }*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("50%");
                        }
                    });}
                    Log.d("start thread: ", "startThread: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

