package com.example.nestledemo.ui.components.flavors;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.Flavor;
import com.example.nestledemo.ui.components.addons.AddonActivity;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FlavorsActivity extends BaseActivity implements FlavorAdapter.FlavorListener {

    @BindView(R.id.rvFlavors)
    RecyclerView rvFlavors;

    private FlavorAdapter flavorAdapter;
    private List<Flavor> flavorList;
    private Flavor selectedFlavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flavorList = new ArrayList<>();
        flavorAdapter = new FlavorAdapter(flavorList, this);
        rvFlavors.setAdapter(flavorAdapter);
        loadFlavors();
    }

    private void loadFlavors() {
        Flavor flavor1 = new Flavor("Cappucino", R.drawable.cappucino);
        Flavor flavor2 = new Flavor("Chalilatte", R.drawable.chailatte);
        Flavor flavor3 = new Flavor("NestleCoffee", R.drawable.mocha);
        Flavor flavor4 = new Flavor("Espresso", R.drawable.espresso);
        Flavor flavor5 = new Flavor("Flat White", R.drawable.flatwhite);
        flavorList.add(flavor1);
        flavorList.add(flavor2);
        flavorList.add(flavor3);
        flavorList.add(flavor4);
        flavorList.add(flavor5);
        flavorAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_flavors;
    }

    @Override
    public void onItemSelected(int position) {
        selectedFlavor = flavorList.get(position);
        AppUtils.showToast(this, "You selected " + selectedFlavor.getName());
        startActivity(new Intent(this, AddonActivity.class));
    }
}