package com.hortonvillerobotics.scoringapp_v2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class TeleOpFragment extends TitledFragment{

    public static SeekBar gold;
    public static SeekBar silver;
    public static SeekBar depot;
    public static Spinner endPos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);

        gold = (SeekBar)v.findViewById(R.id.GoldFill);
        FillUpImage f = (FillUpImage)v.findViewById(R.id.GoldFillUpImage);
        gold.setMax(10);
        f.pairSeekBar(gold);

        silver = (SeekBar)v.findViewById(R.id.SilverFill);
        f = (FillUpImage)v.findViewById(R.id.SilverFillUpImage);
        silver.setMax(10);
        f.pairSeekBar(silver);

        depot = (SeekBar)v.findViewById(R.id.DepotFill);
        f = (FillUpImage)v.findViewById(R.id.DepotFillUpImage);
        depot.setMax(10);
        f.pairSeekBar(depot);

        endPos = v.findViewById(R.id.EndgameOptions);

        return v;
    }

    @Override
    String getPageTitle() {
        return "TeleOp";
    }


}
