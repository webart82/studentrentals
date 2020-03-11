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
import com.student.models.DataUser
import com.student.rentals.R
import kotlinx.android.synthetic.main.item_landloard_list_item.view.*
import kotlinx.android.synthetic.main.notification_view.view.*


class NotificationsListAdapter(
    private val aData: List<DataUser>,
    private val context: Context,
    val onItemClick: ((View, DataUser) -> Unit)? = null) : RecyclerView.Adapter< NotificationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  NotificationsViewHolder {
        return  NotificationsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.notification_view,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return aData.size
    }



    override fun onBindViewHolder(holder:  NotificationsViewHolder, position: Int) {
        val data = aData.get(position)
        holder.u_name?.text = data.userName
        val item = aData.get(position)
        holder.itemView.setOnClickListener { view ->
            item.let { it -> onItemClick?.invoke(view, aData.get(position)) }
        }
    }
}

class NotificationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val u_name = view.title


}