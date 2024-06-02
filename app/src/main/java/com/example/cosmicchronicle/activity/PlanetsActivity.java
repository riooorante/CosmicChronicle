package com.example.cosmicchronicle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.adapter.RVPlanetAdapter;
import com.example.cosmicchronicle.client.ApiPlanetsClient;
import com.example.cosmicchronicle.client.ApiPlanetsInterface;
import com.example.cosmicchronicle.model.SpaceServiceList;
import com.example.cosmicchronicle.planet.ServicesListResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanetsActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView_planets;
    RVPlanetAdapter rvPlanetAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;

    ArrayList<SpaceServiceList> serviceLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planets);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.planetLoading);
        swipeRefreshLayout = findViewById(R.id.swipeLoadPlanets);
        serviceLists =  new ArrayList<>();
        recyclerView_planets = findViewById(R.id.rv_planetsList);
        recyclerView_planets.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvPlanetAdapter = new RVPlanetAdapter(PlanetsActivity.this, serviceLists);
        recyclerView_planets.setAdapter(rvPlanetAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        populateServices(false);
    }

    public void populateServices(Boolean isSwipe) {
        if (isSwipe){
            progressBar.setVisibility(View.GONE);
        } else{
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView_planets.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                ApiPlanetsClient.getClient().create(ApiPlanetsInterface.class).getServiceList().enqueue(new Callback<ServicesListResponse>() {
                    @Override
                    public void onResponse(Call<ServicesListResponse> call, Response<ServicesListResponse> response) {
                        if (response.code() == 200) {
                            if (!response.body().getData().isEmpty()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        serviceLists.addAll(response.body().getData());
                                        rvPlanetAdapter.notifyDataSetChanged();
                                        progressBar.setVisibility(View.GONE);
                                        recyclerView_planets.setVisibility(View.VISIBLE);
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                });

                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServicesListResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Swipe To Refresh", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                });
            }
        }).start();
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        serviceLists.clear();
        rvPlanetAdapter.notifyDataSetChanged();
        populateServices(true);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}