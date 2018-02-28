package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent

class AllProjectsFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_allprojects_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            view.adapter = MyAllProjectsRecyclerViewAdapter(AllProjectsContent.ITEMS, mListener)
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: AllProjectsContent.ProjectItem)
    }

    companion object {

        private const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int): AllProjectsFragment {
            val fragment = AllProjectsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
