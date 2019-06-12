package com.example.lenskart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenskart.model.Filter;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Filter> lstFilters;
    private OnClickLisetner listener;
    private Context mContext;
    private int selected_position =-1;

    public MainAdapter(Context context, List<Filter> lstShapeCategories, OnClickLisetner onClickLisetner){
        this.lstFilters = lstShapeCategories;
        this.listener = onClickLisetner;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.child_main, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final Filter filterModel = lstFilters.get(position);
        if (filterModel.getDefault().equals(1)) {
            selected_position = viewHolder.getAdapterPosition();
            viewHolder.ivSelect.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.selected));
        } else
            viewHolder.ivSelect.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.unselect));
        viewHolder.tvSelect.setText(filterModel.getName());
        viewHolder.rlSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterModel.getSelectable())
                    listener.onClick(selected_position, viewHolder.getAdapterPosition(), filterModel.getId(), filterModel.getUnSelectableId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstFilters.size();
    }

    public void setData(List<Filter> lstShapeCategories) {
        this.lstFilters = lstShapeCategories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSelect;
        TextView tvSelect;
        RelativeLayout rlSelect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.iv_unselect);
            tvSelect= itemView.findViewById(R.id.tv_catogery_name);
            rlSelect = itemView.findViewById(R.id.rl_select);

        }
    }
}
