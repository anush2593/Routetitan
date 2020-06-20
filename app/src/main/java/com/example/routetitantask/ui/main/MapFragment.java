package com.example.routetitantask.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.routetitantask.R;
import com.example.routetitantask.room.OrderAddress;
import com.example.routetitantask.room.OrderAddressViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView gMapView;
    private OrderAddressViewModel orderAddressViewModel;


    public static MapFragment newInstance() {
        return new MapFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        orderAddressViewModel = ViewModelProviders.of(this).get(OrderAddressViewModel.class);

        gMapView = (MapView) view.findViewById(R.id.mapView);
        gMapView.getMapAsync(this);
        gMapView.onCreate(getArguments());
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MapFragment", "onMapReady");

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        List<Marker> markers = new ArrayList<>();
        IconGenerator iconGenerator = new IconGenerator(getActivity());

        orderAddressViewModel.getAllOrders().observe(Objects.requireNonNull(getActivity()), new Observer<List<OrderAddress>>() {
            @Override
            public void onChanged(List<OrderAddress> orderAddresses) {
                for (int i = 0; i < orderAddresses.size(); i++) {
                    iconGenerator.setStyle(IconGenerator.STYLE_BLUE);
                    if (orderAddresses.get(i).isIsFinished())
                        iconGenerator.setStyle(IconGenerator.STYLE_GREEN);
                    else if (orderAddresses.get(i).isExpanded())
                        iconGenerator.setStyle(IconGenerator.STYLE_RED);
                    Bitmap bmp = iconGenerator.makeIcon((i + 1) + "");
                    Marker m = googleMap.addMarker(
                            new MarkerOptions().position(new LatLng(Objects.requireNonNull(orderAddresses.get(i)).getLat(),
                                    Objects.requireNonNull(orderAddresses.get(i)).getLng())).icon(BitmapDescriptorFactory.fromBitmap(bmp)));
                    markers.add(m);
                }
                if (markers.size() != 0) moveCamera(googleMap, markers);
            }
        });
    }

    private void moveCamera(GoogleMap map, List<Marker> markers) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 10);
        map.moveCamera(cu);
        map.animateCamera(cu);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("MapFragment", "OnResume");
        if (gMapView != null)
            gMapView.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MapFragment", "onDestroy");

        if (gMapView != null)
            gMapView.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("MapFragment", "onStart");

        if (gMapView != null)
            gMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("MapFragment", "onStop");

        if (gMapView != null)
            gMapView.onStop();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (gMapView != null)
            gMapView.onSaveInstanceState(outState);
    }
}
