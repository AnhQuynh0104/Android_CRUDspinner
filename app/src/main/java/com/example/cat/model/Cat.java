package com.example.cat.model;

public class Cat {
    private int imageCat;
    private String name, description;
    private double price;

    public Cat(int imageCat, String name, String description, double price) {
        this.imageCat = imageCat;
        this.name = name;
        this.description = description;
        this.price = price;
    }



    public int getImageCat() {
        return imageCat;
    }

    public void setImageCat(int imageCat) {
        this.imageCat = imageCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
