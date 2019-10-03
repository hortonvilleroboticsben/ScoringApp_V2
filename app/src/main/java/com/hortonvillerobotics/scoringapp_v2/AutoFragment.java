package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class AutoFragment extends TitledFragment {

    public static Switch foundation, parked;
    public static TextView skyStone,regStone;
    public int skyNum = 0,regNum = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        foundation = (Switch)v.findViewById(R.id.FoundationSwitch);
        parked = (Switch)v.findViewById(R.id.ParkedBridge);

        Button skyDown = v.findViewById(R.id.skyDecrease);
        skyDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skyNum <= 0){
                    skyNum = 0;
                    skyStone.setText("0");
                } else {
                    skyStone.setText(--skyNum + "");
                }
            }
        });

        Button skyUp = v.findViewById(R.id.skyIncrease);
        skyUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skyNum >= 2){
                    skyNum = 2;
                    skyStone.setText("2");
                } else {
                    skyStone.setText(++skyNum + "");
                }
            }
        });

        Button regDown = v.findViewById(R.id.stoneDecrease);
        regDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regNum <= 0){
                    regNum = 0;
                    regStone.setText("0");
                } else {
                    regStone.setText(--regNum + "");
                }
            }
        });

        Button regUp = v.findViewById(R.id.stoneIncrease);
        regUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regNum >= 50){
                    regNum = 50;
                    regStone.setText("50");
                } else {
                    regStone.setText(++regNum + "");
                }
            }
        });

        skyStone = v.findViewById(R.id.sky_count);
        regStone = v.findViewById(R.id.stone_count);

        return v;
    }

    @Override
    String getPageTitle() {
        return "Autonomous";
    }

}
