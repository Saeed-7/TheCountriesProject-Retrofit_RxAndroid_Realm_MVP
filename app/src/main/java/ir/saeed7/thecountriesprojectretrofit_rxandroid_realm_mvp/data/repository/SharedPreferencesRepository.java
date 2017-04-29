package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;

import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.TheCountriesApplication;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.component.ApplicationComponent;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class SharedPreferencesRepository {

    private final ApplicationComponent applicationComponent;
    private SharedPreferences preferences;
    private Context context;

    @Inject
    public SharedPreferencesRepository() {
        applicationComponent = TheCountriesApplication.get(TheCountriesApplication.getAppContext()).getApplicationComponent();
        context = applicationComponent.context();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }

    public void addString(String key, String input) {
        preferences.edit().putString(key, input).commit();
    }

    public String getString(String key) {
        if (!preferences.contains(key)) {
            preferences.edit().putString(key, "APPLICATION").commit();
        }
        return preferences.getString(key, "APPLICATION");
    }

    public void addInt(String key, int input) {
        preferences.edit().putInt(key, input).commit();
    }

    public int getInt(String key) {
        if (!preferences.contains(key)) {
            preferences.edit().putInt(key, 0).commit();
        }
        return preferences.getInt(key, 0);
    }

}
