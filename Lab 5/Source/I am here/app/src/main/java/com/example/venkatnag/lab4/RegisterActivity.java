package com.example.venkatnag.lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.location.Address;
import java.util.ArrayList;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private EditText retype;
    private EditText name;
    private EditText loc;
    int TAKE_PHOTO_CODE = 0;
    int PICK_IMAGE=1;
    LocationManager locationManager;
    ImageView userImage ;
    Geocoder g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        locationManager = (LocationManager)this. getSystemService(Context.LOCATION_SERVICE);
        g=new Geocoder(this);

        LocationListener userCurrentLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {
                    double x = location.getLatitude();

                double y = location.getLongitude();
                    List<Address> addresses = g.getFromLocation(x, y, 1);
                    loc=(EditText) findViewById(R.id.editText7);
loc.setText(addresses.get(0).getAddressLine(0));
            }catch (Exception e)
                {

                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LatLng userCurrentLocationCorodinates = null;
        double latitute = 0, longitude = 0;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat
                .checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //show message or ask permissions from the user.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,userCurrentLocationListener);
        email = (EditText) findViewById(R.id.editText6);
        pass = (EditText) findViewById(R.id.editText2);
        retype = (EditText) findViewById(R.id.editText3);
        name=(EditText)findViewById(R.id.editText);
        userImage=(ImageView) findViewById(R.id.imageView);
        Button photo=(Button) findViewById(R.id.button3);
        final Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email.setError(null);
                pass.setError(null);
                retype.setError(null);
                String e=email.getText().toString();
                String p=pass.getText().toString();
                String re=retype.getText().toString();
                String n=name.getText().toString();
                if(e.isEmpty()&p.isEmpty()&re.isEmpty())
                {
                    email.setError("Email is required");
                    pass.setError("Password is required");
                    retype.setError("Password is required");
                }
                else if(e.isEmpty()&p.isEmpty())
                {
                    email.setError("Email is required");
                    pass.setError("Password is required");
                }
                else if(e.isEmpty()&re.isEmpty())
                {
                    email.setError("Email is required");
                    retype.setError("Password is required");
                }
                else if(e.isEmpty())
                {
                    email.setError("Email is required");
                }
                else if(p.isEmpty()){
                    pass.setError("Password is required");
                }
                else if(re.isEmpty())
                {
                    retype.setError("Password is required");
                }
                else {
                    if(p.equals(re)) {
                        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("email", e);
                        editor.putString("username", n);
                        editor.putString("password", p);
                        editor.putString("retype", re);
                        editor.commit();
                        Intent redirect = new Intent(RegisterActivity.this,MapsActivity.class);
                        startActivity(redirect);
                    }
                    else {
                        pass.setError("Password Mismatch");
                        retype.setError("Password Mismatch");
                    }
                    }
                }


        });
        photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"),PICK_IMAGE);
                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            userImage.setImageBitmap(photo);
            Log.d("CameraDemo", "Pic saved");
        }

        if(requestCode==PICK_IMAGE&&resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            String s=uri.toString();
            SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("photo", s);
            editor.commit();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                userImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
