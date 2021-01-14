package com.example.nestledemo.ui.components.stores;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

import com.example.nestledemo.R;
import com.example.nestledemo.model.MarkerData;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;

public class StoresActivity extends BaseActivity implements OnMapReadyCallback {

    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_stores;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions()
//                .position(sydney)
//                .title("Marker in Sydney"));
        ArrayList<MarkerData> markersArray = new ArrayList<MarkerData>();

        MarkerData m1 = new MarkerData();
        m1.setLatitude(41.386543747527966);
        m1.setLongitude(2.177602097907546);
        m1.setTitle("Starbucks (Cafe),Via Laietana");
        markersArray.add(m1);

        MarkerData m2 = new MarkerData();
        m2.setLatitude(41.39178498600898);
        m2.setLongitude(2.164716590551906);
        m2.setTitle("Starbucks (Cafe), C. del Consell de Cent");
        markersArray.add(m2);

        MarkerData m3 = new MarkerData();
        m3.setLatitude(41.39044257843253);
        m3.setLongitude(2.1305383225876926);
        m3.setTitle("Starbucks (Cafe), Avinguda Diagonal");
        markersArray.add(m3);

        MarkerData m4 = new MarkerData();
        m4.setLatitude(41.37938734236166);
        m4.setLongitude(2.1502396596389484);
        m4.setTitle("Starbucks (Cafe), Gran Via de les Corts Catalanes");
        markersArray.add(m4);

        MarkerData m5 = new MarkerData();
        m5.setLatitude(41.37820084837243);
        m5.setLongitude(2.1606319747310274);
        m5.setTitle("Galp(Gas Station), Av. del Paral.lel ");
        markersArray.add(m5);

        MarkerData m6 = new MarkerData();
        m6.setLatitude(41.38781684787928);
        m6.setLongitude(2.130002148155966);
        m6.setTitle("BP (Gas Station), Carer de Joan Guell");
        markersArray.add(m6);

//        Projection projection = googleMap.getProjection();
//        LatLng markerPosition = marker.getPosition();
//        Point markerPoint = projection.toScreenLocation(markerPosition);
//        Point targetPoint = new Point(markerPoint.x, markerPoint.y - view.getHeight() / 2);
//        LatLng targetPosition = projection.fromScreenLocation(targetPoint);
//        googleMap.animateCamera(CameraUpdateFactory.newLatLng(targetPosition), 1000, null);
//
//        LatLng coordinate = new LatLng(lat, lng); //Store these lat lng values somewhere. These should be constant.
//        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
//                coordinate, 15);
//        mMap.animateCamera(location);

        for(int i = 0 ; i < markersArray.size() ; i++) {

            createMarker(markersArray.get(i).getLatitude(), markersArray.get(i).getLongitude(), markersArray.get(i).getTitle(), markersArray.get(i).getSnippet(), markersArray.get(i).getIconResID());
        }

    }

    Marker createMarker(double latitude, double longitude, String title, String snippet, int iconResID) {

        return googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet));
//                .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }
}