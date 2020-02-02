package com.student.rentals.ui.fragments.list_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.student.models.dUserData
import com.student.models.mUsers
import com.student.rentals.R
import com.student.rentals.ui.activities.LandLoadProfileActivity
import com.student.rentals.ui.adapters.LandLoardsListAdapter
import kotlinx.android.synthetic.main.list_fragment.*

class ListItemsFragment : Fragment() {
    val landloards: ArrayList<String> = ArrayList()
    private val model: ListItemsViewModel by activityViewModels()
    companion object {
        fun newInstance() = ListItemsFragment()
    }


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

    fun createList(data: List<dUserData>){
        recycler_landloards.layoutManager = LinearLayoutManager(context)
        var landLoardsListAdapter: LandLoardsListAdapter
        landLoardsListAdapter = LandLoardsListAdapter(data, requireContext(),  null)

        recycler_landloards.adapter = landLoardsListAdapter
    }

    fun openActivity(): ((View, dUserData) -> Unit)? {
        startActivity(Intent(context, LandLoadProfileActivity::class.java))
        return null
    }

}
