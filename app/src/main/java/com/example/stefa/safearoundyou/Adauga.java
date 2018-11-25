package com.example.stefa.safearoundyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.android.volley.VolleyLog.TAG;

public class Adauga extends Activity {

    EditText Nume,Descriere;
    Button Creeaza;
    FirebaseAuth mAuth;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("pinuri");
    DatabaseReference count = database.getReference("count");
    int g;
    Double x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaugawindow);
        Nume = findViewById(R.id.Nume_Locatie);
        mAuth = FirebaseAuth.getInstance();
        Descriere = findViewById(R.id.Descriere_Locatie);
        Creeaza=findViewById(R.id.Creeaza_Marker);
        Intent intent = getIntent();
        Creeaza.setText(count.getPath().toString());
        x= intent.getDoubleExtra("lat", 0);
        y=intent.getDoubleExtra("long",0);

        Creeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue().toString();
                        Log.d(TAG, "Value is: " + value);

                        g=Integer.parseInt(value);
                        Creeaza.setText(""+value);
                        g++;
                        myRef.child(""+g).child("name").setValue(Nume.getText().toString());
                        myRef.child(""+g).child("descriere").setValue(Descriere.getText().toString());
                        myRef.child(""+g).child("x").setValue(x.toString());
                        myRef.child(""+g).child("y").setValue(y.toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                //count.setValue(g);
            }

        });
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.7),(int)(height*.7));

    }
}
