package com.example.cosmicchronicle.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.adapter.RVRoverGalleryAdapter;
import com.example.cosmicchronicle.client.ApiGalleryInterface;
import com.example.cosmicchronicle.client.GalleryDataClient;
import com.example.cosmicchronicle.model.RoverData;
import com.example.cosmicchronicle.planet.RoverDataListResponse;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {
    private static final String TAG = "GalleryFragment";

    Button buttonReload;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    RVRoverGalleryAdapter rvRoverGallery;
    ArrayList<RoverData> roverData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        buttonReload = view.findViewById(R.id.reloadButton);
        progressBar = view.findViewById(R.id.galleryProgress);
        recyclerView = view.findViewById(R.id.roverRV);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        roverData = new ArrayList<>();
        rvRoverGallery = new RVRoverGalleryAdapter(getContext(), roverData);
        recyclerView.setAdapter(rvRoverGallery);

        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateServices();
            }
        });

        populateServices();
        return view;
    }

    public void populateServices() {
        progressBar.setVisibility(View.VISIBLE);
        buttonReload.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        ApiGalleryInterface apiService = GalleryDataClient.getClient().create(ApiGalleryInterface.class);
        Call<RoverDataListResponse> call = apiService.getAllPhoto();
        call.enqueue(new Callback<RoverDataListResponse>() {
            @Override
            public void onResponse(Call<RoverDataListResponse> call, Response<RoverDataListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RoverDataListResponse roverDataListResponse = response.body();
                    ArrayList<RoverData> photoList = roverDataListResponse.getPhotos();
                    Log.e(TAG, "photo " + photoList);

                    if (photoList != null && !photoList.isEmpty()) {
                        if (isAdded()) {
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    roverData.clear();
                                    roverData.addAll(photoList);
                                    rvRoverGallery.notifyDataSetChanged();
                                    Log.e(TAG, "Data updated in RecyclerView: " + roverData.size() + " items");
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    } else {
                        if (isAdded()) {
                            Log.e(TAG, "No data found in API response");
                            progressBar.setVisibility(View.GONE);
                            buttonReload.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (isAdded()) {
                        Log.e(TAG, "Response error: " + response.message());
                        progressBar.setVisibility(View.GONE);
                        buttonReload.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Response Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RoverDataListResponse> call, Throwable t) {
                if (isAdded()) {
                    Log.e(TAG, "Connection error", t);
                    progressBar.setVisibility(View.GONE);
                    buttonReload.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Connection Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
