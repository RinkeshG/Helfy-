package com.example.rinks.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void DonateClick(View view) {

        Intent intent = new Intent(Navigation.this, Donate.class);
        startActivity(intent);
        finish();
    }
}
