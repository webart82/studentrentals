package com.student.rentals.ui.activities.view

import android.os.Bundle
import com.student.ApplicationContext
import com.student.Utils.SharedPreferencesManager
import com.student.models.UploadApartment
import com.student.rentals.R
import com.student.rentals.ui.activities.viewmodel.CreateNewApartmentViewModel
import kotlinx.android.synthetic.main.activity_create_apartment.*

class CreateNewApartmentActivity : AppBaseActivity() {
    var viewModel: CreateNewApartmentViewModel? =
        CreateNewApartmentViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_apartment)
        //setupToolbar("Create new Apartment")
    }

    override fun onResume() {
        setupToolbar("Create new Apartment")
        btn_save.setOnClickListener {
            viewModel!!.createNewApartment(
                UploadApartment(
                    item_type.text.toString(),
                    item_long.text.toString(),
                    item_lat.text.toString(),
                    item_terms.text.toString(),
                    item_amount.text.toString(),
                    ApplicationContext.instance.getsharepref().getString(
                        SharedPreferencesManager.Key.LOGGED_IN_USERID
                    ),
                    item_desc.text.toString(),
                    "hu",
                    item_name.text.toString(),
                    item_location.text.toString()
                )
            )
        }
        super.onResume()
    }

    private fun setupToolbar(str: String) {
        upload_toolbar.title = str
        setSupportActionBar(upload_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }


}
