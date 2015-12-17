package com.example.shana.androidlesson5_material_design.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shana.androidlesson5_material_design.Model.PersonalProfile;
import com.example.shana.androidlesson5_material_design.R;
import com.example.shana.androidlesson5_material_design.Utils.DataDownloader;
import com.example.shana.androidlesson5_material_design.Utils.LocalDataRequest;

import junit.framework.Assert;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shana on 2015/12/16.
 */
public class RecyclerGridFragment extends Fragment {
    @Bind(R.id.fragment_recycler_recycler_view)
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        loadData();
        return view;
    }

    private void loadData() {
        new DataDownloader<ArrayList<PersonalProfile>>(new DataDownloader.OnDataDownloadFinishListener<ArrayList<PersonalProfile>>() {
            @Override
            public void onFinish(ArrayList<PersonalProfile> result) {
                Assert.assertTrue(true);
            }

            @Override
            public void onFailed(String errorMessage) {
                System.out.println(errorMessage);
                Assert.assertTrue(false);
            }
        }).download(new LocalDataRequest(getActivity(), "result_data"));
    }
}
