package com.example.plexushospital;

public class ServicesModel {
    String name ;
    int image ;

    public ServicesModel(String name, int image) {
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
