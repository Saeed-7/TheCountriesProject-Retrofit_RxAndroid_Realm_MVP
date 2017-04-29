package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository.TheCountriesRepository;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.ActivityModule;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.ApiModule;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.ApplicationModule;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.RepositoryModule;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module.UtilityModule;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.qualifier.ApplicationContext;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.ui.activities.main.MainActivity;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

@Singleton
@Component(modules = {
        ActivityModule.class,
        ApiModule.class,
        ApplicationModule.class,
        RepositoryModule.class,
        UtilityModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    //Repository
    void inject(TheCountriesRepository theCountriesRepository);

    // Activities
    void inject(MainActivity mainActivity);

}
