package com.example.routetitantask.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Database(entities = {OrderAddress.class}, version = 3, exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {

    public abstract AddressDao addressDao();

    private static OrderDatabase INSTANCE;

    public static OrderDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderDatabase.class, "order_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AddressDao mDao;
        PopulateDbAsync(OrderDatabase db) {
            mDao = db.addressDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            List<String> mainAddressList = new ArrayList<>();
            for(int i=1;i<21;i++){
                mainAddressList.add("Random Address" + " " + i+1);
            }
            List<String> secondaryAddressList = new ArrayList<>();
            for(int i=1;i<21;i++){
                secondaryAddressList.add("Secondary Address" + " " + i);
               }
            List<String> deliveryTime = Arrays.asList("08:00-09:00", "09:00-10:00", "11:00-12:00",
                    "11:00-12:00","11:00-12:00","11:00-12:00",
                    "08:00-09:00", "09:00-10:00", "11:00-12:00",
                    "11:00-12:00","11:00-12:00","11:00-12:00",
                    "08:00-09:00", "09:00-10:00", "11:00-12:00",
                    "11:00-12:00","11:00-12:00","11:00-12:00",
                    "11:00-12:00","11:00-12:00");

            List<List<Double>> latLngList = Arrays.asList(
                    Arrays.asList(40.2019506,44.5158318), Arrays.asList(40.2136343,44.521414),
                    Arrays.asList(40.2014241, 44.5066291),Arrays.asList(40.2025509,44.5131657),
                    Arrays.asList(40.206017,44.5117673), Arrays.asList(40.2046563,44.5129712),
                    Arrays.asList(40.2078709,44.51555), Arrays.asList(40.2087886,44.5179157),
                    Arrays.asList(40.2088715,44.5220302),Arrays.asList(40.2089479,44.5212132),
                    Arrays.asList(40.201197,44.51081), Arrays.asList(40.2013425,44.5100858),
                    Arrays.asList(40.2011924,44.508805), Arrays.asList(40.2017089,44.517248),
                    Arrays.asList(40.1993958,44.5164547),Arrays.asList(40.2000052,44.5153429),
                    Arrays.asList(40.203956,44.5190417), Arrays.asList(40.2017312,44.5145356),
                    Arrays.asList(40.2018326,44.5141842), Arrays.asList(40.1984385,44.5194547));

            for (int i = 0; i < mainAddressList.size(); i++) {
                String orderNumber = randomNumericString(8);
                OrderAddress orderAddress = new OrderAddress(latLngList.get(i).get(0), latLngList.get(i).get(1), orderNumber,
                        mainAddressList.get(i), secondaryAddressList.get(i), deliveryTime.get(i), false, false);
                mDao.insert(orderAddress);
            }

            return null;
        }

        private static final String RANDOM_NUMERIC_STRING = "0123456789";

        public static String randomNumericString(int count) {
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int) (Math.random() * RANDOM_NUMERIC_STRING.length());
                builder.append(RANDOM_NUMERIC_STRING.charAt(character));
            }
            return builder.toString();
        }
    }
}


