package com.example.cosmicchronicle.planet;

import com.example.cosmicchronicle.model.SpaceServiceList;

import java.util.ArrayList;

public class ServicesListResponse {
    private ArrayList<SpaceServiceList> bodies;

    public ArrayList<SpaceServiceList> getData() {
        return bodies;
    }

    public void setData(ArrayList<SpaceServiceList> bodies) {
        this.bodies = bodies;
    }
}
