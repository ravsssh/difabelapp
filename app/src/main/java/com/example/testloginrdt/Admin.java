package com.example.testloginrdt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {
    Button nambahfoto;
    Button logout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final Button logout = findViewById(R.id.logout);
        Button nambahfoto = findViewById(R.id.nambahfoto);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Admin.this,Login.class));

            }
        });
        nambahfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this, Tts.class));
            }
        });
    }
}
