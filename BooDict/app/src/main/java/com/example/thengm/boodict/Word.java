package com.example.thengm.boodict;

/**
 * Created by TheNGM on 25/10/2016.
 */

public class Word {
    public String keyword;
    public String mean;

    public Word() {

    }

    public Word(String keyword, String mean) {
        this.keyword = keyword;
        this.mean = mean;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
