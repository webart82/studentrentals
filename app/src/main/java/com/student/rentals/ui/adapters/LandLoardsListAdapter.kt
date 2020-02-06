package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.Utils.Constants
import com.student.Utils.GlideApp
import com.student.models.DUserData
import com.student.rentals.R
import kotlinx.android.synthetic.main.item_landloard_list_item.view.*


class LandLoardsListAdapter(
    private val aData: List<DUserData>,
    private val context: Context,
    val onItemClick: ((View, DUserData) -> Unit)? = null) : RecyclerView.Adapter< LandLoardsViewHolder>() {

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
        return aData.size
    }



    override fun onBindViewHolder(holder:  LandLoardsViewHolder, position: Int) {
        val data = aData.get(position)
        holder.u_name?.text = data.userName
        holder.u_description?.text = data.about
        holder.u_date_since?.text = "Since :"+ data.createdDate
        holder.u_fullname?.text = data.fullName
        val url = Constants.IMAGE_BASE_URL + data.thumbNail
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        GlideApp
            .with(context)
            .load(url)
            .transition(withCrossFade(factory))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.person)
            .error(R.drawable.person)
            .into(holder.u_image)

        val item = aData.get(position)
        holder.itemView.setOnClickListener { view ->
            item.let { it -> onItemClick?.invoke(view, aData.get(position)) }
        }
    }
}

class LandLoardsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val u_name = view.landloard_profile_username
    val u_image = view.landloard_profile_image
    var u_description = view.landloard_profile_description
    var u_date_since = view.landloard_profile_since
    var u_fullname = view.landloard_profile_fullname

}