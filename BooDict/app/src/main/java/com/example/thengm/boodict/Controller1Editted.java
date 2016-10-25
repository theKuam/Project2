package com.example.thengm.boodict;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by TheNGM on 25/10/2016.
 */

public class Controller {
    //Kiem tra ket noi
    public static boolean isNetworkConneceted(Context ct, Activity activity){
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(ct.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    //Chuan hoa xau
    public static String Normalization(String word){
        word = word.trim();
        return word.toLowerCase();
    }


    public static void querySearch(Menu menu, final DatabaseReference mData, SearchView searchView, final ArrayList<String> list, final ArrayList<Word> wordList, final ArrayAdapter<String> adapter) {
        final int[] index = {0};
        searchView = (SearchView) menu.findItem(R.id.searchBar).getActionView();
        searchView.setQueryHint("Tra từ...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                index[0] = 0;
                newText = Normalization(newText);
                list.clear();
                wordList.clear();
                if (TextUtils.isEmpty(newText)) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }

                else {
                    final String finalNewText = newText;
                    mData.child("FRANCE-VIET").orderByChild("keyword").startAt(newText).limitToFirst(60).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if (dataSnapshot.child("keyword").getValue().toString().contains(finalNewText)) {
                                index[0]++;
                                list.add(dataSnapshot.child("keyword").getValue().toString());
                                wordList.add(new Word(dataSnapshot.child("keyword").getValue().toString(), dataSnapshot.child("mean").getValue().toString()));
                                adapter.notifyDataSetChanged();
                            }

                            if (index[0] == 0) {
                                list.clear();
                                wordList.clear();
                                adapter.notifyDataSetChanged();
                            }

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

                return true;
            }


        });
    }

    public static void goToWordLayout(Intent intent, AdapterView<?> parent, int position, Word word, ArrayList<Word> wordList, Bundle bundle) {
        int k = 0;
        while(k < wordList.size()){
            if(wordList.get(k).keyword.equals(parent.getAdapter().getItem(position).toString())){
                break;
            }
            k++;
        }

        word = wordList.get(k);
        bundle.putString("word", word.getKeyword());
        intent.putExtra("MyPackage", bundle);
    }
}
