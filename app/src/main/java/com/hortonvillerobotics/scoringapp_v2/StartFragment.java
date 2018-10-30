package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

public class StartFragment extends TitledFragment {

    public static EditText matchNumber;
    public static Spinner teams;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        teams = v.findViewById(R.id.TeamName);
        return v;
    }

    @Override
    String getPageTitle() {
        return "Start";
    }
}
