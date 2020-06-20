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

    public void deleteAll() {
        new deleteAllDataAsyncTask(mAddressDao).execute();
    }

    public void update(String orderId) {
        new updateOrderStatusInfoAsyncTask(mAddressDao).execute(orderId);
    }

    public void expandView(String orderId, boolean isExpanded) {
        new updateOrderViewAsyncTask(mAddressDao, isExpanded).execute(orderId);

    }

    private static class updateOrderViewAsyncTask extends AsyncTask<String, Void, Void> {
        private AddressDao mAsyncTaskDao;
        private boolean mIsExpanded;

        updateOrderViewAsyncTask(AddressDao dao, boolean isExpanded) {
            mAsyncTaskDao = dao;
            mIsExpanded = isExpanded;
        }

        @Override
        protected Void doInBackground(final String... orderId) {
            mAsyncTaskDao.updateOrderView(orderId[0], mIsExpanded);
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

    private static class deleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private AddressDao mAsyncTaskDao;

        deleteAllDataAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }


}
