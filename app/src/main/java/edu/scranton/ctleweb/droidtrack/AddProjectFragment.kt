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
import edu.scranton.ctleweb.droidtrack.projtrack.Content
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent
import java.util.*

class AddProjectFragment : Fragment(), View.OnClickListener {

    private var mListener: OnFragmentInteractionListener? = null
    private var client: Int = 0
    private var type: Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_add_project, container, false)

        val clientSpinner: Spinner? = v.findViewById(R.id.client_spinner)
        val submitButton: Button = v.findViewById(R.id.submit_project)
        submitButton.setOnClickListener(this)

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
                type = (parentView.getItemAtPosition(position) as Content.TypeItem).id
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }

        clientSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                Log.d("SPINNER", position.toString())
                client = (parentView.getItemAtPosition(position) as Content.ClientItem).id
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }

        }

        return v
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.submit_project -> {
                val titleText: EditText = v.findViewById(R.id.title)
                val description: EditText = v.findViewById(R.id.description)
                val completed: CheckBox = v.findViewById(R.id.completed)
                val hours: EditText = v.findViewById(R.id.hours)
                val users: EditText = v.findViewById(R.id.users)
                var project: MyProjectsContent.ProjectItem = MyProjectsContent.ProjectItem(
                        id = "",
                        title = titleText.text.toString(),
                        description = description.text.toString(),
                        completed = completed.isChecked,
                        client = client,
                        type = type,
                        date = Calendar.getInstance().time.toString(),
                        hours = hours.text.toString(),
                        consultants = Content.USERS.filter { s ->
                            s.username == users.text.toString()
                        }
                )
                val sg = ServiceGenerator.createService(ProjtrackService::class.java, Content.TOKEN)
                sg.addProject(Content.TOKEN, project)
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
        fun newInstance(): AddProjectFragment {
            val fragment = AddProjectFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
