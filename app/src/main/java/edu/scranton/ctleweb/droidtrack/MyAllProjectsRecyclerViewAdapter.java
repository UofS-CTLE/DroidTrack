package edu.scranton.ctleweb.droidtrack;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.scranton.ctleweb.droidtrack.AllProjectsFragment.OnListFragmentInteractionListener;
import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent.ProjectItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ProjectItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAllProjectsRecyclerViewAdapter extends RecyclerView.Adapter<MyAllProjectsRecyclerViewAdapter.ViewHolder> {

    private final List<ProjectItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyAllProjectsRecyclerViewAdapter(List<ProjectItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_allprojects, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).title);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    Log.d("Click", "Clicked.");
                    Log.d("ClickedOn", v.toString());
                    TextView tv = (TextView)((LinearLayout)v).getChildAt(0);
                    String text = tv.getText().toString();
                    Log.d("Content", text);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mClientView;
        public ProjectItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mClientView = (TextView) view.findViewById(R.id.client);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
