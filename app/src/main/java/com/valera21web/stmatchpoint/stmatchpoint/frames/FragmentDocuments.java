package com.valera21web.stmatchpoint.stmatchpoint.frames;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valera21web.stmatchpoint.stmatchpoint.MainActivity;
import com.valera21web.stmatchpoint.stmatchpoint.R;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentDocuments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDocuments extends Fragment
{
    public static final int PAGE_ID = 4;

    public static FragmentDocuments newInstance() {
        FragmentDocuments fragment = new FragmentDocuments();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_documents, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity)
                .onSectionAttached(
                        activity.getString(R.string.page_documents),
                        new String[]{
                                activity.getString(R.string.page_documents_statute),
                                activity.getString(R.string.page_documents_protocols)
                        }
                );
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
