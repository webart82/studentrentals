package com.student.rentals.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.student.Utils.Constants
import com.student.models.TermsDatas
import com.student.rentals.R
import com.student.rentals.ui.adapters.TermsAndConditionsListAdapter
import kotlinx.android.synthetic.main.terms_list.*
import timber.log.Timber

class TermsAndConditionsDialog: DialogFragment() {
    private var content:String? = null
    private var u:List<TermsDatas>? = null

    //private lateinit var binding: ItemPropertyPopupProfileBinding
    override fun getTheme(): Int {
        return R.style.AppTheme_NoActionBar_FullScreenDialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        content = arguments?.getString("content")
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogTheme
        setStyle(style,theme)
        retainInstance = true
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d(resources.getString(R.string.on_attach))
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.terms_list, container, false)

        if (u!!.isEmpty()) {
            Toast.makeText(activity, "You have read and learned all Terms and conditions", Toast.LENGTH_SHORT).show()

        }
        return view
    }
    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as android.view.WindowManager.LayoutParams

        dialog_disagree.setOnClickListener {
            Toast.makeText(activity, "Closed as Disagree", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        dialog_agree.setOnClickListener {
            Toast.makeText(activity, "You have read and learned all Terms and conditions", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        recycler_terms_and_conditions.layoutManager = LinearLayoutManager(dialog?.context)
        recycler_terms_and_conditions.adapter = TermsAndConditionsListAdapter(u, dialog?.context!!)
    }

    companion object{
        fun newInstance(content: String, bundle:Bundle): TermsAndConditionsDialog {
            val f = TermsAndConditionsDialog()
            val args = bundle
            var data = bundle.getParcelableArrayList<TermsDatas>(Constants.PARCEL_KEY)
            args.putString("content", content)
            f.arguments = args
            f.u = data


            return f
        }
    }




}