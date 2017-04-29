package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.injection.module;

import android.util.Base64;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.config.Config;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by Saeed-7
 * @email: en.SaeedFekri@gmail.com
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("OKHttp").d(message);
            }
        });

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logging) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(logging);
        }

        builder.connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES);

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(Config.ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    protected OkHttpClient.Builder addBasicAuthToken(OkHttpClient.Builder okHttpClient) {
        final String basicToken = "Basic " + Base64.encodeToString(("").getBytes(), Base64.NO_WRAP);

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", basicToken)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        });

        return okHttpClient;
    }

}
