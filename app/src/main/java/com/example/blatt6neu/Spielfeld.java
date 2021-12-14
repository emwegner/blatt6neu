package com.example.blatt6neu;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Spielfeld {
    public static Activity activity;
    public static Feld[][] felder;
    private boolean[][] bomben;
    private int anzahlBomben = 0;
    private int aufgedeckteFelder;

    public Spielfeld(Activity activity) {
        this.activity = activity;
        felder = new Feld[7][11];
        bomben = new boolean[7][11];

        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                felder[i][j] = new Feld();
                if (felder[i][j].isMine()) {
                    bomben[i][j] = true;
                    anzahlBomben++;
                }
            }
            setzeZahlen();
        }


    }


    public void setzeZahlen() {
        for (int i = 0; i < felder.length; i++) {
            for (int j = 0; j < felder[i].length; j++) {
                felder[i][j].setBomben(zaehlen(i, j));
            }
        }
    }


    public int zaehlen(int currentI, int currentJ) {
        int count = 0;
        for (int i = currentI - 1; i <= currentI + 1; i++) {
            if (i >= 0 && i < felder.length)
                for (int j = currentJ - 1; j <= currentJ + 1; j++) {
                    if (j >= 0 && j < felder[i].length)
                        if (i != currentI || j != currentJ)
                            if (bomben[i][j])
                                count++;
                }
        }
        return count;
    }

    public static void nachbarnAufdecken(int currentI, int currentJ) {
        if (!felder[currentI][currentJ].isAufgedeckt() && felder[currentI][currentJ].getBenachbarteBomben() == 0) {
            felder[currentI][currentJ].aufdecken();
            ImageButton tempe = (ImageButton) activity.findViewById(felder[currentI][currentJ].buttonID);
            background(tempe, currentI, currentJ);
            for (int i = currentI - 1; i <= currentI + 1; i++) {
                if (i >= 0 && i < felder.length)
                    for (int j = currentJ - 1; j <= currentJ + 1; j++)
                        if (j >= 0 && j < felder[i].length)
                            if (i != currentI || j != currentJ)
                                nachbarnAufdecken(i, j);

            }
        }
    }

    public int getAnzahlBomben() {
        return anzahlBomben;
    }

    public static void background(ImageButton button, int i, int j) {
        if (felder[i][j].getBenachbarteBomben() == 0)
            button.setBackground(activity.getResources().getDrawable(R.drawable.blank_pressed));
        else if (felder[i][j].getBenachbarteBomben() == 1)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed1));
        else if (felder[i][j].getBenachbarteBomben() == 2)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed2));
        else if (felder[i][j].getBenachbarteBomben() == 3)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed3));
        else if (felder[i][j].getBenachbarteBomben() == 4)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed4));
        else if (felder[i][j].getBenachbarteBomben() == 5)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed5));
        else if (felder[i][j].getBenachbarteBomben() == 6)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed6));
        else if (felder[i][j].getBenachbarteBomben() == 7)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed7));
        else if (felder[i][j].getBenachbarteBomben() == 8)
            button.setBackground(activity.getResources().getDrawable(R.drawable.pressed8));
        button.setClickable(false);
    }
}



