package com.brick.codingtest.Solution5;

import java.util.Map;

public class ResponseResult {
    private int id;
    private String quote;

    public ResponseResult(int id, String quote) {
        this.id = id;
        this.quote = quote;
    }

    public int getId() {
        return id;
    }

    public String getQuote() {
        return quote;
    }
}
