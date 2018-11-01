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

import javax.net.ssl.HttpsURLConnection;

public class PushToGoogleService extends IntentService {
    public PushToGoogleService() {
        super("PushToGoogleService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Cursor c = Database.getInstance(getApplicationContext()).database.query("Matches", null, null, null, null, null, null);
        while (c.getPosition() < c.getCount()) {
            c.moveToNext();
            try {
                SharedPreferences pM = PreferenceManager.getDefaultSharedPreferences(this);
                String sheetID = pM.getString(getString(R.string.sheetID), "");

                URL url = new URL("https://script.google.com/a/hortonvillerobotics.com/macros/s/AKfycbzSukoOXFOX1jKz3rp7MDyrG_czuIuk6zeoA-3iNLy1AH4KD58/exec");
                JSONObject postDataParams = new JSONObject();
                String id = "1Fu9qCjgzlClW2OxjeJrEjVGu9MaaW93NaMcrojPdQpQ";

                String[] parameters = {"matchNumber", "teamNumber", "Hanging", "goldCube", "teamIcon"
                        , "parkedCrater", "goldFilled", "silverFilled", "depoScore", "endPosition"};

                for (int i = 0; i < parameters.length; i++) {
                    postDataParams.put(parameters[i], c.getString(i));
                }
                postDataParams.put("id", id);


                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();


                }
            } catch (Exception e) {
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