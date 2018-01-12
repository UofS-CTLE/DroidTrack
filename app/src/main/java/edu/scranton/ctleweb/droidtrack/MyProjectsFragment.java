package edu.scranton.ctleweb.droidtrack;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MyProjectsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyProjectsFragment() {
    }

    @SuppressWarnings("unused")
    public static MyProjectsFragment newInstance(int columnCount) {
        MyProjectsFragment fragment = new MyProjectsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        Bundle arg = getArguments();
        String token = arg.getString("token", "0");

        ProjtrackService sg = ServiceGenerator.createService(ProjtrackService.class, token);

        if (MyProjectsContent.ITEMS.size() == 0) {
            Call<List<MyProjectsContent.ProjectItem>> projects = sg.getMyProjects(token);
            projects.enqueue(new Callback<List<MyProjectsContent.ProjectItem>>() {
                @Override
                public void onResponse(Call<List<MyProjectsContent.ProjectItem>> call,
                                       Response<List<MyProjectsContent.ProjectItem>> response) {
                    try {
                        Log.d("DownloadData", response.body().toString());
                        MyProjectsContent.ITEMS.addAll(response.body());
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(MyProjectsFragment.this).attach(MyProjectsFragment.this).commit();
                    } catch (NullPointerException e) {
                        try {
                            Log.d("ERRBODY", response.errorBody().string());
                        } catch (IOException f) {
                            Log.d("IOException", f.getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<MyProjectsContent.ProjectItem>> call, Throwable t) {
                    Toast.makeText(getContext(), "We're having trouble retrieving projects.", Toast.LENGTH_LONG).show();
                    Log.d("DownloadFail", t.toString());
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprojects_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMyProjectsRecyclerViewAdapter(MyProjectsContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MyProjectsContent.ProjectItem item);
    }
}
