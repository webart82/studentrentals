package com.student.rentals.ui.fragments.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.student.rentals.R
import timber.log.Timber

open class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d(resources.getString(R.string.on_create))
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Timber.d(resources.getString(R.string.on_attach))
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d(resources.getString(R.string.on_create_view))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d(resources.getString(R.string.on_view_created))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        Timber.d(resources.getString(R.string.on_attach_fragment))
        super.onAttachFragment(childFragment)
    }


    /** **/
    override fun onStart() {
        super.onStart()
        Timber.d(resources.getString(R.string.on_start))
    }

    /** **/
    override fun onResume() {
        super.onResume()
        Timber.d(resources.getString(R.string.on_resume))
    }

    /** **/
    override fun onPause() {
        super.onPause()
        Timber.d(resources.getString(R.string.on_pause))
    }

    /** Fragment is Active **/

    /** **/
    override fun onStop() {
        super.onStop()
        Timber.d(resources.getString(R.string.on_stop))
    }

    override fun onDestroy() {
        Timber.d(resources.getString(R.string.on_destroy))
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d(resources.getString(R.string.on_detach))
        super.onDetach()
    }

    override fun onDestroyView() {
        Timber.d(resources.getString(R.string.on_destroy_view))
        super.onDestroyView()
    }
}