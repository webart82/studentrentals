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
import com.student.models.DataApartment;
import com.student.rentals.R;
import com.student.rentals.R.color;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.ui.activities.view.ViewPropertyActivity;
import com.student.rentals.ui.adapters.HouseListAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static java.lang.Float.parseFloat;

public class HouseListAdapter extends Adapter<ViewHolder> {
    private static final String TAG = "HouseListAdapter";

    private final LayoutInflater layoutInflater;
    final Context context;
    private List<DataApartment> aData = new ArrayList<>();


    public HouseListAdapter(final Context context, final List<DataApartment> aData) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.aData = aData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout.item_house_property, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        DataApartment apartment = this.aData.get(position);
        holder.houseName.setText(apartment.getApartmentName());
        holder.houseLocation.setText(apartment.getApartmentLocation());
        holder.housePrice.setText(apartment.getApartmentAmount());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        String url = apartment.getApartmentThumbnail();


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


    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener {
        @BindView(id.house_location)
        TextView houseLocation;
        @BindView(id.house_name)
        TextView houseName;
        @BindView(id.house_image)
        ImageView houseImage;
        @BindView(id.house_price)
        TextView housePrice;


        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            houseImage.setOnClickListener(this::onClick);
            houseLocation.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(final View v) {
           switch (v.getId()){
               case id.house_image:
                   final int adapterPosition = this.getAdapterPosition();

                   final Intent parcelIntent = new Intent(HouseListAdapter.this.context, ViewPropertyActivity.class);

                   Bundle bundle = new Bundle();
                   bundle.putParcelable(Constants.INSTANCE.getPARCEL_KEY(), aData.get(adapterPosition));
                   parcelIntent.putExtra(Constants.INSTANCE.getPARCEL_BUNDLE(), bundle);
                   parcelIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   HouseListAdapter.this.context.startActivity(parcelIntent);
                   break;
               case id.house_location:
                   DataApartment apartment = aData.get(getAdapterPosition());
                   startCustomTabIntent(parseFloat(apartment.getLongitude()), parseFloat(apartment.getApartmentLatitude()));
                       break;
           }

        }
        @Override
        public boolean onLongClick(final View v) {
            return false;
        }

    }


    private void startCustomTabIntent(float ln, float lt) {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", ln, lt);

        final String PACKAGE_NAME = "com.android.chrome";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(this.context.getResources().getColor(color.primaryColor));
        builder.setShowTitle(true);


        CustomTabsIntent customTabsIntent = builder.build();


        final List<ResolveInfo> resolveInfoList = this.context.getPackageManager().queryIntentActivities(customTabsIntent.intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (final ResolveInfo resolveInfo : resolveInfoList) {
            final String packageName = resolveInfo.activityInfo.packageName;
            Log.d(HouseListAdapter.TAG, packageName);
            if (TextUtils.equals(packageName, PACKAGE_NAME)) {
                customTabsIntent.intent.setPackage(PACKAGE_NAME);
            }
        }
        customTabsIntent.launchUrl(this.context, Uri.parse(uri));
    }
}
