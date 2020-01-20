package com.student.rentals.ui.fragments.view_fragment


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.Utils.TermsAndConditionsDialog
import com.student.rentals.R
import com.student.rentals.ui.Adapters.RoomsListAdapter
import kotlinx.android.synthetic.main.item_property_description.*
import kotlinx.android.synthetic.main.item_property_extra_costs.*
import kotlinx.android.synthetic.main.item_property_owner.*
import kotlinx.android.synthetic.main.item_property_owner_profile.*
import kotlinx.android.synthetic.main.item_property_rooms.*


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

        table_method_bank.setOnClickListener(){
            val dialog = TermsAndConditionsDialog(context!!)
            dialog.show()

        }
        property_owner_dp.setOnClickListener(){
            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.item_property_owner_profile)
            GlideApp
                .with(context!!)
                .load("https://kprofiles.com/wp-content/uploads/2019/12/WhatsApp-Image-2019-12-03-at-4.13.01-PM-799x800.jpeg")
                .apply(RequestOptions.circleCropTransform())
                .into(dialog.profile_image)
            dialog.show()
            dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

        }
        property_bedrooms.setOnClickListener(){
            val dialog = Dialog(context!!)
            val houses: ArrayList<String> = ArrayList()
            for (i in 0..10){
                houses.add("House: " +i)
            }
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.item_property_rooms)
            dialog.recycler_rooms_images.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            dialog.recycler_rooms_images.adapter = RoomsListAdapter(houses, requireContext(), onItemClick = { view, catgory -> Unit})

            dialog.show()
            dialog.window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        }


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
