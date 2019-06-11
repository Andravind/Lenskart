package com.example.lenskart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenskart.model.Filter;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Filter> lstFilters;
    private OnClickLisetner listener;

    public MainAdapter(List<Filter> lstShapeCategories, OnClickLisetner onClickLisetner){
        this.lstFilters = lstShapeCategories;
        this.listener = onClickLisetner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_main, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {


    }

    @Override
    public int getItemCount() {
        return lstFilters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSelect;
        TextView tvSelect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
