package com.example.rinks.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2); }

       /* Thread myThread  = new Thread() {

            @Override

            public void run() {

                try {

                    sleep(1000);
                    Intent intent1 = new Intent(getApplicationContext(), Splash3.class);

                    intent1.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                    startActivity(intent1);

                    finish();
                }
                catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        };

        myThread.start();





    }
    } */

    public void nextbtn1Click(View view) {

        Intent intent = new Intent(this, Splash3.class);

        startActivity(intent);

        finish();
    }
}