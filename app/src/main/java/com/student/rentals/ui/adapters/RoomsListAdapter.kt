package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.models.RoomData
import com.student.rentals.R
import kotlinx.android.synthetic.main.item_property_room_content.view.*
import kotlinx.android.synthetic.main.item_property_room_images.view.*


class RoomsListAdapter(
    private val items: List<RoomData>?,
    private val context: Context,
    val onItemClick: ((RoomData) -> Unit)? = null
) : RecyclerView.Adapter<RoomsListAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_property_room_content, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return  items!!.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val roomData = items?.get(position)
        holder.room_name?.text = roomData?.total.toString() + " " +  roomData?.name
        (holder as ImagesViewHolder).itemView.setOnClickListener{
            roomData.let { onItemClick?.invoke(roomData!!) }
        }


    }
    class ImagesViewHolder(view: View): RecyclerView.ViewHolder(view){

        val room_name = view.property_bedrooms_content_title

    }

}

