package com.example.shana.androidlesson5_material_design.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by shana on 2015/12/16.
 */
public class RecyclerGridFragment extends RecyclerViewFragment {
    @Override
    protected int getLayoutOrientation() {
        return LinearLayout.VERTICAL;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 2);
    }
}
