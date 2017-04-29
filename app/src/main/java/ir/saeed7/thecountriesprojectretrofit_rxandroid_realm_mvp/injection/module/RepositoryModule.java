package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module;

import dagger.Module;
import dagger.Provides;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository.SharedPreferencesRepository;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository.TheCountriesRepository;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

@Module
public class RepositoryModule {

    @Provides
    public SharedPreferencesRepository provideSharedPreferencesRepository() {
        return new SharedPreferencesRepository();
    }

    @Provides
    public TheCountriesRepository provideTheCountriesRepository() {
        return new TheCountriesRepository();
    }

}
