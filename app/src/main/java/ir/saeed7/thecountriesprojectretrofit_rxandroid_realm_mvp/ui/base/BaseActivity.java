package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.TheCountriesApplication;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.ApplicationComponent;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public ApplicationComponent applicationComponent() {
        return TheCountriesApplication.get(this).getApplicationComponent();
    }

}
