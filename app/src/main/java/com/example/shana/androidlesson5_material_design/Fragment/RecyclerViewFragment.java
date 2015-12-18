package com.example.shana.androidlesson5_material_design.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Bind(R.id.fragment_recycler_floating_action_button)
    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        setupFloatingActionButton();
        setupRecyclerView();
        loadData();
        return view;
    }

    private void setupFloatingActionButton() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(), "On FloatingActionButton on click", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(new PersonalProfileRecyclerViewAdapter(new ArrayList<PersonalProfile>(), getLayoutOrientation(), new PersonalProfileRecyclerViewAdapter.OnViewClickListener() {
            @Override
            public void onClickAt(int position) {
                Snackbar.make(getView(), "On " + position + " item click", Snackbar.LENGTH_SHORT).show();
            }
        }));
        recyclerView.setLayoutManager(getLayoutManager());
    }

    private void loadData() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.progressing), getResources().getString(R.string.progressing_hint));
        new DataDownloader(new DataDownloader.OnDataDownloadFinishListener() {
            @Override
            public void onFinish(String result) {
                progressDialog.dismiss();
                layoutRecyclerView((ArrayList<PersonalProfile>) new Gson().fromJson(result, new TypeToken<ArrayList<PersonalProfile>>() {
                }.getType()));
            }

            @Override
            public void onFailed(String errorMessage) {
                progressDialog.dismiss();
                System.out.println(errorMessage);
                Assert.assertTrue(false);
            }
        }).download(new LocalDataRequest(getActivity(), "result_data"));
    }

    private void layoutRecyclerView(ArrayList<PersonalProfile> personalProfileArray) {
        PersonalProfileRecyclerViewAdapter adapter = (PersonalProfileRecyclerViewAdapter)recyclerView.getAdapter();
        adapter.appendData(personalProfileArray);
        adapter.notifyDataSetChanged();
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract int getLayoutOrientation();
}
