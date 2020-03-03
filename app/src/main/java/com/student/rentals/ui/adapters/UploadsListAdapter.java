package com.student.rentals.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.student.Utils.Constants;
import com.student.Utils.GlideApp;
import com.student.models.DataApartment;
import com.student.rentals.R;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;
import com.student.rentals.ui.activities.ViewUploadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class UploadsListAdapter extends Adapter<UploadsListAdapter.UploadsViewHolder> {
    private static final String TAG = "HouseListAdapter";

    private final LayoutInflater layoutInflater;
     final Context context;
    private List<DataApartment> aData = new ArrayList<>();

     public UploadsListAdapter (final Context context,final List<DataApartment> aData) {
         this.aData = aData;
         this.layoutInflater = LayoutInflater.from(context);
         this.context = context;
         notifyDataSetChanged();
     }
    @NonNull
    @Override
    public UploadsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UploadsViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout.uploads_item_property, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull UploadsViewHolder holder, int position) {
        DataApartment apartment = this.aData.get(position);
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



    class UploadsViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener {
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
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(final View v) {
            final int adapterPosition = this.getAdapterPosition();
              final Intent parcelIntent = new Intent(UploadsListAdapter.this.context, ViewUploadActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constants.INSTANCE.getPARCEL_KEY(),aData.get(adapterPosition));
                    parcelIntent.putExtra(Constants.INSTANCE.getPARCEL_BUNDLE(), bundle);
                    parcelIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    UploadsListAdapter.this.context.startActivity(parcelIntent);


        }


        @Override
        public boolean onLongClick(final View v) {
            return false;
        }


    }
}
