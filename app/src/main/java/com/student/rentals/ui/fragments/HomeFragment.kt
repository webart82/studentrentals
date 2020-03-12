package com.student.rentals.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager

import com.student.rentals.R
import com.student.rentals.databinding.ProfileFragmentBinding
import com.student.rentals.ui.adapters.MainViewerPager
import com.student.rentals.ui.adapters.ProfilePagerAdapter
import com.student.rentals.ui.fragments.profile_fragment.ProfileFragment
import com.student.rentals.ui.fragments.profile_fragment.ProfileViewModel
import kotlinx.android.synthetic.main.activity_land_loard_profile.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var mainViewerPager: MainViewerPager
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewerPager = MainViewerPager(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager = view.findViewById(R.id.main_viewer_pager)
        viewPager.adapter = mainViewerPager
        main_tab_layout.setupWithViewPager(viewPager)


    }

}
