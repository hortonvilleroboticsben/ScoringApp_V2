package com.hortonvillerobotics.scoringapp_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class TeleOpFragment extends TitledFragment {

    public static Switch capped,foundationMoved,buildingPark;
    public static TextView underBridge, onFoundation, tallestHeight;
    public int bridgeNum, foundationNum, heightNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);

        capped = (Switch)v.findViewById(R.id.Capped);
        foundationMoved = (Switch)v.findViewById(R.id.FoundationMove);
        buildingPark = (Switch)v.findViewById(R.id.ParkedBuilding);

        bridgeNum = 0;
        foundationNum = 0;
        heightNum = 0;

        Button bridgeDown = v.findViewById(R.id.StoneUnderBridgeDecrease);
        bridgeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bridgeNum <= 0){
                    bridgeNum = 0;
                    underBridge.setText(bridgeNum+"");
                } else {
                    underBridge.setText(--bridgeNum+ "");
                }
            }
        });

        Button bridgeUp = v.findViewById(R.id.StoneUnderBridgeIncrease);
        bridgeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bridgeNum >= 50){
                    bridgeNum = 50;
                    underBridge.setText(bridgeNum+"");
                } else {
                    underBridge.setText(++bridgeNum+ "");
                }
            }
        });

        Button foundDown = v.findViewById(R.id.BlocksDecrease);
        foundDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bridgeNum <= 0){
                    bridgeNum = 0;
                    underBridge.setText(bridgeNum+"");
                } else {
                    underBridge.setText(--bridgeNum+ "");
                }
            }
        });

        Button foundUp = v.findViewById(R.id.BlocksIncrease);
        foundUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foundationNum >= 50){
                    foundationNum = 50;
                    onFoundation.setText(foundationNum+"");
                } else {
                    onFoundation.setText(++foundationNum+ "");
                }
            }
        });

        Button heightDown = v.findViewById(R.id.TowerDecrease);
        heightDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(heightNum <= 0){
                    heightNum = 0;
                    tallestHeight.setText(heightNum+"");
                } else {
                    tallestHeight.setText(--heightNum+ "");
                }
            }
        });

        Button heightUp = v.findViewById(R.id.TowerIncrease);
        heightUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(heightNum >= 50){
                    heightNum = 50;
                    tallestHeight.setText(heightNum+"");
                } else {
                    tallestHeight.setText(++heightNum+ "");
                }
            }
        });

        onFoundation = v.findViewById(R.id.Blocks_count);
        underBridge = v.findViewById(R.id.StoneUnderBridge_count);
        tallestHeight = v.findViewById(R.id.Tower_count);

        return v;
    }

    @Override
    String getPageTitle() {
        return "TeleOp";
    }

}
