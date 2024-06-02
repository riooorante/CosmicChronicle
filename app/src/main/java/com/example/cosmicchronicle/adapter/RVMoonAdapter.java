package com.example.cosmicchronicle.adapter;

import android.content.Context;
import android.hardware.camera2.CameraExtensionSession;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.model.SpaceServiceList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RVMoonAdapter extends RecyclerView.Adapter<RVMoonAdapter.ViewHolder> {
    Context context;
    ArrayList<SpaceServiceList> moonsData;

    public RVMoonAdapter(Context context, ArrayList<SpaceServiceList> arrayList) {
        this.context = context;
        this.moonsData = arrayList;
    }

    @NonNull
    @Override
    public RVMoonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_moonslayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVMoonAdapter.ViewHolder holder, int position) {
        holder.bind(moonsData.get(position));

    }

    @Override
    public int getItemCount() {
        return moonsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView englishName;
        private final TextView researcherName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            englishName = itemView.findViewById(R.id.englishName);
            researcherName = itemView.findViewById(R.id.researcherName);

        }

        public void bind(SpaceServiceList spaceServiceList) {
            englishName.setText(spaceServiceList.getName());

            String disName = spaceServiceList.getDiscoveredBy();
            if (disName == null || disName.isEmpty()) {
                researcherName.setText("Undefined");
                return; // Keluar dari metode jika disName kosong
            }

            List<String> listReseracher = new ArrayList<>(Arrays.asList(disName.split(", ")));
            if (listReseracher.isEmpty() || listReseracher.get(0) == null || listReseracher.get(0).isEmpty()) {
                researcherName.setText("Undefined");
            } else {
                String firstNameofList = listReseracher.get(0);
                researcherName.setText(firstNameofList.trim());
            }
        }


    }
}
