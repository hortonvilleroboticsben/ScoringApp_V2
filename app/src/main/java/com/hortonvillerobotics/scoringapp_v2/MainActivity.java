package com.hortonvillerobotics.scoringapp_v2;


import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    static TitledFragment[] views = {new StartFragment(), new AutoFragment(), new TeleOpFragment()};
    private ViewPager mViewPager;
    public String[] results = new String[11];
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        getSupportActionBar().setTitle("Hortonville Robotics Scoring App");
        getSupportActionBar().setSubtitle("Team #6981");
        submit = (Button)findViewById(R.id.SubmitButton);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: ENTER ALL POSSIBLE PARAMETERS THAT ARE IN THE GOOGLE SCRIPT
                results[1] = ""+StartFragment.matchNumber.getText();
                results[2] = StartFragment.teams.getSelectedItem().toString();
                results[3] = ""+AutoFragment.hanging.isChecked()+"";
                results[4] = ""+AutoFragment.goldCube.isChecked();
                results[5] = AutoFragment.teamIcon.isChecked()+"";
                results[6] = AutoFragment.parkedCrater.isChecked()+"";
                try {
                    results[7] = "" + (double) TeleOpFragment.gold.getProgress() / TeleOpFragment.gold.getMax();
                }catch (Exception e){
                    results[7] = "0";
                }
                try{
                    results[8] = ""+(double)TeleOpFragment.silver.getProgress()/TeleOpFragment.silver.getMax();
                }catch (Exception e){
                    results[8] = "0";
                }
                try {
                    results[9] = "" + (double) TeleOpFragment.depo.getProgress() / TeleOpFragment.depo.getMax();
                } catch(Exception e){
                    results[9] = "0";
                }
                results[10] = ""+TeleOpFragment.endPos.getSelectedItem().toString();
                Database.getInstance(getApplicationContext()).commitToDatabase(results);

                Intent serviceIntent = new Intent("PushToGoogleService");
                startService(serviceIntent);
            }

        }   );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = views[getArguments().getInt(ARG_SECTION_NUMBER)-1].onCreateView(inflater,container,savedInstanceState);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return views[position].getPageTitle();
        }
    }
}
