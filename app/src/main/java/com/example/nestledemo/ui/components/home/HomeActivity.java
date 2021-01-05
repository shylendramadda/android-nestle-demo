package com.example.nestledemo.ui.components.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.Flavor;
import com.example.nestledemo.ui.components.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rvFlavors)
    RecyclerView rvFlavors;

    private FlavorAdapter flavorAdapter;
    private List<Flavor> flavorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flavorList = new ArrayList<>();
        flavorAdapter = new FlavorAdapter(flavorList);
        rvFlavors.setAdapter(flavorAdapter);

        loadFlavors();
    }

    @OnClick(R.id.cameraIcon)
    public void onCameraClick() {
        // TODO use any barcode library and scan the QR and get the result
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    private void loadFlavors() {
        Flavor flavor1 = new Flavor("Cappucino", R.drawable.cappucino);
        Flavor flavor2 = new Flavor("Chalilatte", R.drawable.chailatte);
        Flavor flavor3 = new Flavor("NestleCoffee", R.drawable.coffeecup);
        Flavor flavor4 = new Flavor("Espresso", R.drawable.espresso);
        Flavor flavor5 = new Flavor("Flat White", R.drawable.flatwhite);
        flavorList.add(flavor1);
        flavorList.add(flavor2);
        flavorList.add(flavor3);
        flavorList.add(flavor4);
        flavorList.add(flavor5);
        flavorAdapter.notifyDataSetChanged();
    }
}