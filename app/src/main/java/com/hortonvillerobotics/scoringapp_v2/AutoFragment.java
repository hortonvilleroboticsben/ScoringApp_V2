package com.hortonvillerobotics.scoringapp_v2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class AutoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_auto, container, false);

        SeekBar s = (SeekBar)v.findViewById(R.id.seekBar);
        FillUpImage f = (FillUpImage)v.findViewById(R.id.fillUpImage);

        f.pairSeekBar(s);

        return v;
    }
}
