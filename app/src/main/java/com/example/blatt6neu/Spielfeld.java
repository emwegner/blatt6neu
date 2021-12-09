package com.example.blatt6neu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Spielfeld {
    private Feld[][] spielfeld;
    private boolean[][] bomben;
    private int height;
    private int width;
    private Paint paint;
    private Canvas canvas;
    private int anzahlFelder;
    private int anzahlBomben;
    private int flaggen;

    public Spielfeld(int height, int width, Paint paint, Canvas canvas) {
        this.height = height;
        this.width = width;
        this.paint = paint;
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                Feld feld = new Feld();
            }
        }
        setzeZahlen();
    }


    public void darstellen() {
        canvas.drawColor(Color.argb(100, 50, 50, 50));
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j].darstellen();
            }
        }
    }

    public void setzeZahlen() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j].setBombe(zaehlen(i, j));
                if(spielfeld[i][j].isMine()) {
                    bomben[i][j] = true;
                    anzahlBomben +=1;
                }
            }
        }
    }


    public int zaehlen(int currentI, int currentJ) {
        int count = 0;
        for(int i = currentI - 1; i <= currentI + 1; i++) {
            if (i >= 0 && i < spielfeld.length)
                for(int j = currentJ - 1; j <= currentJ + 1; j++)
                    if (j >= 0 && j < spielfeld[i].length)
                        if (i != currentI || j != currentJ)
                            if (spielfeld[i][j].isMine())
                                count++;
        }
        return count;
    }

}


