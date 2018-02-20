package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

class AddClientFragment : Fragment() {


    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val spinner: Spinner? = view?.findViewById(R.id.department_spinner)

        val plants = arrayOf("Black birch", "European weeping birch")

        val spinnerArrayAdapter = ArrayAdapter<String>(
                this.context,
                R.layout.support_simple_spinner_dropdown_item,
                plants
        )

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        spinner?.adapter = spinnerArrayAdapter

        return inflater.inflate(R.layout.fragment_add_client, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(param1: String, param2: String): AddClientFragment {
            val fragment = AddClientFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
