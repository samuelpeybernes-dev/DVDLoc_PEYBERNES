package com.example.dvdloc_peybernes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dao.C;

public class Login extends AppCompatActivity {
    EditText edt_Email;
    EditText edt_paswd;
    Button btn_ok;
    TextView txt_inscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Email = (EditText)findViewById(R.id.edt_email);
        edt_paswd = (EditText)findViewById(R.id.edt_pswd);
        btn_ok = (Button)findViewById(R.id.btn_ok_register);
        txt_inscrire = (TextView) findViewById(R.id.txt_inscrire);

        btn_ok.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view){
                String email = edt_Email.getText().toString();
                String paswd = edt_paswd.getText().toString();
                if(email.matches("") || paswd.matches("")){
                    Toast.makeText(getApplicationContext(), "Veuillez saisir les deux valeurs !", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginUser();
                }
            }
        });

        txt_inscrire.setOnClickListener(new TextView.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(){
        final String email = edt_Email.getText().toString().trim();
        final String pswd = edt_paswd.getText().toString().trim();

        StringRequest stringrequest = new StringRequest(Request.Method.POST,
                C.Login_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("Test connexion", response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(jsonObject.getString("error") == "false"){
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("titre", edt_Email.getText().toString());
                                startActivity(intent);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError erreur) {
                        Toast.makeText(getApplicationContext(), erreur.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                if (!email.equals("")&&!pswd.equals("")){
                    params.put("email", email);
                    params.put("password", pswd);
                }
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringrequest);
    }
}