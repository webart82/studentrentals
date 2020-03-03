package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.student.models.DataRoom
import com.student.rentals.R
import kotlinx.android.synthetic.main.item_property_room_content.view.*


class RoomsListAdapter(
    private val items: List<DataRoom>?,
    private val context: Context,
    val onItemClick: ((DataRoom) -> Unit)? = null
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

