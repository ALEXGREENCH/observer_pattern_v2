package com.github.alexgreench.observer_pattern_v2;

import android.annotation.SuppressLint;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

class DataRepository extends Observable {

    private String data;

    private DataRepository() {
        getPseudoNetworkData();
    }

    private static DataRepository INSTANCE = null;

    static DataRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DataRepository();
        }
        return INSTANCE;
    }

    private void getPseudoNetworkData() {
        new Handler().postDelayed(() -> {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String currentTime = sdf.format(new Date());
            setData(currentTime);

            notifyObservers();
            getPseudoNetworkData();
        }, 250);
    }

    private void setData(String data) {
        this.data = data;
        setChanged();
    }

    String getDataText() {
        return data;
    }
}