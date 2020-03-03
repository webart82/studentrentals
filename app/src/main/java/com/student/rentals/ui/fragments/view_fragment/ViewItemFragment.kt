package com.student.rentals.ui.fragments.view_fragment


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.request.RequestOptions
import com.student.Utils.GlideApp
import com.student.rentals.R
import com.student.rentals.ui.activities.LandLoadProfileActivity
import com.student.rentals.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_view_item.*
import kotlinx.android.synthetic.main.item_property_owner.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ViewItemFragment : BaseFragment() {
    /**Fragment is attached to the host Activity**/
    private var viewModel: ViewItemViewModel? = null

    val TAG = "ViewItemFragment"
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(ViewItemViewModel::class.java)
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
        property_owner_action_call.setOnClickListener() {
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

    fun openActivity() {
        startActivity(Intent(context, LandLoadProfileActivity::class.java))
    }
}
