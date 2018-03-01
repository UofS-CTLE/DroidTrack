package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import edu.scranton.ctleweb.droidtrack.projtrack.Content


class AddClientFragment : Fragment(), View.OnClickListener {

    private var mListener: OnFragmentInteractionListener? = null
    private var department: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_add_client, container, false)

        val spinner: Spinner? = v.findViewById(R.id.department_spinner)
        val submitButton: Button = v.findViewById(R.id.submit_client)
        submitButton.setOnClickListener(this)

        val spinnerArrayAdapter = ArrayAdapter<Content.DepartmentItem>(
                this.context,
                R.layout.spinner_item,
                Content.DEPTS
        )

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinner?.adapter = spinnerArrayAdapter

        spinner?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                Log.d("SPINNER", position.toString())
                department = (parentView.getItemAtPosition(position) as Content.DepartmentItem).id
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }
        
        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.submit_client -> {
                val firstName: EditText = v.findViewById(R.id.client_first)
                val lastName: EditText = v.findViewById(R.id.client_last)
                val email: EditText = v.findViewById(R.id.client_email)
                var client: Content.ClientItem = Content.ClientItem(
                        id = 0,
                        first_name = firstName.text.toString(),
                        last_name = lastName.text.toString(),
                        email = email.text.toString(),
                        department = department
                )
                val sg = ServiceGenerator.createService(ProjtrackService::class.java, Content.TOKEN)
                sg.addClient(Content.TOKEN, client)
                Log.d("AddClient", "Client submitted.")
            }
            else -> {
            }
        }
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
