package com.student.rentals.ui.fragments.uploads_fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.Utils.SharedPreferencesManager
import com.student.models.ApartmentData
import com.student.rentals.R
import com.student.rentals.ui.adapters.HouseListAdapter
import com.student.rentals.ui.adapters.UploadsListAdapter
import com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel.UploadsViewModel
import kotlinx.android.synthetic.main.uploaded_list.*

class UploadsFragment : Fragment() {

    private val viewModel: UploadsViewModel? by activityViewModels()
    lateinit var preferencesManager: SharedPreferencesManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.uploaded_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferencesManager = SharedPreferencesManager(activity)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.getRoomsList(preferencesManager.getString(SharedPreferencesManager.Key.LOGGED_IN_USERID))
            .observe(viewLifecycleOwner,
                Observer { (_, _, data) -> createList(data) })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun createList(apartmentData: List<ApartmentData>?) {
        my_uploads_recycler_view.layoutManager = LinearLayoutManager(context)

        my_uploads_recycler_view!!.adapter = UploadsListAdapter(activity, apartmentData)
        my_uploads_recycler_view.visibility = View.INVISIBLE
        my_uploads_recycler_view!!.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}