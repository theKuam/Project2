package com.example.thengm.boodict;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btNetwork, btload;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        btNetwork = (Button) findViewById(R.id.network);
        btload = (Button) findViewById(R.id.loaddata);
        btload.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Controller2.loaddata("ENG-VIET", "eng-v.txt",mData,MainActivity.this);

                                      }
                                  }
        );
        btNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.isNetworkConneceted(MainActivity.this, MainActivity.this) == false) {
                    Toast.makeText(MainActivity.this, "Disconnected", Toast.LENGTH_SHORT).show();
                    mData.child("1").setValue("HELLO");
                }
                ;
            }
        });
        mData.child("1").setValue("HELLO");
    }
}
