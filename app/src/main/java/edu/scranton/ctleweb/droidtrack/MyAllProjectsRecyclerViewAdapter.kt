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

/**
 * [RecyclerView.Adapter] that can display a [ProjectItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
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
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
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
        val mIdView: TextView
        val mContentView: TextView
        val mClientView: TextView
        var mItem: ProjectItem? = null

        init {
            mIdView = mView.findViewById<View>(R.id.id) as TextView
            mContentView = mView.findViewById<View>(R.id.content) as TextView
            mClientView = mView.findViewById<View>(R.id.client) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
