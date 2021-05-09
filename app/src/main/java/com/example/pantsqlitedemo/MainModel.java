package com.example.jeansshoppingcart.Model;

public class MainModel {
    int image, size;
    String name, price, color;

    public MainModel(int image, int size, String name, String price, String color) {
        this.image = image;
        this.size = size;
        this.name = name;
        this.price = price;
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

