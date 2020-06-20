package com.example.routetitantask.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.routetitantask.R;
import com.example.routetitantask.databinding.StopsFragmentBinding;
import com.example.routetitantask.room.OrderAddress;
import com.example.routetitantask.room.OrderAddressViewModel;

import java.util.List;
import java.util.Objects;


public class StopsFragment extends Fragment implements ItemClickListener {

    private OrderAddressViewModel orderAddressViewModel;

    public static StopsFragment newInstance() {
        return new StopsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderAddressViewModel = ViewModelProviders.of(this).get(OrderAddressViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        StopsFragmentBinding mStopsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.stops_fragment, container, false);
        OrdersAdapter orderAdapter = new OrdersAdapter();
        orderAdapter.setItemClickListener(this);
        mStopsFragmentBinding.stopsRecyclerView.setAdapter(orderAdapter);
        orderAddressViewModel.deleteAll();
        orderAddressViewModel.getAllOrders().observe(getViewLifecycleOwner(), new Observer<List<OrderAddress>>() {
            @Override
            public void onChanged(List<OrderAddress> orderAddresses) {
                orderAdapter.setOrderList(orderAddresses);
            }
        });
        return mStopsFragmentBinding.getRoot();
    }

    @Override
    public void chooseMapAndNavigateToTheAddress(OrderAddress orderAddress) {
        String uriBegin = "geo:" + orderAddress.getLat() + "," + orderAddress.getLng();
        String query = orderAddress.getLat() + "," + orderAddress.getLng();
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(mapIntent, getString(R.string.choose_map));
        if (mapIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    public void setOrderFinished(OrderAddress orderFinished) {
        if (orderFinished.isIsFinished())
            Toast.makeText(getActivity(), getString(R.string.already_finished), Toast.LENGTH_SHORT).show();
        else {
            orderFinished.setIsFinished(true);
            orderAddressViewModel.update(orderFinished.getId());
        }

    }

    @Override
    public void openExpandable(OrderAddress orderAddress) {
        if (orderAddress.isExpanded()) {
            orderAddressViewModel.expandView(orderAddress.getId(), false);
        } else {
            orderAddressViewModel.expandView(orderAddress.getId(), true);
        }
    }
}