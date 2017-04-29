package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.R;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Keys;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.model.Country;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main.adapter.ListViewAdapter;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base.BaseActivity;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class  MainActivity extends BaseActivity implements MainActivityMvpView {

    private Unbinder unbinder;
    private View view;
    private ListViewAdapter listViewAdapter;

    @Inject
    MainActivityPresenter presenter;

    @BindView(R.id.loadingBar)
    ProgressBar loadingBar;

    @BindView(R.id.listViewCountries)
    RecyclerView listViewCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        presenter.attachView(this);
        presenter.getAllCountries(Keys.region_allCountries, 0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showCountries(List<Country> countryList) {
        loadingBar.setVisibility(View.GONE);
        listViewCountries.setVisibility(View.VISIBLE);
        listViewCountries.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapter = new ListViewAdapter(this, countryList, Keys.capital);
        listViewCountries.setAdapter(listViewAdapter);
    }

    @Override
    public void showError(Throwable e) {
        // Do everything with error 'e'
    }
}
