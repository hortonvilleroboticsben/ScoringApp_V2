package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;

public class AutoFragment extends TitledFragment {

    public static Switch hanging;
    public static Switch goldCube;
    public static Switch teamIcon;
    public static Switch parkedCrater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        hanging = (Switch)v.findViewById(R.id.HangingSwitch);
        goldCube = (Switch)v.findViewById(R.id.CubeSwitch);
        teamIcon = (Switch)v.findViewById(R.id.IconSwitch);
        parkedCrater = (Switch)v.findViewById(R.id.ParkSwitch);

        return v;
    }

    @Override
    String getPageTitle() {
        return "Autonomous";
    }

    @Override
    void reset() {

    }
}
