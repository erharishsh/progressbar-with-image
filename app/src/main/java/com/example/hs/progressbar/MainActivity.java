package com.example.hs.progressbar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hs.imageprogressbar.ProgressImageView;


public class MainActivity extends AppCompatActivity {
ProgressImageView layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.layout);

        new AsyncTask<Void,Integer,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                for(int i=0;i<=100;i++) {

                    publishProgress(i);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                layout.setProgress(values[0]);
            }
        }.execute();
    }
}
