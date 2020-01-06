package com.student.rentals.ui.fragments.list_fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.student.rentals.R

class ListItemsFragment : Fragment() {

    companion object {
        fun newInstance() = ListItemsFragment()
    }

    private lateinit var itemsViewModel: ListItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemsViewModel = ViewModelProviders.of(this).get(ListItemsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
