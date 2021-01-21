package com.lokendrasingh.apicalljson;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int UserId, Id;
    String title;
    @SerializedName("body")
    private String text;

    public int getUserId() {
        return UserId;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
