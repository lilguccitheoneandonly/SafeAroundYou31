package com.example.stefa.safearoundyou;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterTab extends AppCompatActivity {
    EditText useredt,emailedt,passwordedt;
    Button btn;
    private static final String TAG = "RegisterTab";
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tab);
        useredt = findViewById(R.id.Username);
        mAuth = FirebaseAuth.getInstance();
        emailedt = findViewById(R.id.Email);
        passwordedt= findViewById(R.id.Password);
        btn = findViewById(R.id.Inregistreaza);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,username;
                email=emailedt.getText().toString();
                password=passwordedt.getText().toString();
                username=useredt.getText().toString();
//                SignIn(email,password
                FirebaseDatabase firebaseDataBase;
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterTab.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    if (user) {
//                                        firebaseDataBase.ref('users/' + user.uid).set({
//                                                email: user.email,
//                                                uid : user.uid,
//                                                username: username
//    });
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterTab.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });
    }
    private void updateUI(FirebaseUser user)
    {

    }
}
