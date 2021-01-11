package com.example.nestledemo.ui.components.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.DashboardItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final HomeAdapter.HomeListener homeListener;
    private final List<DashboardItem> dashboardItemList;
    int selectedPosition = -1;

    public HomeAdapter(List<DashboardItem> dashboardItemList, HomeAdapter.HomeListener homeListener) {
        this.dashboardItemList = dashboardItemList;
        this.homeListener = homeListener;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        final DashboardItem dashboardItem = dashboardItemList.get(position);
        holder.tvDescription.setText(dashboardItem.getName());
        holder.contentImage.setImageResource(dashboardItem.getImage());
        /*if (selectedPosition == position) {
            AppUtils.showViews(holder.selectimage);
        } else {
            AppUtils.hideViews(holder.selectimage);
        }*/
       /* holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            selectedPosition = position;
            notifyDataSetChanged();
            homeListener.onItemSelected(position);
        });*/
    }


    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contentImage)
        ImageView contentImage;
        @BindView(R.id.tvDescription)
        TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface HomeListener {
        void onItemSelected(int position);
    }
}