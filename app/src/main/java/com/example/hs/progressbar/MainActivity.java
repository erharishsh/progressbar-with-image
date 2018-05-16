package com.example.hs.progressbar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hs.progressbutton.ProgressImageView;


public class MainActivity extends AppCompatActivity {
ProgressImageView layout;
int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0)
                    layout.updateProgressState(ProgressImageView.ProgressState.PAUSED);
                if(i==1)
                    layout.updateProgressState(ProgressImageView.ProgressState.PROGRESS);
                i++;
            }
        });

        new AsyncTask<Void,Integer,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                for(int i=0;i<=100;i++) {

                    publishProgress(i);
                    try {
                        Thread.sleep(500);
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
