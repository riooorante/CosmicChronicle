package com.example.cosmicchronicle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.client.ApiPlanetsClient;
import com.example.cosmicchronicle.client.ApiPlanetsInterface;
import com.example.cosmicchronicle.model.SpaceServiceList;
import com.example.cosmicchronicle.planet.ServicesListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanetIdActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ProgressBar progressLayout;
    LinearLayout linearLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    private ImageView gambarPlanet;
    private TextView namaPlanet, paragrafPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planet_id);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.mainContentPlanetID);
        progressLayout = findViewById(R.id.planetIDLoading);
        gambarPlanet = findViewById(R.id.gambarPlanetID);
        namaPlanet = findViewById(R.id.planetName);
        paragrafPlanet = findViewById(R.id.planetParagraph);
        swipeRefreshLayout = findViewById(R.id.swipeLoadPlanetID);
        swipeRefreshLayout.setOnRefreshListener(this);

        String planetId = getIntent().getStringExtra("id");
        if (planetId != null) {
            populateServices(planetId, false);
        }

    }

    public void populateServices(String planetId, Boolean isSwipe) {

        if (isSwipe){
            progressLayout.setVisibility(View.GONE);
        } else {
            progressLayout.setVisibility(View.VISIBLE);
        }

        linearLayout.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String filter = "id,eq," + planetId;
                ApiPlanetsClient.getClient().create(ApiPlanetsInterface.class)
                        .getSpecificSpaceObject(filter)
                        .enqueue(new Callback<ServicesListResponse>() {
                            @Override
                            public void onResponse(Call<ServicesListResponse> call, Response<ServicesListResponse> response) {
                                if (response.code() == 200) {
                                    List<SpaceServiceList> planetData = response.body().getData();
                                    if (!planetData.isEmpty()) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                SpaceServiceList specificPlanet = planetData.get(0);
                                                updateUI(specificPlanet);
                                                progressLayout.setVisibility(View.GONE);
                                                linearLayout.setVisibility(View.VISIBLE);
                                                swipeRefreshLayout.setRefreshing(false);
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ServicesListResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                                progressLayout.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Swipe To Refresh", Toast.LENGTH_SHORT).show();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
            }
        }).start();
    }

    private void updateUI(SpaceServiceList planet) {
        int imageResourceId = getResources().getIdentifier(
                planet.getId(), "drawable", getPackageName());
        gambarPlanet.setImageResource(imageResourceId);
        namaPlanet.setText("The " + planet.getEnglishName());
        String paragraf = String.format("Planet %s is one of the planets in the solar system. The gravitational acceleration on the surface of this planet is %.2f m/s². The average temperature on this planet is %.0f Kelvin.", planet.getEnglishName(), planet.getGravity(), planet.getAvgTemp());
        paragrafPlanet.setText(paragraf);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        String planetId = getIntent().getStringExtra("id");
        if (planetId != null) {
            populateServices(planetId, true);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}