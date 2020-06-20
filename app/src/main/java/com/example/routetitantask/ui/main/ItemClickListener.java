package com.example.routetitantask.ui.main;

import com.example.routetitantask.room.OrderAddress;

public interface ItemClickListener {
     void chooseMapAndNavigateToTheAddress(OrderAddress orderAddress);
     void setOrderFinished(OrderAddress orderFinished);
     void openExpandable(OrderAddress orderAddress);
}
