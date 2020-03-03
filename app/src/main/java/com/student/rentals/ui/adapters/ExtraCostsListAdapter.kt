package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.student.models.DataExtraCost
import com.student.models.DataTerms
import com.student.rentals.BR
import com.student.rentals.R
import com.student.rentals.databinding.ItemPropertyRoomCostsBinding


class ExtraCostsListAdapter(
    private val items: List<DataExtraCost>?,
    private val context: Context,
    val onItemClick: (List<DataTerms>) -> Unit): RecyclerView.Adapter<ExtraCostsListAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding: ItemPropertyRoomCostsBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_property_room_costs, parent, false)


        return ImagesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  items!!.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val cost = items?.get(position)
        val terms = cost?.terms
        holder.bind(items!!.get(position))
        if(!cost?.terms!!.isEmpty()){
            holder.binding.txtCostMethod.isEnabled = true
            (holder as ImagesViewHolder).itemView.setOnClickListener{
                terms.let { onItemClick.invoke(terms!!) }
            }
        }

    }
    class ImagesViewHolder(val binding: ItemPropertyRoomCostsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data:DataExtraCost) {
            binding.setVariable(BR.extraCosts, data)
            binding.executePendingBindings()
        }

    }

}

