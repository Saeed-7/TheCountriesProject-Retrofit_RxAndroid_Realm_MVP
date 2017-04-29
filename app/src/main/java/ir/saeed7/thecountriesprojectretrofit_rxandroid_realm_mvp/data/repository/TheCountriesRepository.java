package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.R;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Keys;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.api.TheCountriesService;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.model.Country;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util.AlertDialogBuilder;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util.ApiStreamHandler;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util.NetworkUtil;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class TheCountriesRepository extends BaseRepository<Country> {

    private Context context;
    private Subscription subscription;

    @Inject TheCountriesService service;

    @Inject
    public TheCountriesRepository() {
        applicationComponent().inject(this);
        context = applicationComponent().context();
    }

    @Override
    public void detachRepository() {
        super.detachRepository();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void addListToRealm(final String inputList) {
        if (!ApiStreamHandler.isError(inputList)) {
            getRealm().executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.createOrUpdateAllFromJson(Country.class, inputList);
                }
            });
        }
    }

    @Override
    public Integer getCountOfCountries() {
        RealmResults<Country> allCountries = getRealm().where(Country.class).findAllSorted("name");
        return allCountries.size();
    }

    /**
     * In this method, get All Countries from api
     */
    @Override
    public void syncCountries() {
        Observable<ResponseBody> countriesObservable = service.getAllCountries();
        if (!countriesObservable.equals(null)) {
            subscription = countriesObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ResponseBody>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            AlertDialogBuilder.splashError(context).setPositiveButton(R.string.tryAgain, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    syncCountries();
                                }
                            });
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            try {
                                addListToRealm(responseBody.string());
                                Toast.makeText(context, "Loading is complete", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    public Observable<RealmResults<Country>> getCountries(String region, int sortItem) {
        if (NetworkUtil.checkNetConnection(context) &&
                getCountOfCountries() < 240) {
            syncCountries();
        }
        RealmQuery<Country> query;
        Observable<RealmResults<Country>> resultsObservable;
        if (region == Keys.region_allCountries) {
            query = getRealm().where(Country.class);
        } else {
            query = getRealm().where(Country.class).equalTo(Keys.region, region);
        }
        switch (sortItem) {
            case 0:
                resultsObservable = query.findAllSorted("name", Sort.ASCENDING).asObservable();
                break;
            case 1:
                resultsObservable = query.findAllSorted("name", Sort.DESCENDING).asObservable();
                break;
            case 2:
                resultsObservable = query.findAllSorted("population", Sort.DESCENDING).asObservable();
                break;
            case 3:
                resultsObservable = query.findAllSorted("population", Sort.ASCENDING).asObservable();
                break;
            case 4:
                resultsObservable = query.findAllSorted("area", Sort.DESCENDING).asObservable();
                break;
            case 5:
                resultsObservable = query.findAllSorted("area", Sort.ASCENDING).asObservable();
                break;
            default:
                resultsObservable = query.findAllSorted("name", Sort.ASCENDING).asObservable();
                break;
        }
        return resultsObservable;
    }

}
