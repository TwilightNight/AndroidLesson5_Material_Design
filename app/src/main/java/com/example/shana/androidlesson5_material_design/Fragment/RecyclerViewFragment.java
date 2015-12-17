package com.example.shana.androidlesson5_material_design.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shana.androidlesson5_material_design.Adapter.PersonalProfileRecyclerViewAdapter;
import com.example.shana.androidlesson5_material_design.Model.PersonalProfile;
import com.example.shana.androidlesson5_material_design.R;
import com.example.shana.androidlesson5_material_design.Utils.DataDownloader;
import com.example.shana.androidlesson5_material_design.Utils.LocalDataRequest;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import junit.framework.Assert;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shana on 2015/12/17.
 */
public abstract class RecyclerViewFragment extends Fragment {
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
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.progressing), getResources().getString(R.string.progressing_hint));
        new DataDownloader(new DataDownloader.OnDataDownloadFinishListener() {
            @Override
            public void onFinish(String result) {
                progressDialog.dismiss();
                ArrayList<PersonalProfile> list = new Gson().fromJson(result, new TypeToken<ArrayList<PersonalProfile>>(){}.getType());
                recyclerView.setAdapter(new PersonalProfileRecyclerViewAdapter(list, getLayoutOrientation(), new PersonalProfileRecyclerViewAdapter.OnViewClickListener() {
                    @Override
                    public void onClickAt(int position) {
                        Toast.makeText(getActivity(), "On " + position + " item click",Toast.LENGTH_SHORT).show();
                    }
                }));
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.setLayoutManager(getLayoutManager());
            }

            @Override
            public void onFailed(String errorMessage) {
                progressDialog.dismiss();
                System.out.println(errorMessage);
                Assert.assertTrue(false);
            }
        }).download(new LocalDataRequest(getActivity(), "result_data"));
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract int getLayoutOrientation();
}
