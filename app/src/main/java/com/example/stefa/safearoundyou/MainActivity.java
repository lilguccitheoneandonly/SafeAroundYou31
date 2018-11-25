package com.example.stefa.safearoundyou;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 31;
    private FirebaseAuth mAuth;
    Button signbtn, googlebtn, registerbtn;
    EditText edt1, edt2;
    String password, email;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signbtn = findViewById(R.id.button);
        edt1 = findViewById(R.id.editText);
        edt2 = findViewById(R.id.editText2);
        googlebtn = findViewById(R.id.button2);
        registerbtn = findViewById(R.id.button3);
        mAuth = FirebaseAuth.getInstance();
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterTab.class);
                startActivity(intent);
            }
        });
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt1.getText().toString();
                password = edt2.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                            startActivity(myIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }

                });
            }

//    void SignIn(final String username, final String password)
//    {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://itec-api.deventure.co/api/Token";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        Toast.makeText(MainActivity.this, response,
//                                            Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        })
//        {
//            @Override
//            public byte[] getBody() throws AuthFailureError {
////                return super.getBody();
//                return ("username="+username+"&password="+password+"&grant_type=password").getBytes();
//
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put("Content-Type", "application/x-www-form-urlencoded");
//                return map;
//            }
//        };
//
//        queue.add(stringRequest);
        });
    }
}

