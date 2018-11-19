package com.github.alexgreench.observer_pattern_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private Observable dataRepositoryObservable;

    private TextView textSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataRepositoryObservable = DataRepository.getInstance();

        textSubscriber = findViewById(R.id.text_subscriber);

        findViewById(R.id.btn_subscribe).setOnClickListener(v
                -> dataRepositoryObservable.addObserver(this));

        findViewById(R.id.btn_unsubscribe).setOnClickListener(v -> {
            dataRepositoryObservable.deleteObserver(this);
            textSubscriber.setText(R.string.you_are_unsubscribed);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataRepositoryObservable.deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DataRepository) {
            DataRepository dataRepository = (DataRepository)o;
            textSubscriber.setText(dataRepository.getDataText());
        }
    }
}
