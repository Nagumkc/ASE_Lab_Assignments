package com.example.venkatnag.lab4;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ApiActivity extends AppCompatActivity {

    EditText text;
    TextView des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        des=(TextView)findViewById(R.id.textView3);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void call(View v)
    {
      text=(EditText) findViewById(R.id.editText4);
        String s=text.getText().toString();
        String z=s.replace(" ","+");
        String getURL="https://www.googleapis.com/books/v1/volumes?q="+z+"&key=AIzaSyCaWJAU_lKTa8aKCHHROYKbjcDy44Z-Gv0";
         String response=null;
        BufferedReader bfr = null;
        try {
            URL url = new URL(getURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

           bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = bfr.readLine()) != null) {
                // Append server response in string
                sb.append(line + " ");
            }
            response=sb.toString();
        }
        catch(Exception ex) {
           String Error = ex.getMessage();
        }
        finally
        {
            try
            {
            bfr.close();}

            catch(Exception ex) {

        }
        }            int i;
                     JSONObject jsonResult;
                    try {
                        des.setText(null);
                        jsonResult = new JSONObject(response);
                        String re = jsonResult.getString("totalItems");
                       JSONArray convertedTextArray = jsonResult.getJSONArray("items");
                        for(i=0;i<convertedTextArray.length();i++) {
                            JSONObject json=convertedTextArray.getJSONObject(i);
                            JSONObject t=json.getJSONObject("volumeInfo");
                            JSONArray a=t.getJSONArray("authors");
                            des.setText(des.getText().toString()+"\n"+t.getString("title")+" by "+a.get(0).toString()+"\n");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

        }
    }


