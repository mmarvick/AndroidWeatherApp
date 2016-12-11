package com.mmarvick.weatherapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mmarvick.weatherapp.R;
import com.mmarvick.weatherapp.dagger.AppComponent;
import com.mmarvick.weatherapp.presenter.LocationSearchPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by michael on 12/10/16.
 */

public class LocationSearchActivity extends BaseActivity implements LocationSearchView {

    @Inject LocationSearchPresenter presenter;

    @BindView(R.id.text_input_location) EditText locationEditText;
    @BindView(R.id.btn_search) Button searchButton;
    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.text_error) TextView errorTextView;

    @Override
    int getLayoutResId() {
        return R.layout.activity_location_search;
    }

    @Override
    void onInject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
    }

    @OnClick(R.id.btn_search)
    public void onSearchButtonClicked() {
        String query = locationEditText.getText().toString();
        presenter.onSearch(query);
    }

    @Override
    public void enableLocationInputEdit() {
        locationEditText.setFocusableInTouchMode(true);
    }

    @Override
    public void disableLocationInputEdit() {
        locationEditText.setFocusable(false);
    }

    @Override
    public void showSearchButton() {
        searchButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchButton() {
        searchButton.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorText) {
        errorTextView.setText(errorText);
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorTextView.setVisibility(View.GONE);
    }

    @Override
    public void navigateToWeather() {
        startActivity(new Intent(this, WeatherActivity.class));
    }
}
