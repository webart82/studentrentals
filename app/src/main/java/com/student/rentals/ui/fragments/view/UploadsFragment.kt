package com.student.rentals.ui.fragments.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.ApplicationContext
import com.student.Utils.SharedPreferencesManager
import com.student.models.DataApartment
import com.student.rentals.R
import com.student.rentals.ui.adapters.UploadsListAdapter
import com.student.rentals.ui.dialogs.uploadsContentDialog.viewModel.UploadsViewModel
import kotlinx.android.synthetic.main.uploaded_list.*

class UploadsFragment : BaseFragment() {
    private val viewModel: UploadsViewModel? by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.uploaded_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel!!.getRoomsList(ApplicationContext.instance.getsharepref().getString(SharedPreferencesManager.Key.LOGGED_IN_USERID)).observe(viewLifecycleOwner, Observer {
            createList(it?.data)
        })
    }



    private fun createList(apartmentData: List<DataApartment>?) {
        my_uploads_recycler_view.layoutManager = LinearLayoutManager(context)
        my_uploads_recycler_view.isNestedScrollingEnabled = false
        my_uploads_recycler_view.setHasFixedSize(true)
        my_uploads_recycler_view.setItemViewCacheSize(20)
        my_uploads_recycler_view!!.adapter = UploadsListAdapter(activity, apartmentData)
        my_uploads_recycler_view.visibility = View.INVISIBLE
        my_uploads_recycler_view!!.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "ApparmentsFragment"
    }
}