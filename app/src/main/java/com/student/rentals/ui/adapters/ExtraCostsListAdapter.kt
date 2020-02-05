package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.student.models.ExtraCostsData
import com.student.rentals.BR
import com.student.rentals.R
import com.student.rentals.databinding.ItemPropertyRoomCostsBinding
import kotlinx.android.synthetic.main.item_property_room_costs.view.*


class ExtraCostsListAdapter(
    private val items: List<ExtraCostsData>?,
    private val context: Context,
    val onItemClick: ((View, String) -> Unit)? = null
) : RecyclerView.Adapter<ExtraCostsListAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {

        val binding: ItemPropertyRoomCostsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_property_room_costs, parent, false)


        return ImagesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items!!.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val cost = items?.get(position)
        holder.bind(items!!.get(position))
        /*val terms = cost?.terms
        holder.costAmount.text = cost?.amount.toString()
        holder.costMethod.text = cost?.paymentType
        holder.costTitle.text = cost?.name*/
    }
    class ImagesViewHolder(val binding: ItemPropertyRoomCostsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data:ExtraCostsData) {
            binding.setVariable(BR.extraCosts, data)
            binding.executePendingBindings()
        }
    }

}

