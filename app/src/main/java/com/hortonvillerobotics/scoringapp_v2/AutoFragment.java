package com.hortonvillerobotics.scoringapp_v2;

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
    public int skyNum,regNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        foundation = (Switch)v.findViewById(R.id.FoundationSwitch);
        parked = (Switch)v.findViewById(R.id.ParkedBridge);
        skyNum = 0;
        regNum = 0;

        Button skyDown = v.findViewById(R.id.skyDecrease);
        skyDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skyNum <= 0){
                    skyNum = 0;
                    skyStone.setText(skyNum+"");
                } else {
                    skyStone.setText(--skyNum + "");
                }
            }
        });

        Button skyUp = v.findViewById(R.id.skyIncrease);
        skyUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skyNum >= 50){
                    skyNum = 50;
                    skyStone.setText(skyNum+"");
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
                    regStone.setText(regNum+"");
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
                    regStone.setText(regNum+"");
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
