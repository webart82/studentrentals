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


class HouseListAdapter(
    private val items: ArrayList<String>,
    private val context: Context,
    val onItemClick: ((View, String) -> Unit)? = null
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_house_property,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position)
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
        var url = "https://d3mqmy22owj503.cloudfront.net/00/500800/images/site_graphics/slider"+position % 5 +".jpg"



        GlideApp
            .with(context)
            .load(url)
            .placeholder(R.drawable.photo)

            .apply(requestOptions)
            .into(holder?.image)

        holder.itemView.setOnClickListener { view ->
            onItemClick?.invoke(view, items.get(position))
        }
    }

    fun <T> GlideRequest<T>.roundCorners(cornerRadius: Int) =
        apply(RequestOptions().transform(RoundedCorners(cornerRadius)))

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.house_name
    val image = view.house_image

}