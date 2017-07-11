package com.example.rinks.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void RegisterButtonClick(View view) {

        Intent i = new Intent(this, Register.class);
        startActivity(i);
        finish();
    }

    public void LoginButtonClick(View view) {

        EditText editText1 = (EditText) findViewById(R.id.email);
        EditText editText2 = (EditText) findViewById(R.id.password);

        String email = editText1.getText().toString();
        String pass =  editText2.getText().toString();

        String oEmail = "admin";
        String oPass = "admin";

        if(email.equals(oEmail) && pass.equals(oPass)) {

            Intent intent = new Intent(this, Navigation.class);
            startActivity(intent);
            finish();
        } else {

            Toast toast = Toast.makeText(this, "Invalid Email Email or Password", Toast.LENGTH_LONG);
            toast.show();

        }


    }
}
