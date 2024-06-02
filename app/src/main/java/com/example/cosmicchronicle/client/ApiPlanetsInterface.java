package com.example.cosmicchronicle.client;

import com.example.cosmicchronicle.planet.ServicesListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiPlanetsInterface {

    @GET("rest/bodies?filter%5B%5D=isPlanet%2Ceq%2Ctrue")
    Call<ServicesListResponse> getServiceList();

    @GET("rest/bodies")
    Call<ServicesListResponse> getSpecificSpaceObject(@Query("filter[]") String filter);

    @GET
    Call<ServicesListResponse> getMoons(@Url String url);
}
