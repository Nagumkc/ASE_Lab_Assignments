package com.example.user.videoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    void ve(View v)
    {
        Intent in=new Intent(HomeActivity.this,MainActivity.class);
        startActivity(in);
    }
}
