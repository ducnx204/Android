package com.example.assignment_android_pd05266;

import android.graphics.Bitmap;

public class Item {
    Bitmap image;
    String title;

    public Item(Bitmap image, String title) {
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
