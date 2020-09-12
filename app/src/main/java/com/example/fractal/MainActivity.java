package com.example.fractal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private SeekBar bar ;
    static float ang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayVie vie = new LayVie (this);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout= (RelativeLayout) findViewById(R.id.Vista);
        mainLayout.addView(vie);
        bar = (SeekBar)findViewById(R.id.sk);
        bar.setProgress(0);
        bar.setMax(180);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ang= progress;
                vie.invalidate();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    public class LayVie extends View{
        public LayVie (Context context) {
            super(context);
        }
        @Override
        protected void onDraw(@org.jetbrains.annotations.NotNull Canvas canvas){
            canvas.translate(getWidth()/2, getHeight()/2);
            Rama (100, canvas);
        }

        public  void Rama( float longitud , Canvas can)
        {
            int  col=Color.rgb(15,59,60);
            Paint p=new Paint();
            p.setStrokeWidth(8);
            p.setColor(col);
            can.drawLine(0,0,0,-longitud,p);
            can.translate(0,-longitud);
            if(longitud>=1)
            {
                can.save();
                can.rotate(ang);
                Rama((float) (longitud *0.67),can);
                can.restore();
                can.save();
                can.rotate(-ang);
                Rama((float) (longitud *0.67),can);
                can.restore();
            }
        }
    }
}