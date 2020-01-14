package com.student.rentals.ui.fragments.view_fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.rentals.R
import kotlinx.android.synthetic.main.fragment_view_item.*
import kotlinx.android.synthetic.main.item_property_owner.*


/**
 * A simple [Fragment] subclass.
 */
class ViewItemFragment : Fragment() {
    /**Fragment is attached to the host Activity**/
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    /**A new fragment instance initializes, happen soon after being attached to the host**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**Fragment create its own view hierarchy, which is added to its
     * Activity view hierarchy **/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_view_item, container, false)
        return view
    }

    /**Fragment activity has finished its own
     *{@link com.student.rentals.ui.fragments.view_fragment.onCreate onCreate}
     * **/
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlideApp
            .with(context!!)
            .load("https://kprofiles.com/wp-content/uploads/2019/12/WhatsApp-Image-2019-12-03-at-4.13.01-PM-799x800.jpeg")
            .apply(RequestOptions.circleCropTransform())
            .into(property_owner_dp)


    }


    /** **/
    override fun onStart() {
        super.onStart()
    }

    /** **/
    override fun onResume() {
        super.onResume()
    }

    /** **/
    override fun onPause() {
        super.onPause()
    }

    /** Fragment is Active **/

    /** **/
    override fun onStop() {
        super.onStop()
    }

    /** **/
    override fun onDestroyView() {
        super.onDestroyView()
    }

    /** **/
    override fun onDestroy() {
        super.onDestroy()
    }

    /** **/
    override fun onDetach() {
        super.onDetach()
    }

    /** Fragment is Destroyed **/
}
