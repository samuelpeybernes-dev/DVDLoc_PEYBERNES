package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText edt_Email;
    EditText edt_pswd;
    Button btn_ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Email =(EditText) findViewById(R.id.edt_Email);
        edt_pswd =(EditText) findViewById(R.id.edt_pswd);
        btn_ok = (Button)findViewById(R.id.btn_ok_login);
    }
}