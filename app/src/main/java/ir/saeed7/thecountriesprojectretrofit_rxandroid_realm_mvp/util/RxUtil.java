package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util;

import rx.Subscription;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

public class RxUtil {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
