package com.hortonvillerobotics.scoringapp_v2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeleOpFragment extends TitledFragment{

    public static SeekBar gold;
    public static SeekBar silver;
    public static SeekBar depot;
    public static Spinner endPos;
    public static TextView goldRowNum;
    public static TextView silverRowNum;
    public static TextView depotNum;
    public static int goldRows;
    public static int silverRows;

    static FillUpImage f;
    static FillUpImage f0;
    static FillUpImage f1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tele_op, container, false);
        gold = (SeekBar) v.findViewById(R.id.GoldFill);
        f = (FillUpImage) v.findViewById(R.id.GoldFillUpImage);
        goldRowNum = (TextView) v.findViewById(R.id.goldRowNum);
        gold.setMax(7);

        gold.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                goldRows = gold.getProgress();
                goldRowNum.setText(goldRows + " Rows");
                f.changePercentFilled((double) progress / seekBar.getMax());


            }
        });

        silver = (SeekBar) v.findViewById(R.id.SilverFill);
        f0 = (FillUpImage) v.findViewById(R.id.SilverFillUpImage);
        silverRowNum = (TextView) v.findViewById(R.id.silverNumRows);
        silver.setMax(7);

        silver.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                silverRows = silver.getProgress();
                silverRowNum.setText(silverRows + " Rows");
                f0.changePercentFilled((double) progress / seekBar.getMax());
            }
        });

        depotNum = v.findViewById(R.id.DepotNum);

        String numberString = depotNum.getText().toString();
//        int num = num.;

        endPos = v.findViewById(R.id.EndgameOptions);

        return v;
    }

        @Override
        String getPageTitle () {
            return "TeleOp";
        }


}
