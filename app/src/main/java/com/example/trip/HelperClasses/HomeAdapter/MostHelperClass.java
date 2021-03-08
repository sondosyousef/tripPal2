package com.example.trip.HelperClasses.HomeAdapter;

import java.util.ArrayList;

public class MostHelperClass {

   public String image;
   public   String title;
   public String description;

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String nameCity;

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public MostHelperClass(String image, String title,String description)
    {
        this.image = image;
        this.title = title;
        this.description = description;

    }

    public MostHelperClass() {
    }

    public String getImage() { return image; }

    public String getTitle() {
        return title;
    }

    public String getDescription(){return description;}


}
