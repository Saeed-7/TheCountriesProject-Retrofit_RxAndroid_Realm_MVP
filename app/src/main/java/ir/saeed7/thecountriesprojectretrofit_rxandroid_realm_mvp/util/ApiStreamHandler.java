package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */

public class ApiStreamHandler {

    public static boolean isError(String input) {
        try {
            JSONObject jsonObject = new JSONObject(input);
            String status = jsonObject.getString("status");
            if (status.equals("error")) {
                return true;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
