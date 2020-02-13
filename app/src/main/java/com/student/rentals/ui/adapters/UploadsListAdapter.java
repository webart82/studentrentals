package com.student.rentals.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.student.Utils.Constants;
import com.student.Utils.GlideApp;
import com.student.models.ApartmentData;
import com.student.rentals.R;
import com.student.rentals.R.color;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.ui.activities.ViewPropertyActivity;
import com.student.rentals.ui.adapters.HouseListAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class UploadsListAdapter extends Adapter<UploadsListAdapter.UploadsViewHolder> {
    private static final String TAG = "HouseListAdapter";

    private final LayoutInflater layoutInflater;
     final Context context;
    private List<ApartmentData> aData = new ArrayList<>();

     public UploadsListAdapter (final Context context) {
         this.layoutInflater = LayoutInflater.from(context);
         this.context = context;
     }
    @NonNull
    @Override
    public UploadsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UploadsViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout.item_house_property, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull UploadsViewHolder holder, int position) {
        ApartmentData apartment = this.aData.get(position);
        holder.houseName.setText(apartment.getApartmentName());
        holder.houseLocation.setText(apartment.getLocation());
        holder.housePrice.setText(apartment.getAmount());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        String url = apartment.getThumbNail();


        GlideApp
                .with(context)
                .load(url)
                .transition(withCrossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .apply(requestOptions)
                .into(holder.houseImage);

    }

    @Override
    public int getItemCount() {
        return this.aData.size();
    }

    public void addItems(final List<ApartmentData> aData) {
        this.aData = aData;
        this.notifyDataSetChanged();
    }

    class UploadsViewHolder extends RecyclerView.ViewHolder {
        @BindView(id.house_location)
        TextView houseLocation;
        @BindView(id.house_name)
        TextView houseName;
        @BindView(id.house_image)
        ImageView houseImage;
        @BindView(id.house_price)
        TextView housePrice;


        public UploadsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
