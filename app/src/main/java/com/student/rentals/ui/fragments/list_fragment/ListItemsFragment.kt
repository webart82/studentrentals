package com.student.rentals.ui.fragments.list_fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.student.rentals.R
import com.student.rentals.ui.adapters.LandLoardsListAdapter
import com.student.rentals.ui.activities.LandLoadProfileActivity
import kotlinx.android.synthetic.main.list_fragment.*

class ListItemsFragment : Fragment() {
    val landloards: ArrayList<String> = ArrayList()

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

        createList()
        createHouses()
    }

    fun createList(){
        recycler_landloards.layoutManager = LinearLayoutManager(context)
        recycler_landloards.adapter = LandLoardsListAdapter(landloards, requireContext(), onItemClick = { view, catgory -> openActivity(view, catgory)})
    }
    fun createHouses(){
        for (i in 0..10){
            landloards.add("House: " +i)
        }

    }
    fun openActivity(view: View, category: String){
        startActivity(Intent(context, LandLoadProfileActivity::class.java))
    }

}
