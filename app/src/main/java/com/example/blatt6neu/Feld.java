package com.example.blatt6neu;

import java.util.Random;

public class Feld {
    private boolean mine;
    private boolean flagged;
    private boolean aufgedeckt;
    private int benachbarteBomben;
    public int buttonID;


    public Feld() {
        Random random = new Random();
        int bombe = random.nextInt(10);
        if (bombe > 4) mine = true;
        flagged = false;
        aufgedeckt = false;
    }

    public boolean isMine() {
        return mine;
    }

    public void setBomben(int zahl) {
        this.benachbarteBomben = zahl;
    }

    public void aufdecken() {
        aufgedeckt = true;
    }

    public int getBenachbarteBomben() {
        return benachbarteBomben;
    }

    public boolean isAufgedeckt() {
        return aufgedeckt;
    }

    public void setID(int id) {
        this.buttonID = id;
    }

    public int getButtonID() {
        return buttonID;
    }


    public boolean isFlagged() {
        return flagged;
    }
}
