package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StartFragment extends TitledFragment {

    public static EditText matchNumber;
    public static EditText teamNumber;
    public static View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_start, container, false);
        teamNumber = v.findViewById(R.id.TeamNumber);
        matchNumber = v.findViewById(R.id.editText2);
        return v;
    }

    @Override
    String getPageTitle() {
        return "Start";
    }


}
