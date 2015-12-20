package com.example.shana.androidlesson5_material_design.Views;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shana.androidlesson5_material_design.R;

/**
 * Created by shana on 2015/12/20.
 */
public class StatusViewHolder {
    public static void addRetryView(Activity activity, ViewGroup parentView, Button.OnClickListener listener) {
        removeAllStatusView(parentView);

        View retryView = activity.getLayoutInflater().inflate(R.layout.status_retry_view, null);
        retryView.setLayoutParams(getFullPageLayoutParams());

        Button retryButton = (Button)retryView.findViewById(R.id.status_retry_view_button);
        retryButton.setOnClickListener(listener);

        parentView.addView(retryView);
    }

    public static void addLoadingView(Activity activity, ViewGroup parentView) {
        removeAllStatusView(parentView);

        View loadingView = activity.getLayoutInflater().inflate(R.layout.status_loading_view, null);
        loadingView.setLayoutParams(getFullPageLayoutParams());

        parentView.addView(loadingView);
    }

    public static void removeAllStatusView(ViewGroup parentView) {
        int[] tryRemoveList = {R.id.status_retry_view_layout, R.id.status_loading_view_layout};
        for (int viewId : tryRemoveList) {
            View view = parentView.findViewById(viewId);
            if (view != null) {
                parentView.removeView(view);
            }
        }
    }

    private static ViewGroup.LayoutParams getFullPageLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}
