package com.hortonvillerobotics.scoringapp_v2;


import android.Manifest;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.service.quicksettings.Tile;
import android.support.annotation.IdRes;
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
    public String[] results = new String[12];
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

            }//ok
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String sheetID = preferences.getString("sheetID",null);
        if(sheetID==null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("sheetID",getString(R.string.DefaultSheetID));
            editor.commit();
        }


        //lp
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        getSupportActionBar().setTitle("Hortonville Robotics Scoring App");
        getSupportActionBar().setSubtitle("Team #6981");
        submit = (Button)findViewById(R.id.submitButton);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewPager.setCurrentItem(2,true);

                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Submit Alert");
                alertDialog.setMessage("Are you sure you want to submit?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                results[0] = ""+StartFragment.matchNumber.getText();
                                results[1] = ""+StartFragment.teamNumber.getText();
                                results[2] = ""+AutoFragment.foundation.isChecked();
                                results[3] = ""+AutoFragment.parked.isChecked();
                                results[4] = "" + ((AutoFragment.skyStone!=null) ? AutoFragment.skyStone.getText():0);
                                results[5] = "" + ((AutoFragment.regStone!=null) ? AutoFragment.regStone.getText():0);
                                results[6] = "" + ((TeleOpFragment.underBridge!=null) ? TeleOpFragment.underBridge.getText() : 0);
                                results[7] = "" + ((TeleOpFragment.onFoundation!=null) ? TeleOpFragment.onFoundation.getText(): 0);
                                results[8] = "" + ((TeleOpFragment.tallestHeight!=null) ? TeleOpFragment.tallestHeight.getText() : 0);
                                results[9] = ""+TeleOpFragment.capped.isChecked();
                                results[10] = ""+TeleOpFragment.foundationMoved.isChecked();
                                results[11] = ""+TeleOpFragment.buildingPark.isChecked();


                                if(results[0]!="" && results[1]!="") {
                                    Database.getInstance().commitToDatabase(results);
                                    AutoFragment.skyNum = 0;
                                    AutoFragment.regNum = 0;
                                    TeleOpFragment.bridgeNum = 0;
                                    TeleOpFragment.heightNum = 0;
                                    TeleOpFragment.foundationNum = 0;
                                    Intent serviceIntent = new Intent(MainActivity.this, PushToGoogleService.class);
                                    startService(serviceIntent);
                                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(getIntent());
                                } else {
                                    Toast.makeText(getApplicationContext(),"Please Fill in Match and Team number",Toast.LENGTH_LONG).show();
                                    mViewPager.setCurrentItem(0, true);
                                }

                            }
                        });
                alertDialog.show();

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
//            TitledFragment frag = null;
//            switch(getArguments().getInt(ARG_SECTION_NUMBER)-1){
//                case START:
//                    frag = new StartFragment();
//                    views[START] = views[START] == null ? frag : views[START];
//                    break;
//                case AUTO:
//                    frag = new AutoFragment();
//                    views[AUTO] = views[AUTO] == null ? frag : views[AUTO];
//                    break;
//                case TELEOP:
//                    frag = new TeleOpFragment();
//                    views[TELEOP] = views[TELEOP] == null ? frag : views[TELEOP];
//                    break;
//            }
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

            return PlaceholderFragment.newInstance(position+1);
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
