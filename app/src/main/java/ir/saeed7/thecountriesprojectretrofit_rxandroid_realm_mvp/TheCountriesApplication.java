package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Config;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Keys;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository.SharedPreferencesRepository;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.ApplicationComponent;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.DaggerApplicationComponent;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.ApplicationModule;
import timber.log.Timber;

/**
 * Created by Saeed-7
 * @email: en.SaeedFekri@gmail.com
 */

public class TheCountriesApplication extends Application {

    private static Context context;

    ApplicationComponent mApplicationComponent;

    @Inject
    SharedPreferencesRepository repository;

    public static TheCountriesApplication get(Context context) {
        return (TheCountriesApplication) context.getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        // Initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // Initialize Realm
        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name(Config.DATABASE_NAME)
                        .schemaVersion(1)
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        if (repository != null) {
            if (repository.contains(Keys.languages)) {
                changeLanguage(repository.getString(Keys.languages));
            } else {
                Locale locale = getResources().getConfiguration().locale;
                repository.addString(Keys.languages, locale.toString());
                changeLanguage(locale.toString());
            }
        }

    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void changeLanguage(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = getResources().getConfiguration();
        Configuration configuration = new Configuration(config);
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
}
