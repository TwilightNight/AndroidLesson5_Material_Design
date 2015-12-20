package com.example.shana.androidlesson5_material_design.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shana.androidlesson5_material_design.Adapter.PersonalProfileRecyclerViewAdapter;
import com.example.shana.androidlesson5_material_design.BuildConfig;
import com.example.shana.androidlesson5_material_design.Model.PersonalProfile;
import com.example.shana.androidlesson5_material_design.R;
import com.example.shana.androidlesson5_material_design.Utils.DataDownloader;
import com.example.shana.androidlesson5_material_design.Utils.DataRequest;
import com.example.shana.androidlesson5_material_design.Utils.ImageDownloadManager;
import com.example.shana.androidlesson5_material_design.Utils.LocalDataRequest;
import com.example.shana.androidlesson5_material_design.Utils.UrlDataRequest;
import com.example.shana.androidlesson5_material_design.Views.StatusViewHolder;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shana on 2015/12/17.
 */
public abstract class RecyclerViewFragment extends Fragment {
    private final static String HOST_NAME = "http://Windows11:8888";
    @Bind(R.id.fragment_recycler_layout)
    ViewGroup layout;
    @Bind(R.id.fragment_recycler_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.fragment_recycler_floating_action_button)
    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        loadData();
        return view;
    }

    private void setupFloatingActionButton() {
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(), "On FloatingActionButton on click", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(new PersonalProfileRecyclerViewAdapter(new ArrayList<PersonalProfile>(),
                new PersonalProfileRecyclerViewAdapter.OnViewClickListener() {
            @Override
            public void onClickAt(int position) {
                Snackbar.make(getView(), "On " + position + " item click", Snackbar.LENGTH_SHORT).show();
            }
        }, new PersonalProfileRecyclerViewAdapter.LayoutDataSource() {
            @Override
            public void onGetImageFile(ImageView imageView, String fileName) {
                if (BuildConfig.LOAD_DATA_FROM_INTERNET) {
                    ImageDownloadManager.download(imageView, HOST_NAME + "/img/" + fileName);
                } else {
                    imageView.setImageBitmap(null);
                    imageView.setImageResource(getResources().getIdentifier(fileName, "drawable", imageView.getContext().getPackageName()));
                }
            }

            @Override
            public int getLinearLayoutOrientation() {
                return getLayoutOrientation();
            }
        }));
        recyclerView.setLayoutManager(getLayoutManager());
    }

    private void loadData() {
        StatusViewHolder.addLoadingView(getActivity(), layout);
        new DataDownloader(new DataDownloader.OnDataDownloadFinishListener<String>() {
            @Override
            public void onFinish(String result) {
                StatusViewHolder.removeAllStatusView(layout);
                layoutRecyclerView((ArrayList<PersonalProfile>) new Gson().fromJson(result, new TypeToken<ArrayList<PersonalProfile>>() {
                }.getType()));
                setupFloatingActionButton();
            }

            @Override
            public void onFailed(String errorMessage) {
                layoutErrorView();
            }
        }).download(getDataRequest());
    }

    private void layoutErrorView() {
        StatusViewHolder.addRetryView(getActivity(), layout, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void layoutRecyclerView(ArrayList<PersonalProfile> personalProfileArray) {
        StatusViewHolder.removeAllStatusView(layout);
        PersonalProfileRecyclerViewAdapter adapter = (PersonalProfileRecyclerViewAdapter)recyclerView.getAdapter();
        adapter.appendData(personalProfileArray);
        adapter.notifyDataSetChanged();
    }

    private DataRequest getDataRequest() {
        if (BuildConfig.LOAD_DATA_FROM_INTERNET) {
            return new UrlDataRequest(HOST_NAME);
        }
        return new LocalDataRequest(getActivity(), "result_data");
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract int getLayoutOrientation();
}
