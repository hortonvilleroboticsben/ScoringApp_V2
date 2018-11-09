package com.hortonvillerobotics.scoringapp_v2;


import android.Manifest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;


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
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        }

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

                results[0] = ""+StartFragment.matchNumber.getText();
                results[1] = StartFragment.teams.getSelectedItem().toString();
                results[2] = ""+AutoFragment.hanging.isChecked()+"";
                results[3] = ""+AutoFragment.goldCube.isChecked();
                results[4] = AutoFragment.teamIcon.isChecked()+"";
                results[5] = AutoFragment.parkedCrater.isChecked()+"";
                results[6] = "" + ((TeleOpFragment.gold!=null) ? (double) TeleOpFragment.gold.getProgress()/TeleOpFragment.gold.getMax(): 0);
                results[7] = "" + ((TeleOpFragment.silver!=null) ? (double) TeleOpFragment.silver.getProgress()/TeleOpFragment.silver.getMax(): 0);
                results[8] = "" + ((TeleOpFragment.depot !=null) ? (double) TeleOpFragment.depot.getProgress()/TeleOpFragment.depot.getMax(): 0);
                results[9]= (TeleOpFragment.endPos!=null) ? TeleOpFragment.endPos.getSelectedItem().toString() : "Nothing";

                Database.getInstance().commitToDatabase(results);

                Intent serviceIntent = new Intent(MainActivity.this, PushToGoogleService.class);
                startService(serviceIntent);

                Toast.makeText(getApplicationContext(),"SUCCESS",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
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
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
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
