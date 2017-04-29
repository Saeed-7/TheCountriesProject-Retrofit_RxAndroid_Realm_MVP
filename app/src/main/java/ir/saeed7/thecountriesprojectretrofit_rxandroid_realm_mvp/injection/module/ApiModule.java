package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.api.TheCountriesService;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.qualifier.ActivityScope;
import retrofit2.Retrofit;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

@ActivityScope
@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public TheCountriesService provideTheCountryService(Retrofit restAdapter) {
        return restAdapter.create(TheCountriesService.class);
    }

}
