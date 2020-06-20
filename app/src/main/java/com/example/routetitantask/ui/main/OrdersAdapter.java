package com.example.routetitantask.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routetitantask.R;
import com.example.routetitantask.databinding.OrderItemBinding;
import com.example.routetitantask.room.OrderAddress;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderInfoViewHolder> {
    private OrderItemBinding mOrderItemBinding;
    private List<OrderAddress> orderList;
    private ItemClickListener itemClickListener;

    public OrdersAdapter() {
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOrderList(List<OrderAddress> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (orderList != null) orderList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mOrderItemBinding = DataBindingUtil.inflate(inflater, R.layout.order_item, parent, false);
        return new OrderInfoViewHolder(mOrderItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderInfoViewHolder holder, int position) {
        OrderAddress orderAddress = orderList.get(position);
        holder.bind(orderAddress);
    }

    @Override
    public int getItemCount() {
        if (orderList != null)
            return orderList.size();
        return 0;
    }

    class OrderInfoViewHolder extends RecyclerView.ViewHolder {
        public OrderItemBinding mOrderItemBinding;

        private OrderInfoViewHolder(@NonNull OrderItemBinding orderItemBinding) {
            super(orderItemBinding.getRoot());
            this.mOrderItemBinding = orderItemBinding;
        }

        void bind(OrderAddress orderAddress) {
            mOrderItemBinding.setOrderAddress(orderAddress);
            mOrderItemBinding.setOrderPosition(getAdapterPosition());
            mOrderItemBinding.setItemClickListener(itemClickListener);
            mOrderItemBinding.executePendingBindings();
        }

    }
}
