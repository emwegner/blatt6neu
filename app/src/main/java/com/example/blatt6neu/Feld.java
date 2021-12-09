package com.example.blatt6neu;

import android.widget.Button;

import java.util.Random;

public class Feld {
    private boolean mine;
    private boolean flagged;
    private boolean aufgedeckt;
    private int benachbarteBomben;
    private Button button;

    public Feld() {
        Random random = new Random();
        int bombe = random.nextInt(10);
        if (bombe > 4) mine = true;
        flagged = false;
        aufgedeckt = false;
    }

    public void darstellen() {
        //button darstellen
        //beachten ob feld aufgedeckt ist sowie anzahl der anliegenden bomben
    }

    public boolean isMine() {
        return mine;
    }

    public void setBombe(int zahl) {
        this.benachbarteBomben = zahl;
    }
}
