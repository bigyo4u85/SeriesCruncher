package com.balazs_csernai.seriescruncher.utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
public class NetworkErrorDialogFragment extends DialogFragment {

    public interface NetworkErrorDialogCallback {

        void onNetworkErrorRetry();

        void onNetworkErrorCancel();

    }

    private NetworkErrorDialogCallback callback;

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
        Random random = new Random();
        Resources resources = getResources();
        String dialogTitleText = getRandomText(random, resources.getStringArray(R.array.network_error_titles));
        String[] dialogOptionTexts = new String[]{
                getRandomText(random, resources.getStringArray(R.array.network_error_retry_responses)),
                getRandomText(random, resources.getStringArray(R.array.network_error_cancel_responses))
        };

        return new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle)
                .setTitle(dialogTitleText)
                .setAdapter(new CustomAdapter(dialogOptionTexts), new CustomListener())
                .setCancelable(false)
                .create();
    }

    public String getRandomText(Random random, String[] texts) {
        return texts[random.nextInt(texts.length)];
    }

    class CustomAdapter extends BaseAdapter {
        private final String[] items;

        public CustomAdapter(String[] items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_dialog_list_item, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            populate(holder, position);

            return view;
        }

        private void populate(ViewHolder holder, int position) {
            holder.setText(items[position]);
        }

        class ViewHolder {
            @InjectView(R.id.mainText)
            TextView mainText;

            public ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }

            public void setText(String text) {
                mainText.setText(text);
            }
        }
    }

    class CustomListener implements DialogInterface.OnClickListener {
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
    }
}
