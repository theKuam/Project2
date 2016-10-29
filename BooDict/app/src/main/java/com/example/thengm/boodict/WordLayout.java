package com.example.thengm.boodict;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by TheNGM on 25/10/2016.
 */

public class WordLayout extends AppCompatActivity {
    TextView keyword, ref;
    ScrollView scroll;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // chỉnh fullscreen
        Intent callerIntent = getIntent();
        Bundle bundle = callerIntent.getBundleExtra("MyPackage");
        Word word = new Word();
        word.setKeyword(bundle.getString("word"));
        Controller2.showWord(word.keyword, WordLayout.this);
    }
}
