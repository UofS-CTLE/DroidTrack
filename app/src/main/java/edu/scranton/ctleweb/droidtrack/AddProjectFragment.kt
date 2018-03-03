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
import edu.scranton.ctleweb.droidtrack.projtrack.LoaderThread
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        val layout: LinearLayout = v.parent as LinearLayout
        when (v.id) {
            R.id.submit_project -> {
                val titleText: EditText = layout.findViewById(R.id.title)
                val description: EditText = layout.findViewById(R.id.description)
                val completed: CheckBox = layout.findViewById(R.id.completed)
                val hours: EditText = layout.findViewById(R.id.hours)
                val users: EditText = layout.findViewById(R.id.users)
                val walkIn: CheckBox = layout.findViewById(R.id.walk_in)
                val project: MyProjectsContent.ProjectItem = MyProjectsContent.ProjectItem(
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
                val projects = sg.addProject(Content.TOKEN,
                        title = project.title,
                        description = project.description,
                        completed = project.completed,
                        client = project.client,
                        type = project.type,
                        date = project.date,
                        hours = project.hours,
                        users = project.consultants,
                        walk_in = walkIn.isChecked,
                        semester = Content.CURRENT_SEMESTER.semester
                )
                projects.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        Toast.makeText(context,
                                "Project submitted.",
                                Toast.LENGTH_LONG).show()
                        val ld = LoaderThread(Content.TOKEN)
                        ld.clearLists()
                        ld.start()
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        Toast.makeText(context,
                                "Whoops. Something's not right.",
                                Toast.LENGTH_LONG).show()
                    }
                })
                Log.d("AddProject", "Project submitted.")
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
