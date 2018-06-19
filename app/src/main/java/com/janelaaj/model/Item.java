package com.janelaaj.model;

/**
 * Created On 24-05-2018
 *
 * @author Narayan Semwal
 */
public class Item {

    private String name;
    private int image;


    public Item(String name, int image) {
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
