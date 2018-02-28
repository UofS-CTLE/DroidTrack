package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent
import edu.scranton.ctleweb.droidtrack.projtrack.Content

class AllProjectsView : Fragment() {

    private var project: AllProjectsContent.ProjectItem? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            project = arguments?.getSerializable("item") as AllProjectsContent.ProjectItem
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_all_projects_view, container, false)
        val title: TextView = v.findViewById(R.id.title) as TextView
        title.text = project?.title
        val descr: TextView = v.findViewById(R.id.description) as TextView
        descr.text = project?.description
        val type: TextView = v.findViewById(R.id.type) as TextView
        val typeobj = Content.TYPES[project!!.type]
        type.text = typeobj.name
        val clint: TextView = v.findViewById(R.id.client) as TextView
        val cli = Content.CLIENTS[project!!.client]
        clint.text = "Client: " + cli.last_name
        val hours: TextView = v.findViewById(R.id.hours) as TextView
        hours.text = "Hours: " + project?.hours
        val compl: TextView = v.findViewById(R.id.completed) as TextView
        compl.text = "Completed: " + project?.completed.toString()
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
        fun newInstance(): AllProjectsView {
            val fragment = AllProjectsView()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
