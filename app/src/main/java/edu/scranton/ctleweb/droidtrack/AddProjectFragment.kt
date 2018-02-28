package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import edu.scranton.ctleweb.droidtrack.projtrack.Content

class AddProjectFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_add_project, container, false)

        val clientSpinner: Spinner? = v.findViewById(R.id.client_spinner)

        val spinnerArrayAdapter1 = ArrayAdapter<Content.ClientItem>(
                this.context,
                R.layout.spinner_item,
                Content.CLIENTS
        )

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item)
        clientSpinner?.adapter = spinnerArrayAdapter1

        val typeSpinner: Spinner? = v.findViewById(R.id.type_spinner)

        val spinnerArrayAdapter2 = ArrayAdapter<Content.TypeItem>(
                this.context,
                R.layout.spinner_item,
                Content.TYPES
        )

        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item)
        typeSpinner?.adapter = spinnerArrayAdapter2

        typeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                Log.d("SPINNER", position.toString())
                val item = parentView.getItemAtPosition(position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }

        clientSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                Log.d("SPINNER", position.toString())
                val item = parentView.getItemAtPosition(position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }

        return v
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
        fun newInstance(): AddProjectFragment {
            val fragment = AddProjectFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
