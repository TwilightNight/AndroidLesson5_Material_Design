package com.example.shana.androidlesson5_material_design.Utils;

import android.os.AsyncTask;

import com.google.common.base.Optional;

/**
 * Created by shana on 2015/12/16.
 */

public class DataDownloader {
    private OnDataDownloadFinishListener listener;

    public DataDownloader(OnDataDownloadFinishListener listener) {
        this.listener = listener;
    }

    public interface OnDataDownloadFinishListener {
        void onFinish(String result);

        void onFailed(String errorMessage);
    }

    public void download(DataRequest request) {
        new DownLoadTask().execute(request);
    }

    private class DownLoadTask extends AsyncTask<DataRequest, Void, Optional<String>> {
        @Override
        protected Optional<String> doInBackground(DataRequest... params) {
            return params[0].execute();
        }

        @Override
        protected void onPostExecute(Optional<String> result) {
            if (result.isPresent()) {
                try {
                    listener.onFinish(result.get());
                } catch (Exception e) {
                    listener.onFailed(e.getMessage());
                }
            } else {
                listener.onFailed("Cannot get data.");
            }
        }
    }
}
