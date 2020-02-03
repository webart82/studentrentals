package com.student.rentals.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.student.Utils.GlideApp;
import com.student.models.mApartmentData;
import com.student.models.pAData;
import com.student.models.pApartmentData;
import com.student.rentals.R;
import com.student.rentals.R.drawable;
import com.student.rentals.ui.activities.ViewPropertyActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder> {
    private static final String TAG = "HouseListAdapter";

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<pAData> aData;

    public HouseListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public HouseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HouseListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_property, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HouseListAdapter.ViewHolder holder, int position) {
        final pAData apartment = aData.get(position);
        holder.houseName.setText(apartment.getApartmentName());
        holder.houseLocation.setText(apartment.getLocation());
        holder.housePrice.setText(apartment.getAmount());





        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        final String url = apartment.getThumbNail();


        GlideApp
                .with(this.context)
                .load(url)
                .transition(withCrossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(drawable.photo)
                .placeholder(drawable.photo)
                .apply(requestOptions)
                .into(holder.houseImage);

    }

    @Override
    public int getItemCount() {
        return aData.size();
    }

    public void addItems(List<pAData> aData) {
        this.aData = aData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.house_location)
        TextView houseLocation;
        @BindView(R.id.house_name)
        TextView houseName;
        @BindView(R.id.house_image)
        ImageView houseImage;
        @BindView(R.id.house_price)
        TextView housePrice;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(this::onLongClick);
            houseImage.setOnClickListener(this::onClick);
            houseLocation.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.house_image:
                    startIntent(new Intent(context, ViewPropertyActivity.class));
                    break;
                case R.id.house_location:
                    startCustomTabIntent("http://maps.google.com/maps?saddr=-6.715698,39.219768&daddr=-6.7422793,39.1952295");
                    break;
            }
        }


        @Override
        public boolean onLongClick(View v) {
            return false;
        }

        private void startIntent(Intent intent) {
            context.startActivity(intent);
        }
    }


    private void startCustomTabIntent(final String url) {
        String PACKAGE_NAME = "com.android.chrome";
        final Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(context.getResources().getColor(R.color.primaryColor));
        builder.setShowTitle(true);


        final CustomTabsIntent customTabsIntent = builder.build();


        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(customTabsIntent.intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            Log.d(TAG, packageName);
            if (TextUtils.equals(packageName, PACKAGE_NAME)) {
                customTabsIntent.intent.setPackage(PACKAGE_NAME);
            }
        }
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
