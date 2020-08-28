package com.shivas.retrofitsample;

public class Post {
    private int userId;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "userId=" + userId + "\n" +
                "id=" + id + "\n" +
                "title='" + title + '\'' + "\n" +
                "body='" + body + '\'' +  "\n" + "\n" ;
    }

    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
