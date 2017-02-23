package com.example.venkatnag.lab4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
public void signup(View v){
    Intent redirect = new Intent(LoginActivity.this, RegisterActivity.class);
    startActivity(redirect);
}
    public  void signin(View V){
        AutoCompleteTextView a=(AutoCompleteTextView) findViewById(R.id.email);
        EditText b=(EditText) findViewById(R.id.password);
        Button s=(Button) findViewById(R.id.email_sign_in_button);
        a.setError(null);
        b.setError(null);
        s.setError(null);
        String str=a.getText().toString();
        String xyz=b.getText().toString();
        SharedPreferences settings = getSharedPreferences("MyPrefsFile", 0);
        String x=settings.getString("email",null);
        String y=settings.getString("password",null);
        if(str.isEmpty()&xyz.isEmpty())
        {
        a.setError("Email is required");
        b.setError("password is required");}
        else if(str.isEmpty())
            a.setError("Email is required");
        else if(xyz.isEmpty())
            b.setError("Password is required");
        else {
            if (str.equals(x) & xyz.equals(y)) {
                Intent redirect = new Intent(LoginActivity.this, ApiActivity.class);
                startActivity(redirect);
            } else {
                a.setError("Email or password mismatch");
                b.setError("Email or password mismatch");
            }
        }
    }
}

