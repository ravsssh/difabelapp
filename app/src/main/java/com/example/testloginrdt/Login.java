package com.example.testloginrdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginBtn,gotoRegister;
    boolean valid = true;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        getSupportActionBar().hide();
        if(firebaseUser!=null){
            Intent i=new Intent(Login.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.loginPassword);
        password = findViewById(R.id.loginPassword);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final Button gotoRegister = findViewById(R.id.gotoRegister);
        auth=FirebaseAuth.getInstance();

    loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email_text=email.getText().toString();
            String pass_text=password.getText().toString();

            if(TextUtils.isEmpty(email_text)||TextUtils.isEmpty(pass_text)){
                Toast.makeText(Login.this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show();
            }else{
                auth.signInWithEmailAndPassword(email_text,pass_text)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i=new Intent(Login.this,MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Login.this, "Giriş Başarısız!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        }
    });

    gotoRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(Login.this, Register.class));
        }
    });


    }
}
