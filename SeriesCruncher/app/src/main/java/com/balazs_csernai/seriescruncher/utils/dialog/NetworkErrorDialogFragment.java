package com.balazs_csernai.seriescruncher.utils.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.balazs_csernai.seriescruncher.R;

import java.util.Random;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
public class NetworkErrorDialogFragment extends DialogFragment {

    public interface NetworkErrorDialogCallback {

        void onNetworkErrorRetry();

        void onNetworkErrorCancel();

    }

    private NetworkErrorDialogCallback callback;
    private Random random;
    private String[] networkErrorTitles;
    private String[] networkErrorRetryResponses;
    private String[] networkErrorCancelResponses;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (NetworkErrorDialogCallback) activity;
        } catch (ClassCastException exception) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must implement NetworkErrorDialogCallback");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        random = new Random();
        Resources resources = getResources();
        networkErrorTitles = resources.getStringArray(R.array.network_error_titles);
        networkErrorRetryResponses = resources.getStringArray(R.array.network_error_retry_responses);
        networkErrorCancelResponses = resources.getStringArray(R.array.network_error_cancel_responses);

        return new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle)
                .setTitle(getRandomText(networkErrorTitles))
                .setItems(new String[]{getRandomText(networkErrorRetryResponses), getRandomText(networkErrorCancelResponses)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        switch (which) {
                            case 0:
                                callback.onNetworkErrorRetry();
                                break;
                            case 1:
                            default:
                                callback.onNetworkErrorCancel();
                        }
                    }
                })
                .setCancelable(false)
                .create();
    }

    public String getRandomText(String[] texts) {
        return texts[random.nextInt(texts.length)];
    }
}
