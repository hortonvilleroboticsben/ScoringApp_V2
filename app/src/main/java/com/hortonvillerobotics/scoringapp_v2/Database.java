package com.hortonvillerobotics.scoringapp_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Currency;
import java.util.HashMap;

public class Database {
    private static Database db;
    public SQLiteDatabase database;

    private Database() {

        database = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getPath() + "/matchRecords.db", null);

        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS Matches (id VARCHAR(255), matchNum VARCHAR(255), teamNum VARCHAR(255), moveFoundation VARCHAR(255), skyStones VARCHAR(255), regStones VARCHAR(255), parkedBridge VARCHAR(255), stonesBridge VARCHAR(255), onFoundation VARCHAR(255), tallestHeight VARCHAR(255), capped VARCHAR(255), retFoundation VARCHAR(255),parkedBuilding VARCHAR(255))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return db == null ? (db = new Database()) : db;
    }

    public void commitToDatabase(String[] args) {
        int entryHash = (("" + args[0]) + args[1]).hashCode();
        String elementEntry = "('"+entryHash+"','";
        for (String s : args) {
            elementEntry += s + "','";
        }
        elementEntry = elementEntry.substring(0, elementEntry.length() - 2) + ")";
//        Log.d("Database",elementEntry);
        database.execSQL("INSERT INTO Matches (id,matchNum,teamNum,moveFoundation,skyStones,regStones,parkedBridge,stonesBridge,onFoundation,tallestHeight,capped,retFoundation,parkedBuilding) VALUES " + elementEntry);

    }
}
