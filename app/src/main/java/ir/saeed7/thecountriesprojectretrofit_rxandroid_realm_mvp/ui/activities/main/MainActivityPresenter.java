package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main;

import javax.inject.Inject;

import io.realm.RealmResults;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.model.Country;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository.TheCountriesRepository;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.base.BasePresenter;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class MainActivityPresenter extends BasePresenter<MainActivityMvpView> {

    @Inject
    TheCountriesRepository repository;

    @Inject
    public MainActivityPresenter() {
    }

    private Subscription subscription;

    @Override
    public void detachView() {
        super.detachView();
        RxUtil.unsubscribe(subscription);
    }

    public void getAllCountries(String region, int sortItem) {
        RxUtil.unsubscribe(subscription);
        subscription = repository.getCountries(region, sortItem)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RealmResults<Country>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e);
                        getMvpView().showError(e);
                    }

                    @Override
                    public void onNext(RealmResults<Country> countries) {
                        getMvpView().showCountries(countries);
                    }
                });
    }

}
