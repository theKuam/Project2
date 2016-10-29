package com.example.thengm.boodict;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Word> wordList = new ArrayList<Word>();
    SearchView searchView;
    ArrayAdapter<String> adapter;
    private DatabaseReference mData;
    private ListView listview;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // chỉnh fullscreen
        mData = FirebaseDatabase.getInstance().getReference();
        container = (LinearLayout) findViewById(R.id.container);// đây là layout dùng để hiển thị Listview cho thanh tìm kiếm searchView
        searchView = (SearchView) findViewById(R.id.searchView);// ô tìm kiếm
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                container.removeAllViews();
                return false;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.removeAllViews(); // làm trống vùng hiển thị
                // tạo liên kết inflater và trỏ đến List_searchview.xml
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = layoutInflater.inflate(R.layout.list_searchview, null);
                // đưa listView vào vùng hiển thị
                container.addView(addView);
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                listview = (ListView) addView.findViewById(R.id.lvData2);
                listview.setAdapter(adapter);
                Controller1Editted.querySearch(MainActivity.this, mData, searchView, list, wordList, adapter); // tìm kiếm
                // khi chọn 1 từ trên danh sách hiển thị
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent wordLayout = new Intent(getApplicationContext(), WordLayout.class);
                        Controller1Editted.goToWordLayout(wordLayout, parent, position, new Word(), wordList, new Bundle());
                        startActivity(wordLayout);
                    }
                });

            }

        });
    }
}
