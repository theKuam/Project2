package com.example.thengm.boodict;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.StringTokenizer;

/**
 * Created by hoang on 28/10/2016.
 */

public class Controller2 {
    public static void newtextview(Activity activity, String xau, int type) {
        LinearLayout container;
        container = (LinearLayout) activity.findViewById(R.id.container_wordlayout); //
        LayoutInflater layoutInflater = (LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView = layoutInflater.inflate(R.layout.w_word, null); // tạo 1 textview mới trong vùng container_wordLayout

        switch (type) {
            case 1: {
                addView = layoutInflater.inflate(R.layout.w_word, null);
                // Định dạng textview hiển thị keyword
                break;
            }
            case 2: {
                addView = layoutInflater.inflate(R.layout.w_noun, null);
                // Định dạng textview hiển thị noun
                break;
            }
            case 3: {
                addView = layoutInflater.inflate(R.layout.w_type, null);
                // Định dạng textview hiển thị loại từ
                break;
            }
            case 4: {
                addView = layoutInflater.inflate(R.layout.w_means, null);
                // Định dạng textview hiển thị nghĩa
                break;
            }
            case 5: {
                addView = layoutInflater.inflate(R.layout.w_eg, null);
                // Định dạng textview hiển thị ví dụ
                break;
            }
            case 6: {
                addView = layoutInflater.inflate(R.layout.w_traneg, null);
                // Định dạng textview hiển thị dịch nghĩa ví dụ
                break;
            }

        }
        //final View addView = layoutInflater.inflate(R.layout.w_word, null);
        TextView textOut = (TextView) addView.findViewById(R.id.textout);
        textOut.setText(xau);
        container.addView(addView);
    }

    // Hàm hiền thị nghĩa của từ lên màn hình
    public static void showWord(String s1, Activity activity) {
        StringTokenizer st = new StringTokenizer(s1, "#");
        int dem = 1;
        if (st.countTokens() == 3) {
            while (st.hasMoreTokens()) {
                String xau = st.nextToken();

                if (dem == 2) {
                    newtextview(activity, xau, 2);
                    dem++;
                } else if (dem == 3) {
                    getMeans(xau, activity);
                } else if (dem == 1) {
                    dem++;
                    newtextview(activity, xau, 1);
                }
            }
        } else {
            newtextview(activity, "đây là katana", 2);
        }
    }

    // Hàm hiển thị nghĩa của từ lên màn hình
    public static void getMeans(String s, Activity activity) {
        StringTokenizer st;
        st = new StringTokenizer(s, "|");
        while (st.hasMoreTokens()) {
            String xau = st.nextToken();
            if (xau.contains("*")) {
                newtextview(activity, xau, 3);
            } else if (xau.contains("-")) {
                newtextview(activity, xau, 4);
            } else if (xau.contains("=")) {
                newtextview(activity, xau, 5);
            } else if (xau.contains("+")) {
                newtextview(activity, xau, 6);
            }
        }
    }


}
