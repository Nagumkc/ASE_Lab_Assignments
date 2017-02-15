package com.example.venkatnag.lab4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private EditText retype;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.editText6);
        pass = (EditText) findViewById(R.id.editText2);
        retype = (EditText) findViewById(R.id.editText3);
        name=(EditText)findViewById(R.id.editText);
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
                        Intent redirect = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(redirect);
                    }
                    else {
                        pass.setError("Password Mismatch");
                        retype.setError("Password Mismatch");
                    }
                    }
                }


        });
    }
}
