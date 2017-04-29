package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository;

import io.realm.Realm;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.TheCountriesApplication;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.ApplicationComponent;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
abstract public class BaseRepository<T> {

    private final ApplicationComponent applicationComponent;
    private Realm realm;

    public BaseRepository() {
        applicationComponent = TheCountriesApplication.get(TheCountriesApplication.getAppContext()).getApplicationComponent();
        this.realm = Realm.getDefaultInstance();
    }

    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    public Realm getRealm() {
        return realm;
    }

    public void detachRepository() {
        realm.close();
    }

    public abstract void addListToRealm(String inputList);

    public abstract Integer getCountOfCountries();

    public abstract void syncCountries();
}
