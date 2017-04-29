package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.qualifier.ApplicationContext;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return application.getApplicationContext();
    }
}
