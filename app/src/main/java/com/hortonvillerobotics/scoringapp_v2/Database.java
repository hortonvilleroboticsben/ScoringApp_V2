package com.hortonvillerobotics.scoringapp_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Environment;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Currency;
import java.util.HashMap;

public class Database {
    private static Database db;
    public SQLiteDatabase database;

    private Database(Context c) {

        String absStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String relStorageDir = Environment.getExternalStorageDirectory().getPath();

        database = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory().getPath() + "/matchRecords.db", null);

        try {
            database.execSQL("CREATE TABLE Matches (id VARCHAR(255), matchNum VARCHAR(255), teamNum VARCHAR(255), hanging VARCHAR(255), autoDebris VARCHAR(255), icon VARCHAR(255), autoCrater VARCHAR(255), gold VARCHAR(255), silver VARCHAR(255), depot VARCHAR(255), endPos VARCHAR(255))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance(Context c) {
        return db == null ? (db = new Database(c)) : db;
    }

    public void commitToDatabase(String[] args) {
        int entryHash = (("" + args[0]) + args[1] + System.currentTimeMillis()).hashCode();
        String elementEntry = "('";
        for (String s : args) {
            elementEntry += s + "','";
        }
        elementEntry = elementEntry.substring(0, elementEntry.length() - 2) + ")";
        database.execSQL("INSERT INTO Matches (id,matchNum,teamNum,hanging,autoDebris,icon,autoCrater,gold,silver,depot,endPos) VALUES " + elementEntry);

    }
}
