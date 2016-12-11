package com.mmarvick.weatherapp.ui;

import android.text.Layout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmarvick.weatherapp.R;
import com.mmarvick.weatherapp.dagger.AppComponent;
import com.mmarvick.weatherapp.presenter.WeatherPresenter;
import com.vlonjatg.progressactivity.ProgressActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity implements WeatherView {

    @Inject WeatherPresenter presenter;

    @BindView(R.id.progress) ProgressActivity progressActivityView;
    @BindView(R.id.content_layout) View contentLayout;
    @BindView(R.id.loading_layout) View loadingLayout;
    @BindView(R.id.loading_text) TextView loadingTextView;
    @BindView(R.id.location) TextView locationTextView;
    @BindView(R.id.degrees) TextView degreesTextView;
    @BindView(R.id.degrees_unit) TextView degreesUnitTextView;

    private static final char DEGREE_FAHRENHEIT = (char) 0x2109;
    private static final char DEGREE_CELSIUS = (char) 0x2103;

    @Override
    int getLayoutResId() {
        return R.layout.activity_weather;
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

    @OnClick(R.id.degrees_layout)
    public void onDegreesLayoutClicked() {
        presenter.onSwitchDegreeUnit();
    }

    @Override
    public void showContent() {
        progressActivityView.showContent();
        contentLayout.setVisibility(View.VISIBLE);
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressActivityView.showContent();
        contentLayout.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String title, String content, String buttonText) {
        progressActivityView.showError(null, title, content, buttonText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRefresh();
            }
        });
    }

    @Override
    public void setLoadingText(String loadingText) {
        loadingTextView.setText(loadingText);
    }

    @Override
    public void setLocation(String location) {
        locationTextView.setText(location);
    }

    @Override
    public void setDegrees(int degrees) {
        degreesTextView.setText(Integer.toString(degrees));
    }

    @Override
    public void setFahrenheit() {
        degreesUnitTextView.setText(Character.toString(DEGREE_FAHRENHEIT));
    }

    @Override
    public void setCelsius() {
        degreesUnitTextView.setText(Character.toString(DEGREE_CELSIUS));
    }
}
