package com.example.shana.androidlesson5_material_design.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shana.androidlesson5_material_design.Model.PersonalProfile;
import com.example.shana.androidlesson5_material_design.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shana on 2015/12/16.
 */
public class PersonalProfileRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<PersonalProfile> profileList;

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.adapter_personal_data_name)
        TextView nameText;
        @Bind(R.id.adapter_personal_data_age)
        TextView ageText;
        @Bind(R.id.adapter_profile_picture_image_view)
        ImageView photoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void layoutWithPersonalProfile(PersonalProfile personalProfile) {
            nameText.setText("Name: " + personalProfile.getName());
            ageText.setText("Age: " + personalProfile.getAge());
            Context context = photoImageView.getContext();
            photoImageView.setImageResource(context.getResources().getIdentifier(personalProfile.getPhoto(), "drawable", context.getPackageName()));
        }
    }

    public PersonalProfileRecyclerViewAdapter(ArrayList<PersonalProfile> profileList) {
        this.profileList = profileList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.adapter_personal_profile_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).layoutWithPersonalProfile(profileList.get(position));
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }
}
