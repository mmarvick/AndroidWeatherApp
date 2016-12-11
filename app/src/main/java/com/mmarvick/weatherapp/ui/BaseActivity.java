package com.mmarvick.weatherapp.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.mmarvick.weatherapp.R;
import com.mmarvick.weatherapp.WeatherApplication;
import com.mmarvick.weatherapp.dagger.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by michael on 12/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        onInject(((WeatherApplication) getApplication()).getAppComponent());
    }

    @LayoutRes abstract int getLayoutResId();
    abstract void onInject(AppComponent appComponent);
}
