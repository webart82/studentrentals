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
import com.student.Utils.GlideRequest
import com.student.rentals.R
import kotlinx.android.synthetic.main.item_house_property.view.*
import kotlinx.android.synthetic.main.item_house_property_infos.view.*
import kotlinx.android.synthetic.main.item_landloard_list_item.view.*


class LandLoardsListAdapter(
    private val items: ArrayList<String>,
    private val context: Context,
    val onItemClick: ((View, String) -> Unit)? = null
) : RecyclerView.Adapter< LandLoardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  LandLoardsViewHolder {
        return  LandLoardsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_landloard_list_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder:  LandLoardsViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position)
        var url = "https://d3mqmy22owj503.cloudfront.net/00/500800/images/site_graphics/slider"+position % 5 +".jpg"



        GlideApp
            .with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.photo)
            .into(holder?.image)

        holder.itemView.setOnClickListener { view ->
            onItemClick?.invoke(view, items.get(position))
        }
    }


}

class LandLoardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.landloard_profile_name
    val image = view.landloard_profile_image

}