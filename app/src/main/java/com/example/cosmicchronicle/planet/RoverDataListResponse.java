package com.example.cosmicchronicle.planet;

import com.example.cosmicchronicle.model.RoverData;
import java.util.ArrayList;

public class RoverDataListResponse {
    private ArrayList<RoverData> photos;

    public ArrayList<RoverData> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<RoverData> photos) {
        this.photos = photos;
    }
}
