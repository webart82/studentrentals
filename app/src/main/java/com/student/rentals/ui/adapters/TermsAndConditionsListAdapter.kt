package com.student.rentals.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.student.models.DataTerms
import com.student.rentals.R
import com.student.rentals.databinding.TermsAndConditionBinding

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 2/6/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
class TermsAndConditionsListAdapter(
    private val terms: List<DataTerms>?,
    private val context: Context
) :
    RecyclerView.Adapter<TermsAndConditionsListAdapter.TermsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        val binding: TermsAndConditionBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.terms_and_condition,
                parent,
                false
            )

        return TermsViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return terms!!.size
    }


    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        val term = terms?.get(position)
        term?.Id = (position + 1).toString()
        holder.bind(term!!)
    }

    class TermsViewHolder(val binding: TermsAndConditionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataTerms) {
            binding.setVariable(BR.termsData, data)
            binding.executePendingBindings()
        }
    }
}