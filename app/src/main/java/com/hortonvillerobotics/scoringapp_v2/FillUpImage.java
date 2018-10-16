package com.hortonvillerobotics.scoringapp_v2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.SeekBar;

@SuppressLint("AppCompatCustomView")
public class FillUpImage extends ImageView {

    Bitmap img = null;

    public FillUpImage(Context context) {
        super(context);
        img = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        img.setPixel(img.getWidth()/2, img.getHeight()/2, Color.RED);
        this.setImageBitmap(img);
        changePercentFilled(0);
    }

    public FillUpImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        changePercentFilled(0);
    }

    public FillUpImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        changePercentFilled(0);
    }

    public void pairSeekBar(SeekBar s){
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changePercentFilled((double)progress/seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void changePercentFilled(double percentFilled){
        percentFilled = 1-percentFilled;
        img = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        img.setPixel(img.getWidth()/2, img.getHeight()/2, Color.RED);
        for(int i = img.getHeight()-1; i >=0; i--){
            for(int j = 0; j < img.getWidth(); j++){
                if(i >= percentFilled*img.getHeight()) img.setPixel(j,i,Color.rgb(0xff,0xc6,0x1c));
                else img.setPixel(j,i,Color.GRAY);
            }
        }
        this.setImageBitmap(img);
    }

}
