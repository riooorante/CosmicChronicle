package com.example.cosmicchronicle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmicchronicle.R;
import com.example.cosmicchronicle.utils.ConvertTitle;

import java.util.ArrayList;

public class FilterMoonsAdapter extends RecyclerView.Adapter<FilterMoonsAdapter.ViewHolder> {
    Context context;
    ArrayList<String> filters;
    OnFilterClickListener listener;

    public FilterMoonsAdapter(Context context, ArrayList<String> filters, OnFilterClickListener listener) {
        this.context = context;
        this.filters = filters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilterMoonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterMoonsAdapter.ViewHolder holder, int position) {
        String filter = filters.get(position);
        holder.filterTextView.setText(ConvertTitle.toTitleCase(filter));
        holder.itemView.setOnClickListener(v -> listener.onFilterClick(filter));
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView filterTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filterTextView = itemView.findViewById(R.id.filterText);
        }
    }

    public interface OnFilterClickListener {
        void onFilterClick(String filter);
    }
}
