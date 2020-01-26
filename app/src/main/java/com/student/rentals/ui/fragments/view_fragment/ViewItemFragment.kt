package com.student.rentals.ui.fragments.view_fragment


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.Utils.TermsAndConditionsDialog
import com.student.rentals.R
import com.student.rentals.ui.adapters.RoomsListAdapter
import com.student.rentals.ui.activities.LandLoadProfileActivity
import kotlinx.android.synthetic.main.fragment_view_item.*
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

    val TAG = "ViewItemFragment"
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

        property_owner_dp.setOnClickListener(){
            val dialog = Dialog(context!!)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.item_property_owner_profile)
            var url = "https://i.pinimg.com/originals/be/ac/96/beac96b8e13d2198fd4bb1d5ef56cdcf.jpg"



            GlideApp
                .with(this)
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.photo)
                .into(dialog.profile_image)
            dialog.odp_view_profile.setOnClickListener{
                openActivity()
            }

            dialog.show()
            dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)


        }
        item_property_scrollview.viewTreeObserver
            .addOnScrollChangedListener {
                if (item_property_scrollview.getChildAt(0).getBottom()
                    <= item_property_scrollview.getHeight() + item_property_scrollview.getScrollY()
                ) { //scroll view is at bottom
                    Log.d(TAG, "Scroll is at BOTTOM")
                } else { //scroll view is not at bottom
                    Log.d(TAG, "Scroll is at TOP")
                }
            }
        property_owner_action_call.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0987654321")
            startActivity(intent)
        }
        property_owner_action_message.setOnClickListener()
        {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.type = "vnd.android-dir/mms-sms"
            smsIntent.putExtra("address", "12125551212")
            smsIntent.putExtra("sms_body", "Body of Message")
            startActivity(smsIntent)

        }


    }
    fun openActivity(){
        startActivity(Intent(context, LandLoadProfileActivity::class.java))
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
