package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.app.Presentation;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

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

public class TeleOpFragment extends TitledFragment{

    public static SeekBar gold;
    public static SeekBar silver;
    public static Switch inCrater;
    public static Switch comCrater;
    public static Switch endHanging;
    public static SeekBar depo;
    public static Spinner endPos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);

        SeekBar s = (SeekBar)v.findViewById(R.id.GoldFill);
        FillUpImage f = (FillUpImage)v.findViewById(R.id.GoldFillUpImage);
        s.setMax(10);
        f.pairSeekBar(s);

        s = (SeekBar)v.findViewById(R.id.SilverFill);
        f = (FillUpImage)v.findViewById(R.id.SilverFillUpImage);
        s.setMax(10);
        f.pairSeekBar(s);

        gold = v.findViewById(R.id.GoldFill);
        silver = v.findViewById(R.id.SilverFill);
        depo = v.findViewById(R.id.DepotFill);
        endPos = v.findViewById(R.id.EndgameOptions);

        return v;
    }

    @Override
    String getPageTitle() {
        return "TeleOp";
    }
}
