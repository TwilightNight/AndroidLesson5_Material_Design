package com.example.shana.androidlesson5_material_design.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private int orientation;
    private OnViewClickListener listener;
    public PersonalProfileRecyclerViewAdapter(ArrayList<PersonalProfile> profileList, int orientation, OnViewClickListener listener) {
        this.orientation = orientation;
        this.profileList = profileList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.adapter_personal_profile, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView, listener);
        LinearLayout linearLayout = (LinearLayout)contactView.findViewById(R.id.adapter_personal_profile_linear_layout);
        linearLayout.setOrientation(orientation);
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

    public interface OnViewClickListener {
        void onClickAt(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        OnViewClickListener listener;
        @Bind(R.id.adapter_personal_data_name)
        TextView nameText;
        @Bind(R.id.adapter_personal_data_age)
        TextView ageText;
        @Bind(R.id.adapter_profile_picture_image_view)
        ImageView photoImageView;
        @Bind(R.id.adapter_personal_profile_selected_view)
        View selectedView;

        public ViewHolder(View itemView, final OnViewClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    System.out.println(event.getAction());
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            selectedView.setAlpha(0.5f);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            selectedView.setAlpha(0);
                            break;
                        case MotionEvent.ACTION_UP:
                            selectedView.setAlpha(0);
                            listener.onClickAt(getAdapterPosition());
                            break;
                    }
                    return true;
                }
            });
        }

        public void layoutWithPersonalProfile(PersonalProfile personalProfile) {
            nameText.setText("Name: " + personalProfile.getName());
            ageText.setText("Age: " + personalProfile.getAge());
            Context context = photoImageView.getContext();
            photoImageView.setImageResource(context.getResources().getIdentifier(personalProfile.getPhoto(), "drawable", context.getPackageName()));
        }
    }
}
