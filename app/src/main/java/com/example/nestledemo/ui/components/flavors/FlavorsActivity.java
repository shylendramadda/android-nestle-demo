package com.example.nestledemo.ui.components.flavors;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        Flavor selectedFlavor = flavorList.get(position);
        AppUtils.showToast(this, "You selected " + selectedFlavor.getName());
        showSizeDialog();
    }

    public void showSizeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_cup_size);

        Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        RadioGroup rdGroup = (RadioGroup) dialog.findViewById(R.id.rdGroup);
        btnSubmit.setOnClickListener(v -> {
            dialog.dismiss();
            int selectedId = rdGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) dialog.findViewById(selectedId);
            if (selectedId == -1) {
                AppUtils.showToast(this, "Please select your choice");
            } else {
                AppUtils.showToast(this, "Selected " + radioButton.getText());
                startActivity(new Intent(FlavorsActivity.this, AddonActivity.class));
            }
        });
        dialog.show();
    }
}