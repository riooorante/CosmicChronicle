package com.example.cosmicchronicle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.model.RoverData;

import java.util.ArrayList;

public class RVRoverGalleryAdapter extends RecyclerView.Adapter<RVRoverGalleryAdapter.ViewHolder> {

    Context context;
    ArrayList<RoverData> roverData;

    public RVRoverGalleryAdapter(Context context, ArrayList<RoverData> roverData) {
        this.context = context;
        this.roverData = roverData;
    }

    @NonNull
    @Override
    public RVRoverGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rover_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVRoverGalleryAdapter.ViewHolder holder, int position) {
        RoverData data = roverData.get(position);
        String imageUrl = data.getImgSrc();

        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return roverData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.roverPhoto);
        }
    }
}
