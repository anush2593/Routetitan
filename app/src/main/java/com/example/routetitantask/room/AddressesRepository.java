package com.example.routetitantask.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AddressesRepository {
    private AddressDao mAddressDao;
    private LiveData<List<OrderAddress>> mAllOrderAddresses;

    AddressesRepository(Application application) {
        OrderDatabase db = OrderDatabase.getDatabase(application);
        mAddressDao = db.addressDao();
        mAllOrderAddresses = mAddressDao.getAllOrders();
    }

    LiveData<List<OrderAddress>> getAllOrders() {
        return mAllOrderAddresses;
    }

    public void update(String orderId)  {
        new updateOrderStatusInfoAsyncTask(mAddressDao).execute(orderId);
    }

    public void expandView(String orderId){
        new updateOrderViewAsyncTask(mAddressDao).execute(orderId);

    }

    private static class updateOrderViewAsyncTask extends AsyncTask<String, Void, Void> {
        private AddressDao mAsyncTaskDao;

        updateOrderViewAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... orderId) {
            mAsyncTaskDao.updateOrderView(orderId[0], true);
            return null;
        }
    }


    private static class updateOrderStatusInfoAsyncTask extends AsyncTask<String, Void, Void> {
        private AddressDao mAsyncTaskDao;

        updateOrderStatusInfoAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... orderId) {
            mAsyncTaskDao.update(orderId[0], true);
            return null;
        }
    }


}
