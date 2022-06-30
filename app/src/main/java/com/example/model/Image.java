package com.example.model;

import java.io.Serializable;

public class Image {
    private String imageName;
    private String imageURL;

        public Image(String imageName, String imageURL ){
        this.imageName=imageName;
        this.imageURL=imageURL;
    }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Image()
    {

    }
}
