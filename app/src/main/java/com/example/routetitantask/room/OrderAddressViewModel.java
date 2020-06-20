package com.example.routetitantask.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderAddressViewModel extends AndroidViewModel {
    private AddressesRepository mRepository;

    private LiveData<List<OrderAddress>> mAllOrders;

    public OrderAddressViewModel(Application application) {
        super(application);
        mRepository = new AddressesRepository(application);
        mAllOrders = mRepository.getAllOrders();
    }

   public LiveData<List<OrderAddress>> getAllOrders() {
        return mAllOrders;
    }

    public void update(String orderId) {
        mRepository.update(orderId);
    }


    public void expandView(String orderId, boolean isExpanded) {
        mRepository.expandView(orderId, isExpanded);
    }

}