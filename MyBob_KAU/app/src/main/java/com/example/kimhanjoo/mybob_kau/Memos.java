package com.example.kimhanjoo.mybob_kau;

/**
 * Created by kimhanjoo on 17. 1. 14.
 */

public class Memos {
    private String times;
    private String title;
    private String memo;

    public Memos(String times, String title, String memo) {
        this.times = times;
        this.title = title;
        this.memo = memo;
    }

    public String getTimes() {
        return this.times;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMemo() {
        return this.memo;
    }
}
