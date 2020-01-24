package com.student.rentals.ui.fragments.home_fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.rentals.R
import com.student.rentals.ui.Adapters.HouseListAdapter
import com.student.rentals.ui.activities.ViewPropertyActivity
import com.student.rentals.ui.fragments.view_fragment.ViewItemFragment
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {
    private val TAG ="HomeFragment"
    val houses: ArrayList<String> = ArrayList()

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.home_fragment, container, false)
        return rootview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.uiState.observe(this, Observer {
            val dataset = it ?: return@Observer
        })
        createHouses()
        createList()
    }
    fun createList(){
        house_list.layoutManager = LinearLayoutManager(context)
        house_list.adapter = HouseListAdapter(houses, requireContext(), onItemClick = {view,catgory -> openActivity(view, catgory)})
    }
    fun createHouses(){
        for (i in 0..10){
            houses.add("House: " +i)
        }

    }
    fun openActivity(view: View, category: String){
       startActivity(Intent(context, ViewPropertyActivity::class.java))
    }

}
