package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class TeleOpFragment extends TitledFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);

        SeekBar s = (SeekBar)v.findViewById(R.id.GoldFill);
        FillUpImage f = (FillUpImage)v.findViewById(R.id.GoldFillUpImage);
        s.setMax(10);
        f.pairSeekBar(s);

        s = (SeekBar)v.findViewById(R.id.SilverFill);
        f = (FillUpImage)v.findViewById(R.id.SilverFillUpImage);
        s.setMax(10);
        f.pairSeekBar(s);


        Log.d("TeleOpFragment", "Val is: " + Database.getInstance().val);
        return v;
    }

    @Override
    String getPageTitle() {
        return "TeleOp";
    }
}
