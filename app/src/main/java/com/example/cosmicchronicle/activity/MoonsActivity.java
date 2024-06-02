package com.example.cosmicchronicle.activity;

import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.adapter.FilterMoonsAdapter;
import com.example.cosmicchronicle.adapter.RVMoonAdapter;
import com.example.cosmicchronicle.client.ApiPlanetsClient;
import com.example.cosmicchronicle.client.ApiPlanetsInterface;
import com.example.cosmicchronicle.database.FIlterData;
import com.example.cosmicchronicle.model.SpaceServiceList;
import com.example.cosmicchronicle.planet.ServicesListResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoonsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, FilterMoonsAdapter.OnFilterClickListener {
    RecyclerView recyclerView_moons;
    RVMoonAdapter rvMoonAdapter;
    ArrayList<SpaceServiceList> serviceLists;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    FilterMoonsAdapter filterMoonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_moons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        swipeRefreshLayout = findViewById(R.id.swipeLoadMoons);
        progressBar = findViewById(R.id.MoonsLoading);
        serviceLists = new ArrayList<>();
        recyclerView_moons = findViewById(R.id.rv_moonslist);
        recyclerView_moons.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMoonAdapter = new RVMoonAdapter(MoonsActivity.this, serviceLists);
        recyclerView_moons.setAdapter(rvMoonAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView filterRecyclerView = findViewById(R.id.filterMoons);
        filterRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<String> filters = new ArrayList<>();
        filters.add("terre");
        filters.add("mars");
        filters.add("uranus");
        filters.add("neptune");
        filters.add("mercure");
        filters.add("venus");
        filters.add("saturne");
        filters.add("jupiter");

        filterMoonsAdapter = new FilterMoonsAdapter(this, filters, this);
        filterRecyclerView.setAdapter(filterMoonsAdapter);

        FIlterData.updateFilter(this, "terre");
        String id = FIlterData.getFilter(this);
        populateServices(id, false);
    }

    public void populateServices(String planetId, Boolean isSwipe) {

        if (isSwipe) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }

        recyclerView_moons.setVisibility(View.GONE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.le-systeme-solaire.net/rest/bodies").newBuilder();
                urlBuilder.addQueryParameter("filter[]", "bodyType,eq,Moon");

                if (planetId != null) {
                    urlBuilder.addQueryParameter("filter[]", "aroundPlanet,eq," + planetId);
                }

                String url = urlBuilder.build().toString();
                ApiPlanetsClient.getClient().create(ApiPlanetsInterface.class).getMoons(url).enqueue(new Callback<ServicesListResponse>() {
                    @Override
                    public void onResponse(Call<ServicesListResponse> call, Response<ServicesListResponse> response) {
                        if (response.code() == 200){
                            if (response.body() != null && !response.body().getData().isEmpty()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        serviceLists.addAll(response.body().getData());
                                        rvMoonAdapter.notifyDataSetChanged();
                                        progressBar.setVisibility(View.GONE);
                                        recyclerView_moons.setVisibility(View.VISIBLE);
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
        rvMoonAdapter.notifyDataSetChanged();

        String id = FIlterData.getFilter(this);
        populateServices(id, true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onFilterClick(String filter) {
        FIlterData.updateFilter(this,filter);

        serviceLists.clear();
        rvMoonAdapter.notifyDataSetChanged();

        String id = FIlterData.getFilter(this);
        populateServices(id, false);
    }
}
