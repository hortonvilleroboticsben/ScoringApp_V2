package com.hortonvillerobotics.scoringapp_v2;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

public class PushToGoogleService extends IntentService {
    public PushToGoogleService() {
        super("PushToGoogleService");
    }

    private static String TAG = "PushToGoogleService";
    private final PushToGoogleService me = this;

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        Cursor c = Database.getInstance().database.query("Matches", null, null, null, null, null, null);
        while (c.getPosition() < c.getCount()-1) {
            c.moveToNext();
            try {
                SharedPreferences pM = PreferenceManager.getDefaultSharedPreferences(this);
                String sheetID = pM.getString(getString(R.string.sheetID), "");


                JSONObject postDataParams = new JSONObject();
                String id = sheetID;

                String[] parameters = {"matchID","matchNumber", "teamNumber", "moveFoundation", "skyStones", "regStones", "parkedBridge", "stonesBridge", "onFoundation", "tallestTower", "capped","retFoundation","parkedBuilding"};

                for (int i = 0; i < parameters.length; i++){
                    postDataParams.put(parameters[i], c.getString(i));
                }
                postDataParams.put("id", id);

                URL url = new URL("https://script.google.com/macros/s/AKfycbxL_zBpL-iHBCJZ7Vz3qc4ZOOvEPMP_38Fk0vsTKCdxHc3HaFkb/dev?"+getPostDataString(postDataParams));

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                //conn.setDoInput(true);
                //conn.setDoOutput(true);

//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//                Log.d(TAG,getPostDataString(postDataParams));
//                writer.flush();
//                writer.close();
//                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                //Log.d(TAG,""+sb);
                in.close();

                   Database.getInstance().database.execSQL("DELETE FROM Matches WHERE id='"+c.getString(0)+"'");


                }else{
                    ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
                    s.schedule(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("PushToGoogleService","DIDNT WORK, else");
                            Toast.makeText(getApplicationContext(), "DIDNT WORK", Toast.LENGTH_SHORT).show();
                            startService(new Intent(me, me.getClass()));
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            } catch (Exception e) {
                Log.d("E/PushToGoogleService",e.getMessage());
                ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
                s.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("PushToGoogleService","DIDNT WORK, catch");
                        Toast.makeText(getApplicationContext(), "DIDNT WORK", Toast.LENGTH_SHORT).show();
                        startService(new Intent(me, me.getClass()));
                    }
                },10, TimeUnit.SECONDS);
            }


        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}