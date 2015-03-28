package com.valera21web.stmatchpoint.stmatchpoint.frames;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valera21web.stmatchpoint.stmatchpoint.MainActivity;
import com.valera21web.stmatchpoint.stmatchpoint.R;
import com.valera21web.stmatchpoint.stmatchpoint.libs.APIConnect;
import org.json.JSONObject;

/**
 * A simple {@link android.app.Fragment} subclass.
 * Use the {@link FragmentAboutUs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAboutUs extends Fragment
{
    public static final int PAGE_ID = 1;
    public TextView textStatus;

    public static FragmentAboutUs newInstance() {
        FragmentAboutUs fragment = new FragmentAboutUs();
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
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        textStatus = (TextView) view.findViewById(R.id.textView);
        (new APIConnect(view, "http://stmatchpoint.org/ajax/admin_auth.php", "lg=&psw=") {
            public void init(JSONObject result) {
                textStatus.setText("["+result+"]");
            }
        }).execute();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity)
                .onSectionAttached(
                        activity.getString(R.string.page_about_us),
                        new String[]{
                                activity.getString(R.string.page_about_us_administration),
                                activity.getString(R.string.page_about_us_about_us),
                                activity.getString(R.string.page_about_us_support_us)
                        }
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected String getStringResurse(int id) {
        return getResources().getString(id);
    }
}
