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
 * Use the {@link com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentSponsorsAndPartners#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSponsorsAndPartners extends Fragment
{
    public static final int PAGE_ID = 6;

    public static FragmentSponsorsAndPartners newInstance() {
        FragmentSponsorsAndPartners fragment = new FragmentSponsorsAndPartners();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentSponsorsAndPartners() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sponsors_and_partners, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity)
                .onSectionAttached(
                        activity.getString(R.string.page_sponsors_and_partners),
                        new String[]{}
                );
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
