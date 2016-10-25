package com.example.thengm.boodict;

import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hoang on 25/10/2016.
 */

public class Controller2 extends MainActivity {
    public static void loaddata(String dictionaryName, String ip, DatabaseReference mDatabase, Activity activity) {
        mDatabase.child(dictionaryName).removeValue();

        File sdcard = Environment.getExternalStorageDirectory();
        //Get the text file
        File directory = new File("storage/6367-4624/MyFiles");
        File file = new File(directory, "/"+ip);
        Map<String, Word> mymap = new HashMap<String, Word>();

        //Read text from file
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            int dem = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    StringTokenizer s;
                    s = new StringTokenizer(line, "#");
                    Word tu = new Word();
                    String k;
                    if (s.hasMoreTokens()) {
                        k = s.nextToken().toString();
                        if ((k.contains("."))
                                || (k.contains("$"))
                                || (k.contains("["))
                                || (k.contains("]"))
                                || (k.contains("#"))
                                || (k.contains("/"))
                                ) continue ;
                        else {
                            dem++;
                            tu.setKeyword(k);
                            tu.setMean(line);
                            mymap.put(tu.getKeyword(), tu);
                        }
                    }
                    mDatabase.child(dictionaryName +"/"+ tu.getKeyword()).setValue(tu);


                }
                br.close();
                //mDatabase.child(dictionaryName).setValue(mymap);
            } catch (IOException e) {
                //You'll need to add proper error handling here
                Toast.makeText(activity.getBaseContext(), e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
            System.out.print(String.valueOf(dem));
        } else System.out.print("no sdcard");

    }

}
