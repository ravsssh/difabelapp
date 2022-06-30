package com.example.testloginrdt;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class Icontts extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    String deskripsi;
    String gambaricon;
    Button TTS, Back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconperitem);
        TTS = findViewById(R.id.texttospeach);
        Back = findViewById(R.id.backtomenu);

        gambaricon= getIntent().getStringExtra("GambarIcon");
        String deskripsi= getIntent().getStringExtra("Deskripsi");

        ImageView post_gambaricon=(ImageView)findViewById(R.id.mainiconimage);
        Picasso.get().load(gambaricon).into(post_gambaricon);

        TextView post_deskripsi=(TextView)findViewById(R.id.Deskripsipericon);
        post_deskripsi.setText(deskripsi);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    int ttsLang = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","The Language is not supported");
                    }
                    else{
                        Log.i("TTS","Language Supported");
                    }
                    Log.i("TTS","Initialization success");
                } else {
                    Toast.makeText(getApplicationContext(),"TTS Initialization failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        TTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data =  getIntent().getStringExtra("Deskripsi");
                Log.i("TTS","button click: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH,null);

                if (speechStatus == TextToSpeech.ERROR){
                    Log.e("TTS","Error in converting Text to Speech!");
                }
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}
