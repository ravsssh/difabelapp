package com.example.testloginrdt;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText fullName, email, password, phone;
    Button registerBtn;
    Button goToLogin;
    FirebaseAuth auth;
    DatabaseReference myRef;


       @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        final EditText fullName = findViewById(R.id.registerName);
        final EditText email = findViewById(R.id.registerEmail);
        final EditText password = findViewById(R.id.registerPassword);
        final EditText  phone = findViewById(R.id.registerPhone);
        final Button registerBtn = findViewById(R.id.registerBtn);
        final Button  goToLogin = findViewById(R.id.gotoLogin);
        auth=FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String fullNametext = fullName.getText().toString();
                 String emailtext = email.getText().toString();
                 String passwordtext = password.getText().toString();
                 String phonetext = phone.getText().toString();
                if (fullNametext.isEmpty() ||emailtext.isEmpty() || passwordtext.isEmpty() || phonetext.isEmpty() ){
                    Toast.makeText(Register.this,"Masukan isi form",Toast.LENGTH_SHORT).show();

                }
                else{
                    RegisterNow(fullNametext,emailtext,passwordtext,phonetext);
                }
            }
        });
goToLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        finish();
    }
});

    }
    private void RegisterNow(final String username,String email,String password,String phone){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            String userid=firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance("https://testloginrdt-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("MyUsers")
                                    .child(userid);

                            HashMap<String, String> hashMap=new HashMap<>();
                            hashMap.put("phone",phone);
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("imageURL","default");


                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent i=new Intent(Register.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });


                        }else{
                            Toast.makeText(Register.this, "Gagal Registrasi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}