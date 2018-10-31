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
    public String[] results = new String[10];
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
                results[0] = ""+StartFragment.matchNumber;
                results[1] = StartFragment.teams.getSelectedItem().toString();
                results[2] = AutoFragment.hanging.isChecked()+"";
                results[3] = AutoFragment.goldCube.isChecked()+"";
                results[4] = AutoFragment.teamIcon.isChecked()+"";
                results[5] = AutoFragment.parkedCrater.isChecked()+"";
                results[6] = (double)TeleOpFragment.gold.getProgress()/TeleOpFragment.gold.getMax()+"";
                results[7] = (double)TeleOpFragment.silver.getProgress()/TeleOpFragment.silver.getMax()+"";
                results[8] = "this is suppose to be the depo score";
                results[9] = "this is suppose to be the end position for the robot";
                Database.getInstance().commitToDatabase(results);

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

//    public class SendRequest extends AsyncTask<String, Void, String> {
//
//
//        protected void onPreExecute(){}
//
//        protected String doInBackground(String... arg0) {
//
//            try{
//
//                String gie = pM.getString(getString(R.string.gie), "");
//
//                URL url = new URL("https://script.google.com/macros/d/MAcVajrwTLWBuILd4kSWqPWKLfJIYyY8Y/edit?uiv=2&mid=ACjPJvHRsK6LSZfrzwYj359tvH57SYd9ylX3sg2J1vr9T9pFHMHwu_utcDL6wJAdqwvn8PTi0eiwJNH_MwX0tI2Dj8d5ymIF7IfqIZ8dNi0hYVcE3N3ucNXgKcSrPaxbNPqqHP1PcyzTLEY");
//                JSONObject postDataParams = new JSONObject();
//                String id= gie;//"14DoM0-EFK_oKTBs1sgPWpb5_Lb9PVxKGNuI44nqNT3Y";
//
//                String[] parameters = {"team","hanging","teamIcon","goldCube","parkedCrater",
//                "goldTele","silverTele","inCrater","completeCrater","endHanging"};
//
//                for(int i = 0; i < parameters.length; i++) {
////                    postDataParams.put(parameters[i],results[i]);
//                }
//                postDataParams.put("id",id);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuffer sb = new StringBuffer("");
//                    String line="";
//
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                }
//                else {
//                    return new String("false : "+responseCode);
//                }
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
//        }
//        @Override
//        protected void onPostExecute(String result) {
//            Toast.makeText(getApplicationContext(), "SUCCESS",
//                    Toast.LENGTH_LONG).show();
//
//            finish();
//            startActivity(getIntent());
//        }
//    }
//
//    public String getPostDataString(JSONObject params) throws Exception {
//
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//
//        Iterator<String> itr = params.keys();
//
//        while(itr.hasNext()){
//
//            String key= itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
//        return result.toString();
//    }

}
