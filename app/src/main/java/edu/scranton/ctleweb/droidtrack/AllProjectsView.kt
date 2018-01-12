package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProjectView.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProjectView.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllProjectsView : Fragment() {

    // TODO: Rename and change types of parameters
    private var project: AllProjectsContent.ProjectItem? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            project = arguments.getSerializable("item") as AllProjectsContent.ProjectItem
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater!!.inflate(R.layout.fragment_all_projects_view, container, false)
        val title: TextView = v.findViewById<TextView>(R.id.title) as TextView
        title.text = project?.title
        val descr: TextView = v.findViewById<TextView>(R.id.description) as TextView
        descr.text = project?.description
        val type: TextView = v.findViewById<TextView>(R.id.type) as TextView
        type.text = project?.projtype?.name
        val clint: TextView = v.findViewById<TextView>(R.id.client) as TextView
        clint.text = "Client: " + project?.client?.last_name
        val hours: TextView = v.findViewById<TextView>(R.id.hours) as TextView
        hours.text = "Hours: " + project?.hours
        val compl: TextView = v.findViewById<TextView>(R.id.completed) as TextView
        compl.text = "Completed: " + project?.completed.toString()
        return v
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the v and potentially other fragments contained in that
     * v.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProjectView.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): AllProjectsView {
            val fragment = AllProjectsView()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
