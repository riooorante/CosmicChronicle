package com.example.cosmicchronicle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.activity.PlanetIdActivity;
import com.example.cosmicchronicle.model.SpaceServiceList;

import java.util.ArrayList;

public class RVPlanetAdapter extends RecyclerView.Adapter<RVPlanetAdapter.ViewHolder> {

    Context context;

    ArrayList<SpaceServiceList> planetsData;

    public RVPlanetAdapter(Context context, ArrayList<SpaceServiceList> arrayList) {
        this.context = context;
        this.planetsData = arrayList;
    }

    @NonNull
    @Override
    public RVPlanetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_planetslayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVPlanetAdapter.ViewHolder holder, int position) {
        holder.bind(planetsData.get(position));
        holder.dataPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlanetIdActivity.class);

                intent.putExtra("id", planetsData.get(position).getId());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return planetsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView englishName;
        private final ImageView imageView;
        private final ConstraintLayout dataPlanet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dataPlanet = itemView.findViewById(R.id.dataPlanet);
            imageView = itemView.findViewById(R.id.gambarPlanet);
            name = itemView.findViewById(R.id.name);
            englishName = itemView.findViewById(R.id.englishName);

        }

        public void bind(SpaceServiceList spaceServiceList) {
            int imageResourceId = itemView.getContext().getResources().getIdentifier(
                    spaceServiceList.getId(), "drawable", itemView.getContext().getPackageName());

            imageView.setImageResource(imageResourceId);

            name.setText(spaceServiceList.getName());
            englishName.setText(spaceServiceList.getEnglishName());

        }
    }
}
