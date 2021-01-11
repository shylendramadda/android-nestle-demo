package com.example.nestledemo.ui.components.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.Flavor;
import com.example.nestledemo.model.Home;
import com.example.nestledemo.ui.components.flavors.FlavorAdapter;
import com.example.nestledemo.utils.AppUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final HomeAdapter.HomeListener homeListener;
    private final List<Home> homeList;
    int selectedPosition = -1;

    public HomeAdapter(List<Home> homeList, HomeAdapter.HomeListener homeListener) {
        this.homeList = homeList;
        this.homeListener = homeListener;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.content_main, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        final Home home = homeList.get(position);
        holder.contentName.setText(home.getName());
        holder.neschoclogo.setImageResource(home.getImage());
        if (selectedPosition == position) {
            AppUtils.showViews(holder.selectimage);
        } else {
            AppUtils.hideViews(holder.selectimage);
        }
        holder.itemView.setOnClickListener((View.OnClickListener) view -> {
            selectedPosition = position;
            notifyDataSetChanged();
            homeListener.onItemSelected(position);
        });
    }


    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.neschoclogo)
        ImageView neschoclogo;
        @BindView(R.id.selectImage)
        ImageView selectimage;
        @BindView(R.id.contentName)
        TextView contentName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface HomeListener {
        void onItemSelected(int position);
    }
}