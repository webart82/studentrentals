package com.student.rentals.ui.activities.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.student.Utils.Constants
import com.student.Utils.Constants.PARCEL_KEY
import com.student.Utils.GlideApp
import com.student.models.DataApartment
import com.student.models.DataRoom
import com.student.models.DataTerms
import com.student.rentals.R
import com.student.rentals.databinding.ActivityViewPropertyBinding
import com.student.rentals.ui.adapters.ExtraCostsListAdapter
import com.student.rentals.ui.adapters.RoomsListAdapter
import com.student.rentals.ui.dialogs.CustomDialogFragment
import com.student.rentals.ui.dialogs.TermsAndConditionsDialog
import com.student.rentals.ui.dialogs.uploadsContentDialog.view.UpdateRoomDialogFragment
import kotlinx.android.synthetic.main.activity_view_property.*
import kotlinx.android.synthetic.main.fragment_view_item.*
import kotlinx.android.synthetic.main.item_property_description.*
import kotlinx.android.synthetic.main.item_property_extra_costs.*
import kotlinx.android.synthetic.main.item_property_owner.*
import timber.log.Timber
import java.util.*


class ViewPropertyActivity : AppBaseActivity() {
    private lateinit var binding: ActivityViewPropertyBinding
    private lateinit var adapter: RoomsListAdapter

    /**System create the activity {@link onCreate}**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_property)
    }

    /** It comes to the foreground
     * The app is now interactive to user **/
    override fun onResume() {
        super.onResume()
        // loadFragment(ViewItemFragment())
        val bundle = intent.getBundleExtra(Constants.PARCEL_BUNDLE)
        val apartmentData = bundle.getParcelable<DataApartment>(Constants.PARCEL_KEY)

        val (_, _, _, _, _, _, thumbNail, apartmentId, _, owner, apartmentName, images, rooms, costs) = apartmentData!!

        val addr = owner?.addresses

        binding.productData = apartmentData
        binding.ownerData = owner
        thumbNail?.let { thumbNail.let { it1 -> updateUI(it1, it) } }
        apartmentName?.let { setupToolbar(it) }

        property_owner_action_call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + addr?.lineOne)
            startActivity(intent)
        }
        property_owner_action_message.setOnClickListener()
        {
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.type = "vnd.android-dir/mms-sms"
            smsIntent.putExtra("address", addr?.lineOne)
            smsIntent.putExtra("sms_body", "Body of Message")
            startActivity(smsIntent)

        }

        property_room_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        property_room_recycler.adapter = RoomsListAdapter(rooms, this, onItemClick = {updateRoomDetails(it)})

        rcv_extra_costs.layoutManager = LinearLayoutManager(this)
        rcv_extra_costs.adapter = ExtraCostsListAdapter(costs, this, onItemClick = { appClickListener(it) })

        property_owner_dp.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(PARCEL_KEY, owner)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            val nInstance = CustomDialogFragment.newInstance("String Here", bundle)

            nInstance.show(ft, "dialog")
        }
        query_icon.visibility = View.VISIBLE
        query_icon.setOnClickListener {
            startCustomTabIntent(-6.7891539, 39.2184032,"restaurants");
        }
    }

    fun appClickListener(dataTermsData: List<DataTerms>) {
        if (dataTermsData.isEmpty()) {
            Toast.makeText(
                this,
                "No terms or condition has being defined currently",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            Timber.d(dataTermsData.toString())
            val bundle = Bundle()
            bundle.putParcelableArrayList(PARCEL_KEY, dataTermsData as ArrayList<out Parcelable>)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            val nInstance = TermsAndConditionsDialog.newInstance("TERMS_DIALOG", bundle)
            nInstance.show(ft, "dialog")
        }
    }


    private fun setupToolbar(str: String) {
        item_toolbar.title = str
        setSupportActionBar(item_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

    private fun startCustomTabIntent(ln: Double, lt: Double, qr: String) {

        val uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%s", ln, lt, qr)
        val gmmIntentUri = Uri.parse(uri);
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
    fun updateRoomDetails(dataRoom: DataRoom) {
        val bundle = Bundle()
        bundle.putParcelable(PARCEL_KEY, dataRoom)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val nInstance = UpdateRoomDialogFragment.newInstance("TERMS_DIALOG", bundle,Constants.ACTION_VIEW)
        nInstance.show(ft, "dialog")
    }

    private fun updateUI(url: String, uThm: String) {
        GlideApp
            .with(this)
            .load(url)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(
                        true
                    ).build()
                )
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(property_image)


        GlideApp
            .with(this)
            .load(Constants.IMAGE_BASE_URL + uThm)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(
                        true
                    ).build()
                )
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.person)
            .error((R.drawable.person))
            .into(property_owner_dp)
    }

}
