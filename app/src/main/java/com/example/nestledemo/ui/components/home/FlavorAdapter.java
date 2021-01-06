package com.example.nestledemo.ui.components.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.Flavor;
import com.example.nestledemo.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.ViewHolder> {
    private List<Flavor> flavorList;
    int selectedPosition = -1;

    public FlavorAdapter(List<Flavor> flavorList) {
        this.flavorList = flavorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_flavor, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Flavor flavor = flavorList.get(position);
        holder.flavorName.setText(flavor.getName());
        holder.flavorImage.setImageResource(flavor.getImage());
        if (selectedPosition == position) {
            AppUtils.showViews(holder.selectedImage);
        } else {
            AppUtils.hideViews(holder.selectedImage);
        }
        holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            selectedPosition = position;
            notifyDataSetChanged();
            AppUtils.showToast(holder.itemView.getContext(), "You selected " + flavor.getName());
        });
    }


    @Override
    public int getItemCount() {
        return flavorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flavorImage)
        ImageView flavorImage;
        @BindView(R.id.selectedImage)
        ImageView selectedImage;
        @BindView(R.id.flavorName)
        TextView flavorName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}