package com.student.rentals.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.student.Utils.GlideApp;
import com.student.rentals.R;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.ui.adapters.HouseListAdapter.ViewHolder;
import com.student.rentals.ui.activities.ViewPropertyActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseListAdapter extends Adapter<ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<String> roomDataList;

    public HouseListAdapter(final Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout.item_house_property, parent, false)); }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String title = roomDataList.get(position);
        holder.houseName.setText(roomDataList.get(position));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        String url = "https://d3mqmy22owj503.cloudfront.net/00/500800/images/site_graphics/slider" + getImageAtposition(position) + ".jpg";

        GlideApp
                .with(context)
                .load(url)
                .error(R.drawable.photo)
                .placeholder(R.drawable.photo)

                .apply(requestOptions)
                .into(holder.houseImage);

    }

    @Override
    public int getItemCount() {
        return this.roomDataList.size();
    }

    public void addItems(final List<String> roomDataList) {
        this.roomDataList = roomDataList;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener {
        @BindView(id.house_location)
        TextView houseLocation;
        @BindView(id.house_name)
        TextView houseName;
        @BindView(id.house_image)
        ImageView houseImage;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(this::onLongClick);
            this.houseImage.setOnClickListener(this::onClick);
            this.houseLocation.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case id.house_image:
                    this.startIntent(new Intent(HouseListAdapter.this.context, ViewPropertyActivity.class));
                    break;
                case id.house_location:
                    this.startIntent(new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr=-6.715698,39.219768&daddr=-6.7422793,39.1952295")));
                    break;
            }
        }


        @Override
        public boolean onLongClick(final View v) {
            return false;
        }

        private void startIntent(final Intent intent) {
            HouseListAdapter.this.context.startActivity(intent);
        }
    }
    private Integer getImageAtposition(Integer pos){
        if (pos%10!=0 || pos%10>5){
            return pos;
        }else
            return 1;

    }
}
