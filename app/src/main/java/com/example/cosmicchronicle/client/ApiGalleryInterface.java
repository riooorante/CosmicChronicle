package com.example.cosmicchronicle.client;


import com.example.cosmicchronicle.model.RoverData;
import com.example.cosmicchronicle.planet.RoverDataListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiGalleryInterface {

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=10&api_key=LSssFTHXEhvwad2Tvq1fPO9jCC655FODIZNYc2Bo")
    Call<RoverDataListResponse> getAllPhoto();
}
