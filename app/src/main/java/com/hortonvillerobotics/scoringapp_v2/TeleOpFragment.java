package com.hortonvillerobotics.scoringapp_v2;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeleOpFragment extends TitledFragment {

    public static Spinner endPos;
    public static TextView goldNum, silverNum, depotNum;
    public int goldNumber = 0;
    public int silverNumber = 0;
    public int depotNumber = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);


        final Button goldDown = v.findViewById(R.id.goldDecrease);
        goldDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goldNumber <= 0){
                    goldNumber = 0;
                    goldNum.setText(goldNumber+"");
                } else {
                    goldNum.setText(--goldNumber + "");
                }
            }
        });

        Button goldUp = v.findViewById(R.id.goldIncrease);
        goldUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goldNumber >= 50) {
                    goldNumber = 50;
                    goldNum.setText(goldNumber + "");
                } else {
                    goldNum.setText(++goldNumber + "");
                }
            }
        });



        Button silverDown = v.findViewById(R.id.silverDecrease);
        silverDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(silverNumber <= 0){
                    silverNumber = 0;
                    silverNum.setText(silverNumber+"");
                } else {
                    silverNum.setText(--silverNumber + "");
                }
            }
        });

        Button silverUp = v.findViewById(R.id.silverIncrease);
        silverUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (silverNumber >= 50) {
                    silverNumber = 50;
                    silverNum.setText(silverNumber + "");
                } else {
                    silverNum.setText(++silverNumber + "");
                }
            }
        });

        Button depotDown = v.findViewById(R.id.depotDecrease);
        silverDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(depotNumber <= 0){
                    depotNumber = 0;
                    depotNum.setText(depotNumber+"");
                } else {
                    depotNum.setText(--silverNumber + "");
                }
            }
        });

        Button depotUp = v.findViewById(R.id.depotIncrease);
        silverUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (silverNumber >= 50) {
                    silverNumber = 50;
                    depotNum.setText(silverNumber + "");
                } else {
                    depotNum.setText(++silverNumber + "");
                }
            }
        });

        endPos = v.findViewById(R.id.EndgameOptions);

        goldNum = v.findViewById(R.id.gold_count);
        silverNum = v.findViewById(R.id.silver_count);
        depotNum = v.findViewById(R.id.depot_count);

        return v;
    }

    @Override
    String getPageTitle() {
        return "TeleOp";
    }

}
