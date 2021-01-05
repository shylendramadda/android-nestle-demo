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
        holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            // TODO
            AppUtils.showToast(holder.itemView.getContext(), flavor.getName());
        });
    }


    @Override
    public int getItemCount() {
        return flavorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.flavorImage)
        ImageView flavorImage;
        @BindView(R.id.flavorName)
        TextView flavorName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}