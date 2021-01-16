package com.example.nestledemo.ui.components.flavors;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.Flavor;
import com.example.nestledemo.ui.components.addons.AddonActivity;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FlavorsActivity extends BaseActivity implements FlavorAdapter.FlavorListener, AdapterView.OnItemClickListener {

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
        Flavor flavor1 = new Flavor("Nescafe", R.drawable.nescafecoffee);
        Flavor flavor2 = new Flavor("Cappucino", R.drawable.cappucino);
        Flavor flavor3 = new Flavor("Chai Latte", R.drawable.chailatte);
        Flavor flavor4 = new Flavor("Long Black", R.drawable.mocha);
        Flavor flavor5 = new Flavor("Espresso", R.drawable.espresso);
        Flavor flavor6 = new Flavor("Flat White", R.drawable.flatwhite);
        flavorList.add(flavor1);
        flavorList.add(flavor2);
        flavorList.add(flavor3);
        flavorList.add(flavor4);
        flavorList.add(flavor5);
        flavorList.add(flavor6);
        flavorAdapter.notifyDataSetChanged();
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_flavors;
    }

    @Override
    public void onItemSelected(int position) {
        Flavor selectedFlavor = flavorList.get(position);
        AppUtils.showToast(this, "You have selected " + selectedFlavor.getName());
        showSizeDialog();
    }

    public void showSizeDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog_cup_size);

        List<String> milkTypes = new ArrayList<>();
        milkTypes.add("Full Cream Milk");
        milkTypes.add("Low Fat Milk");
        milkTypes.add("Soy Milk");
        milkTypes.add("Almond Milk");


        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);
        RadioGroup rdGroup = dialog.findViewById(R.id.rdGroup);
        Spinner spinner =  dialog.findViewById(R.id.spinner);
//        spinner.setOnItemClickListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, milkTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        btnSubmit.setOnClickListener(v -> {
            int selectedId = rdGroup.getCheckedRadioButtonId();
            RadioButton radioButton = dialog.findViewById(selectedId);
            if (selectedId == -1) {
                AppUtils.showToast(this, "Please select your choice");
            } else {
                dialog.dismiss();
                String selectedSize = radioButton.getText().toString();
                AppUtils.showToast(this, "Selected " + selectedSize);
                startActivity(new Intent(FlavorsActivity.this, AddonActivity.class)
                        .putExtra("selectedSize", selectedSize));
            }

        });
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}