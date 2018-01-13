package edu.scranton.ctleweb.droidtrack

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import edu.scranton.ctleweb.droidtrack.AllProjectsFragment.OnListFragmentInteractionListener
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent.ProjectItem

class MyAllProjectsRecyclerViewAdapter(private val mValues: List<ProjectItem>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyAllProjectsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_allprojects, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].id
        holder.mContentView.text = mValues[position].title

        holder.mView.setOnClickListener { v ->
            if (null != mListener) {
                mListener.onListFragmentInteraction(holder.mItem!!)
                Log.d("Click", "Clicked.")
                Log.d("ClickedOn", v.toString())
                val tv = (v as LinearLayout).getChildAt(0) as TextView
                val text = tv.text.toString()
                Log.d("Content", text)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById<View>(R.id.id) as TextView
        val mContentView: TextView = mView.findViewById<View>(R.id.content) as TextView
        var mItem: ProjectItem? = null

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
