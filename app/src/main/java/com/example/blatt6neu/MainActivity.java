package com.example.blatt6neu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {

    private int height = 1500;
    private int width = 1100;
    private int cell1;
    private int cell2;
    private boolean mineClicked = false;

    private int[][] buttons;
    private Spielfeld spielfeld;

    private boolean nichtFertig = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ImageButton temp = (ImageButton) (findViewById(R.id.button0));
        buttons = new int[7][12];
        buttons[0][0] = temp.getId();
        int buttonid = buttons[0][0];
        ImageButton btn_tmp = (ImageButton) findViewById(buttonid);
        btn_tmp.setBackground(getResources().getDrawable(R.drawable.feld_flagged));

        buttons[0][0].setBackground(getResources().getDrawable(R.drawable.feld_flagged));
      */

        intizialize();
        while (nichtFertig) {

            final Button button = findViewById(R.id.button_id);
            int tempID = button.getId();

            for (int i = 0; i < spielfeld.felder.length; i++) {
                for (int j = 0; j < spielfeld.felder[i].length; j++) {
                    if (spielfeld.felder[i][j].getButtonID() == tempID) {
                        cell1 = i;
                        cell2 = j;
                        break;
                    }
                }
            }

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (spielfeld.felder[cell1][cell2].isMine()) {
                        for (int i = 0; i < spielfeld.felder.length;i++) {
                            for(int j = 0; j < spielfeld.felder[i].length;j++) {
                                ImageButton tempe = (ImageButton) findViewById(spielfeld.felder[i][j].getButtonID());
                                tempe.setClickable(false);
                                if(spielfeld.felder[i][j].isMine()) tempe.setBackground(getResources().getDrawable(R.drawable.feld_bomb));
                                Toast.makeText(getApplicationContext(),"Spiel beendet",Toast.LENGTH_LONG).show();
                                mineClicked = true;
                            }
                        }
                        button.setClickable(false);
                    } else {
                        if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 0)
                            spielfeld.nachbarnAufdecken(cell1, cell2);
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 1)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed1));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 2)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed2));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 3)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed3));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 4)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed4));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 5)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed5));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 6)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed6));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 7)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed7));
                        else if (spielfeld.felder[cell1][cell2].getBenachbarteBomben() == 8)
                            button.setBackground(getResources().getDrawable(R.drawable.pressed8));
                        button.setClickable(false);
                    }
                }
            });

            button.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if (spielfeld.felder[cell1][cell2].isFlagged()) {
                        button.setBackground(getResources().getDrawable(R.drawable.feld_default));
                    } else {
                        button.setBackground(getResources().getDrawable(R.drawable.feld_flagged));
                    }
                    return true;
                }
            });

            nichtFertig = fertig();
        }

        Toast.makeText(getApplicationContext(),"Spiel beendet",Toast.LENGTH_LONG).show();

    }



    private void intizialize() {
        this.spielfeld = new Spielfeld(this);
        this.buttons = new int[7][11];
        int count = 0;

        for (int i = 0; i < spielfeld.felder.length; i++) {
            for (int j = 0; j < spielfeld.felder[i].length; j++) {
                String tmp = "button" + count;
                int resID = getResources().getIdentifier(tmp, "id", getPackageName());
                ImageButton tempe = (ImageButton) findViewById(resID);
                //   ImageButton temp = (findViewById(getResources().getIdentifier(tmp, "id", getPackageName())));
                buttons[i][j] = tempe.getId();
                spielfeld.felder[i][j].setID(buttons[i][j]);

            }
        }
        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(spielfeld.getAnzahlBomben());

    }

    private boolean fertig() {
        int countOpen = 0;
        int countFlag = 0;
        for (int i = 0; i < spielfeld.felder.length; i++) {
            for (int j = 0; j < spielfeld.felder[i].length; j++) {
                if (spielfeld.felder[i][j].isAufgedeckt()) countOpen++;
                if (spielfeld.felder[i][j].isFlagged()) countFlag++;
            }
        }
        if (countOpen == (7 * 11) - spielfeld.getAnzahlBomben() && spielfeld.getAnzahlBomben() == countFlag || mineClicked)
            return true;

        return false;
    }


}







