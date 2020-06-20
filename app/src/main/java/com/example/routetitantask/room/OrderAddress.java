package com.example.routetitantask.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "address_table")
public class OrderAddress {



    @NonNull
    @PrimaryKey()
    private String id;
    @NonNull
    @ColumnInfo(name = "is_expanded")
    private boolean mIsExpanded;

    @NonNull
    @ColumnInfo(name = "main_address")
    private String mOrderMainAddress;
    @NonNull
    @ColumnInfo(name = "secondary_address")
    private String mOrderSecondaryAddress;
    @NonNull
    @ColumnInfo(name = "order_number")
    private String mOrderNumber;
    @NonNull
    @ColumnInfo(name = "order_delivery_time")
    private String mOrderDeliveryTime;

    @ColumnInfo(name = "order_delivery_time_range")
    private String mOrderDeliveryTimeRange;
    @NonNull
    @ColumnInfo(name = "order_lat")
    private double mLat;
    @NonNull
    @ColumnInfo(name = "order_lng")
    private double mLng;
    @NonNull
    @ColumnInfo(name = "order_status")
    private boolean mIsFinished;

    public OrderAddress(double lat, double lng, String orderNumber, String orderMainAddress,
                        String orderSecondaryAddress,
                        String orderDeliveryTime,
                        boolean isFinished,
                        boolean isExpanded) {
        mLat = lat;
        mLng = lng;
        mOrderMainAddress = orderMainAddress;
        mOrderSecondaryAddress = orderSecondaryAddress;
        mOrderDeliveryTime = orderDeliveryTime;
        mIsFinished = isFinished;
        mOrderNumber = orderNumber;
        mIsExpanded = isExpanded;
        id = UUID.randomUUID().toString();
    }
    public boolean isIsExpanded() {
        return mIsExpanded;
    }

    public void setIsExpanded(boolean mIsExpanded) {
        this.mIsExpanded = mIsExpanded;
    }
    public boolean isIsFinished() {
        return mIsFinished;
    }

    public void setIsFinished(boolean mIsFinished) {
        this.mIsFinished = mIsFinished;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double mLat) {
        this.mLat = mLat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double mLng) {
        this.mLng = mLng;
    }


    public String getOrderMainAddress() {
        return mOrderMainAddress;
    }

    public void setOrderMainAddress(String mOrderMainAddress) {
        this.mOrderMainAddress = mOrderMainAddress;
    }

    public String getOrderSecondaryAddress() {
        return mOrderSecondaryAddress;
    }

    public void setOrderSecondaryAddress(String mOrderSecondaryAddress) {
        this.mOrderSecondaryAddress = mOrderSecondaryAddress;
    }

    public String getOrderNumber() {
        return mOrderNumber;
    }

    public void setOrderNumber(String mOrderNumber) {
        this.mOrderNumber = mOrderNumber;
    }

    public String getOrderDeliveryTime() {
        return mOrderDeliveryTime;
    }

    public void setOrderDeliveryTime(String orderDeliveryTime) {
        this.mOrderDeliveryTime = orderDeliveryTime;
    }

    public String getOrderDeliveryTimeRange() {
        return mOrderDeliveryTimeRange;
    }

    public void setOrderDeliveryTimeRange(String orderDeliveryTimeRange) {
        this.mOrderDeliveryTimeRange = orderDeliveryTimeRange;
    }


   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
