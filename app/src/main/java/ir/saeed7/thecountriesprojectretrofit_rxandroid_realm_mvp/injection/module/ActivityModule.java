package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return this.activity;
    }
}
