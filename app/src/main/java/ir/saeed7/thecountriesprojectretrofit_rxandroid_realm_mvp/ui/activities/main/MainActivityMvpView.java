package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main;

import java.util.List;

import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.model.Country;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base.MvpView;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public interface MainActivityMvpView extends MvpView {

    void showCountries(List<Country> countryList);

    void showError(Throwable e);

}
