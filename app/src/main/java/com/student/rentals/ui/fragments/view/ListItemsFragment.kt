package com.student.rentals.ui.fragments.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.models.DataUser
import com.student.rentals.R
import com.student.rentals.ui.activities.view.LandLoadProfileActivity
import com.student.rentals.ui.adapters.LandLoardsListAdapter
import com.student.rentals.ui.fragments.list_fragment.NotificationsViewModel
import kotlinx.android.synthetic.main.list_fragment.*

class ListItemsFragment : BaseFragment() {
    private val model: NotificationsViewModel by activityViewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.getUsersList()!!.observe(viewLifecycleOwner, Observer{mUsers ->
            createList(mUsers!!.data)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    fun createList(data: List<DataUser>){
        recycler_landloards.layoutManager = LinearLayoutManager(context)
        recycler_landloards.adapter =  LandLoardsListAdapter(data, requireContext(),  null)
        p_progress_bar.visibility = View.GONE
        recycler_landloards.visibility = View.VISIBLE
    }

    fun openActivity(): ((View, DataUser) -> Unit)? {
        startActivity(Intent(context, LandLoadProfileActivity::class.java))
        return null
    }

    companion object {
        fun newInstance() =
            ListItemsFragment()
    }

}
