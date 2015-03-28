package com.valera21web.stmatchpoint.stmatchpoint;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;

import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentAboutUs;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentClubs;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentContact;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentDocuments;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentGallery;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentNews;
import com.valera21web.stmatchpoint.stmatchpoint.frames.FragmentSponsorsAndPartners;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ActionBar.OnNavigationListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private String[] subPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        getFragmentManager().beginTransaction()
                .replace(R.id.container, getFrame(position))
                .commit();
    }

    public void onSectionAttached(String _title, String[] _subPages) {
        subPages =_subPages;
        mTitle = _title;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(subPages.length > 0) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_item, subPages
                    );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            actionBar.setListNavigationCallbacks(adapter, this);
        } else
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {

        return false;
    }

    private Fragment getFrame(int position)
    {
        ++position;
        Fragment fragment;
        switch (position) {
            case FragmentAboutUs.PAGE_ID:
                fragment = FragmentAboutUs.newInstance();
                break;
            case FragmentNews.PAGE_ID:
                fragment = FragmentNews.newInstance();
                break;
            case FragmentClubs.PAGE_ID:
                fragment = FragmentClubs.newInstance();
                break;
            case FragmentDocuments.PAGE_ID:
                fragment = FragmentDocuments.newInstance();
                break;
            case FragmentGallery.PAGE_ID:
                fragment = FragmentGallery.newInstance();
                break;
            case FragmentSponsorsAndPartners.PAGE_ID:
                fragment = FragmentSponsorsAndPartners.newInstance();
                break;
            case FragmentContact.PAGE_ID:
                fragment = FragmentContact.newInstance();
                break;
            default:
                fragment = FragmentAboutUs.newInstance();
        }
        return fragment;
    }
}
