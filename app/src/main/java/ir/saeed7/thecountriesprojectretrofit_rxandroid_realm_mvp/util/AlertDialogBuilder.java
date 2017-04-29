package ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import ir.saeed7.thecountriesprojectretrofit_rxandroid_realm_mvp.R;

/**
 * Created by Saeed-7
 *
 * @email: en.SaeedFekri@gmail.com
 */
public class AlertDialogBuilder {

    public static AlertDialog.Builder splashError(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.splashError);
        builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        builder.setCancelable(false);

        return builder;
    }

}
