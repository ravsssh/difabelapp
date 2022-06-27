package com.example.model;

public class Post {

    public String imageName;
    public String imageURL;
    public Post(){

    }

    public Post(String name, String url) {
        this.imageName = name;
        this.imageURL = url;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
}
