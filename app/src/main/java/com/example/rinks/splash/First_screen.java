package com.example.rinks.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class First_screen extends AppCompatActivity {

    Button userlocation;
    Button nextActivity;

    private LocationManager locationManager;
    private LocationListener listener;
    private Double myLat,myLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first_screen);

        userlocation = (Button) findViewById(R.id.locate);
        nextActivity = (Button) findViewById(R.id.select);

        String[] donationtypedata = { "Educational","Cloths","Technology","Financial","Lifestyle","Furniture" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, donationtypedata);
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.donationtype);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                myLat = location.getLatitude();
                myLng = location.getLongitude();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        userlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 200, 0, listener);
                String sLat = String.valueOf(myLat);
                String sLng = String.valueOf(myLng);
                Toast.makeText(First_screen.this, "Latitude :"+sLat+"  Longitude : "+sLng, Toast.LENGTH_SHORT).show();
            }
        });

        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(First_screen.this,"Its Working",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(First_screen.this,MapsActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

}
