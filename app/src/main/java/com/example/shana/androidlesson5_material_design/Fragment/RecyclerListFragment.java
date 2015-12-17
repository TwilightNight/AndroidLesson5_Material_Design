package com.example.shana.androidlesson5_material_design.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by shana on 2015/12/16.
 */
public class RecyclerListFragment extends RecyclerViewFragment {
    @Override
    protected int getLayoutOrientation() {
        return LinearLayout.HORIZONTAL;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }
}
