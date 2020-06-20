package com.example.routetitantask.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddressDao {

    @Insert
    void insert(OrderAddress address);

    @Query("SELECT * from address_table")
    LiveData<List<OrderAddress>> getAllOrders();

    @Query("UPDATE address_table SET order_status=:isFinished WHERE id = :orderId")
    void update(String orderId, boolean isFinished);

    @Query("UPDATE address_table SET is_expanded=:isExpanded WHERE id = :orderId")
    void updateOrderView(String orderId, boolean isExpanded);
}
