package com.example.thengm.boodict;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mData;
    Button btNetwork;
    SearchView searchView;
    private ListView listview;
    ArrayAdapter<String> adapter;
    final ArrayList<String> list = new ArrayList<String>();
    final ArrayList<Word> wordList = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        //btNetwork = (Button) findViewById(R.id.network);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listview = (ListView) findViewById(R.id.lvData);

        listview.setAdapter(adapter);

       /* btNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Controller.isNetworkConneceted(MainActivity.this, MainActivity.this)==false){
                    Toast.makeText(MainActivity.this, "Disconnected", Toast.LENGTH_SHORT).show();
                };
            }
        });*/

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent wordLayout = new Intent(getApplicationContext(), WordLayout.class);
                Controller1Editted.goToWordLayout(wordLayout, parent, position, new Word(), wordList, new Bundle());
                startActivity(wordLayout);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        Controller1Editted.querySearch(menu, mData, searchView, list, wordList, adapter);

        return true;
    }
}
