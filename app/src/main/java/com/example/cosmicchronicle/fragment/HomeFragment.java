package com.example.cosmicchronicle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.activity.MoonsActivity;
import com.example.cosmicchronicle.activity.PlanetsActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ImageView planetButton, moonButton;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.galaxyone, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.galaxytwo, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.galaxythree, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        planetButton = view.findViewById(R.id.planetButton);
        moonButton = view.findViewById(R.id.moonButton);

        planetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlanetsActivity.class);
                startActivity(intent);
            }
        });

        moonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoonsActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
