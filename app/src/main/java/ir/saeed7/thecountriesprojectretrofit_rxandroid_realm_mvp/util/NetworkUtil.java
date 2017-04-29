package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

public class NetworkUtil {

    public static boolean checkNetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
