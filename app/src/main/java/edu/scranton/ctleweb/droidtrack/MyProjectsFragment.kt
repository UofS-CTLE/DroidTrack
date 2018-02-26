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
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MyProjectsFragment : Fragment() {
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

        if (MyProjectsContent.ITEMS.size == 0) {
            val projects = sg.getMyProjects(token)
            val clients = sg.getClients(token)
            val types = sg.getTypes(token)
            val depts = sg.getDepartments(token)
            depts.enqueue(object : Callback<List<MyProjectsContent.DepartmentItem>> {
                override fun onResponse(call: Call<List<MyProjectsContent.DepartmentItem>>,
                                        response: Response<List<MyProjectsContent.DepartmentItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        MyProjectsContent.DEPTS.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("ERRBODY", response.errorBody()!!.string())
                            Toast.makeText(context, response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MyProjectsContent.DepartmentItem>>?, t: Throwable?) {
                    Toast.makeText(context, "We're having trouble retrieving types.", Toast.LENGTH_LONG).show()
                    Log.d("DownloadFail", t.toString())
                }
            })
            types.enqueue(object : Callback<List<MyProjectsContent.TypeItem>> {
                override fun onResponse(call: Call<List<MyProjectsContent.TypeItem>>,
                                        response: Response<List<MyProjectsContent.TypeItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        MyProjectsContent.TYPES.addAll(response.body()!!)
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("ERRBODY", response.errorBody()!!.string())
                            Toast.makeText(context, response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MyProjectsContent.TypeItem>>?, t: Throwable?) {
                    Toast.makeText(context, "We're having trouble retrieving types.", Toast.LENGTH_LONG).show()
                    Log.d("DownloadFail", t.toString())
                }
            })
            clients.enqueue(object : Callback<List<MyProjectsContent.ClientItem>> {
                override fun onResponse(call: Call<List<MyProjectsContent.ClientItem>>,
                                        response: Response<List<MyProjectsContent.ClientItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        MyProjectsContent.CLIENTS.addAll(response.body()!!)
                        val ft = fragmentManager?.beginTransaction()
                        ft?.detach(this@MyProjectsFragment)?.attach(this@MyProjectsFragment)?.commit()
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("ERRBODY", response.errorBody()!!.string())
                            Toast.makeText(context, response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MyProjectsContent.ClientItem>>?, t: Throwable?) {
                    Toast.makeText(context, "We're having trouble retrieving clients.", Toast.LENGTH_LONG).show()
                    Log.d("DownloadFail", t.toString())
                }
            })
            projects.enqueue(object : Callback<List<MyProjectsContent.ProjectItem>> {
                override fun onResponse(call: Call<List<MyProjectsContent.ProjectItem>>,
                                        response: Response<List<MyProjectsContent.ProjectItem>>) {
                    try {
                        Log.d("DownloadData", response.body()!!.toString())
                        MyProjectsContent.ITEMS.addAll(response.body()!!)
                        val ft = fragmentManager?.beginTransaction()
                        ft?.detach(this@MyProjectsFragment)?.attach(this@MyProjectsFragment)?.commit()
                    } catch (e: NullPointerException) {
                        try {
                            Log.d("ERRBODY", response.errorBody()!!.string())
                            Toast.makeText(context, response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                        } catch (f: IOException) {
                            Log.d("IOException", f.message)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MyProjectsContent.ProjectItem>>, t: Throwable) {
                    Toast.makeText(context, "We're having trouble retrieving projects.", Toast.LENGTH_LONG).show()
                    Log.d("DownloadFail", t.toString())
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_myprojects_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            view.adapter = MyMyProjectsRecyclerViewAdapter(MyProjectsContent.ITEMS, mListener)
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
        fun onListFragmentInteraction(item: MyProjectsContent.ProjectItem)
    }

    companion object {
        private const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int): MyProjectsFragment {
            val fragment = MyProjectsFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
