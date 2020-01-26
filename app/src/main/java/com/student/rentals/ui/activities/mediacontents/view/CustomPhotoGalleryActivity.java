package com.student.rentals.ui.activities.mediacontents.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.student.rentals.R;
import com.student.rentals.R.id;
import com.student.rentals.R.layout;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 1/26/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
public class CustomPhotoGalleryActivity extends Activity {

    private GridView grdImages;
    private Button btnSelect;

    private CustomPhotoGalleryActivity.ImageAdapter imageAdapter;
    private String[] arrPath;
    private boolean[] thumbnailsselection;
    private int[] ids;
    private int count;


    /**
     * Overrides methods
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.custom_gallery);
        this.grdImages = this.findViewById(id.grdImages);
        this.btnSelect = this.findViewById(id.btnSelect);

        String[] columns = {MediaStore.MediaColumns.DATA, BaseColumns._ID};
        String orderBy = BaseColumns._ID;

        @SuppressWarnings("deprecation")
        final Cursor imagecursor = this.managedQuery(Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
        final int image_column_index = imagecursor.getColumnIndex(BaseColumns._ID);
        count = imagecursor.getCount();
        arrPath = new String[count];
        this.ids = new int[this.count];
        thumbnailsselection = new boolean[count];
        for (int i = 0; i < count; i++) {
            imagecursor.moveToPosition(i);
            this.ids[i] = imagecursor.getInt(image_column_index);
            final int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            this.arrPath[i] = imagecursor.getString(dataColumnIndex);
        }

        this.imageAdapter = new CustomPhotoGalleryActivity.ImageAdapter();
        this.grdImages.setAdapter(this.imageAdapter);
        imagecursor.close();


        this.btnSelect.setOnClickListener(new OnClickListener() {

            public void onClick(final View v) {
                int len = CustomPhotoGalleryActivity.this.thumbnailsselection.length;
                int cnt = 0;
                String selectImages = "";
                for (int i = 0; i < len; i++) {
                    if (CustomPhotoGalleryActivity.this.thumbnailsselection[i]) {
                        cnt++;
                        selectImages = selectImages + CustomPhotoGalleryActivity.this.arrPath[i] + "|";
                    }
                }
                if (cnt == 0) {
                    Toast.makeText(CustomPhotoGalleryActivity.this.getApplicationContext(), "Please select at least one image", Toast.LENGTH_LONG).show();
                } else {

                    Log.d("SelectedImages", selectImages);
                    final Intent i = new Intent();
                    i.putExtra("data", selectImages);
                    CustomPhotoGalleryActivity.this.setResult(Activity.RESULT_OK, i);
                    CustomPhotoGalleryActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();

    }

    /**
     * Class method
     */

    /**
     * This method used to set bitmap.
     *
     * @param iv represented ImageView
     * @param id represented id
     */

    private void setBitmap(ImageView iv, int id) {

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(final Void... params) {
                return Thumbnails.getThumbnail(CustomPhotoGalleryActivity.this.getApplicationContext().getContentResolver(), id, Thumbnails.MICRO_KIND, null);
            }

            @Override
            protected void onPostExecute(final Bitmap result) {
                super.onPostExecute(result);
                iv.setImageBitmap(result);
            }
        }.execute();
    }


    /**
     * List adapter
     *
     * @author tasol
     */

    public class ImageAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;

        public ImageAdapter() {
            this.mInflater = (LayoutInflater) CustomPhotoGalleryActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return CustomPhotoGalleryActivity.this.count;
        }

        public Object getItem(final int position) {
            return position;
        }

        public long getItemId(final int position) {
            return position;
        }

        public View getView(final int position, View convertView, final ViewGroup parent) {
            CustomPhotoGalleryActivity.ViewHolder holder;
            if (convertView == null) {
                holder = new CustomPhotoGalleryActivity.ViewHolder();
                convertView = this.mInflater.inflate(layout.custom_gallery_item, null);
                holder.imgThumb = convertView.findViewById(id.imgThumb);
                holder.chkImage = convertView.findViewById(id.chkImage);

                convertView.setTag(holder);
            } else {
                holder = (CustomPhotoGalleryActivity.ViewHolder) convertView.getTag();
            }
            holder.chkImage.setId(position);
            holder.imgThumb.setId(position);
            holder.chkImage.setOnClickListener(new OnClickListener() {

                public void onClick(final View v) {
                    final CheckBox cb = (CheckBox) v;
                    final int id = cb.getId();
                    if (CustomPhotoGalleryActivity.this.thumbnailsselection[id]) {
                        cb.setChecked(false);
                        CustomPhotoGalleryActivity.this.thumbnailsselection[id] = false;
                    } else {
                        cb.setChecked(true);
                        CustomPhotoGalleryActivity.this.thumbnailsselection[id] = true;
                    }
                }
            });
            holder.imgThumb.setOnClickListener(new OnClickListener() {

                public void onClick(final View v) {
                    final int id = holder.chkImage.getId();
                    if (CustomPhotoGalleryActivity.this.thumbnailsselection[id]) {
                        holder.chkImage.setChecked(false);
                        CustomPhotoGalleryActivity.this.thumbnailsselection[id] = false;
                    } else {
                        holder.chkImage.setChecked(true);
                        CustomPhotoGalleryActivity.this.thumbnailsselection[id] = true;
                    }
                }
            });
            try {
                CustomPhotoGalleryActivity.this.setBitmap(holder.imgThumb, CustomPhotoGalleryActivity.this.ids[position]);
            } catch (final Throwable e) {
            }
            holder.chkImage.setChecked(CustomPhotoGalleryActivity.this.thumbnailsselection[position]);
            holder.id = position;
            return convertView;
        }
    }


    /**
     * Inner class
     *
     * @author tasol
     */
    class ViewHolder {
        ImageView imgThumb;
        CheckBox chkImage;
        int id;
    }

}
