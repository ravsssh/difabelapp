package com.example.testloginrdt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Image;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    private RecyclerView iconlist;
    private DatabaseReference mdatabase;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdatabase = FirebaseDatabase.getInstance("https://testloginrdt-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Images");
        mdatabase.keepSynced(true);
        FirebaseRecyclerOptions<Image> options =
                new FirebaseRecyclerOptions.Builder<Image>()
                        .setQuery(mdatabase, Image.class)
                        .build();
        mdatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {


            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult()));
                }
            }
        });
        final Button logout = findViewById(R.id.logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));

            }
        });


        iconlist =(RecyclerView)findViewById(R.id.rycleview);
        //iconlist.setHasFixedSize(true);
        iconlist.setLayoutManager(new LinearLayoutManager(this));
        firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Image,iconholder>
                (options) {

            @NonNull
            @Override
            public iconholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cyle,parent,false);
               return new iconholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull iconholder holder, int position, @NonNull Image model) {
                holder.setImageURL(getApplication(),model.getImageURL());

                holder.setImageName(model.getImageName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), Icontts.class);

                        intent.putExtra("GambarIcon",model.getImageURL());
                        intent.putExtra("Deskripsi",model.getImageName());


                        //Toast.makeText(v.getContext(),model.getLongtitude().toString(),Toast.LENGTH_LONG).show();
                        v.getContext().startActivity(intent);

                    }
                });


            }







        };
        iconlist.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();

    }
    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
    class iconholder extends RecyclerView.ViewHolder{
        View mView;
        public iconholder(View itemView)
        {
            super(itemView);
            mView=itemView;

        }


        public void setImageURL(Application ctx,String imageURL){
            ImageView post_imageurl=(ImageView) mView.findViewById(R.id.mainimage);
            Picasso.get().load(imageURL).into(post_imageurl);
        }

        public  void setImageName(String imageName){
            TextView post_ImageName=(TextView) mView.findViewById(R.id.Deskripsiicon);
            post_ImageName.setText((imageName));
        }
    }

}
