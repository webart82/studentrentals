package com.student.rentals.ui.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.student.models.DataApartment
import com.student.rentals.R
import com.student.rentals.ui.adapters.HouseListAdapter
import com.student.rentals.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.list_fragment.p_progress_bar

class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel? by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.roomsList.observe(viewLifecycleOwner, Observer { (_, _, data) -> createList(data) })
    }

    private fun createList(apartmentData: List<DataApartment>?) {
        house_list.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
        house_list.isNestedScrollingEnabled = false
        house_list.setHasFixedSize(true)
        house_list.setItemViewCacheSize(20)
        house_list!!.adapter = HouseListAdapter(activity, apartmentData)
        p_progress_bar.visibility = View.INVISIBLE
        house_list!!.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}