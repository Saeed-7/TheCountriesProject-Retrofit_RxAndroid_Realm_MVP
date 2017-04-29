package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.TheCountriesApplication;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.ApplicationComponent;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public ApplicationComponent applicationComponent() {
        return TheCountriesApplication.get(TheCountriesApplication.getAppContext()).getApplicationComponent();
    }
}
