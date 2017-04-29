package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.data.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;

/*
 * Created by Saeed-7
 * @email: en.SaeedFekri@gmail.com
 */

public interface TheCountriesService {

    @GET("/rest/v2/all")
    Observable<ResponseBody> getAllCountries();

}
