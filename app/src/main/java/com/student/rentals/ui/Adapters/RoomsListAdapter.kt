package com.student.rentals.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.rentals.R
import kotlinx.android.synthetic.main.fragment_view_item.view.*
import kotlinx.android.synthetic.main.item_property_room_images.view.*


class RoomsListAdapter(
    private val items: ArrayList<String>,
    private val context: Context,
    val onItemClick: ((View, String) -> Unit)? = null
) : RecyclerView.Adapter<RoomsListAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_property_room_images, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder?.room_size?.text = items.get(position)
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(1))
        var url = "https://d3mqmy22owj503.cloudfront.net/00/500800/images/site_graphics/slider2.jpg"



        GlideApp
            .with(context)
            .load(url)
            .error(R.drawable.photo)
            .placeholder(R.drawable.photo)

            .apply(requestOptions)
            .into(holder?.room_image)
    }
    class ImagesViewHolder(view: View): RecyclerView.ViewHolder(view){
        val room_image = view.property_room_image
        val room_size = view.property_room_size
        val room_name = view.property_room_name

    }
}

