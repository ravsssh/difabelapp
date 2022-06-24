package com.example.testloginrdt;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Tts extends AppCompatActivity {
    EditText deskripsi, alamat;
    ImageView gambarlaporan;
    Button Lapor;
    Button btnbrowse;
    FirebaseAuth auth;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        getSupportActionBar().hide();

        final EditText deskripsi = findViewById(R.id.Deskripsi);
        final EditText alamat = findViewById(R.id.Alamat);
        final ImageView gambarlaporan = findViewById(R.id.postfoto);
        final Button Lapor = findViewById(R.id.postlapor);
        final Button btnbrowse = (Button)findViewById(R.id.carigambar);
        auth = FirebaseAuth.getInstance();


}
}

