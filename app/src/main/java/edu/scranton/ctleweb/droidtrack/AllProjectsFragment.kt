package edu.scranton.ctleweb.droidtrack

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AllProjectsFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }

        val arg = arguments
        val token = arg!!.getString("token", "0")

        val sg = ServiceGenerator.createService(ProjtrackService::class.java, token)

        if (AllProjectsContent.ITEMS.size == 0) {
            val projects = sg.getAllProjects(token)
            projects.enqueue(object : Callback<List<AllProjectsContent.ProjectItem>> {
                override fun onResponse(call: Call<List<AllProjectsContent.ProjectItem>>,
                                        response: Response<List<AllProjectsContent.ProjectItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        AllProjectsContent.ITEMS.addAll(response.body()!!)
                        val ft = fragmentManager?.beginTransaction()
                        ft?.detach(this@AllProjectsFragment)?.attach(this@AllProjectsFragment)?.commit()
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("ERRBODY", response.errorBody()!!.string())
                            Toast.makeText(context, response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }

                    }

                }

                override fun onFailure(call: Call<List<AllProjectsContent.ProjectItem>>, t: Throwable) {
                    Toast.makeText(context, "We're having trouble retrieving projects.", Toast.LENGTH_LONG).show()
                    Log.d("DownloadFail", t.toString())
                }
            })
        }
    }

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
