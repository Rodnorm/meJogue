package com.example.hsport.mejogue;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Dice_Settings extends AppCompatActivity {

    Button vinte;
    Button doze;
    Button seis;
    Button voltar;
    public int a; // seis lados
    public int b; // doze lados
    public int c; // vinte lados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_dice__settings);
        voltar = (Button) findViewById(R.id.voltar);
        seis = (Button) findViewById (R.id.seis_lados);
        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ImageView dado = (ImageView)findViewById(R.id.dado_option);
                dado.setImageResource(R.drawable.die_1);
                a = 1; // seis lados
                b = 0;
                c = 0;
            }
        });

        doze = (Button) findViewById (R.id.doze_lados);
        doze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ImageView dado = (ImageView)findViewById(R.id.dado_option);
                dado.setImageResource(R.drawable.d_1);
                a = 0;
                b = 1;// doze lados
                c = 0;
            }
        });
        vinte = (Button) findViewById (R.id.vinte_lados);
        vinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ImageView dado = (ImageView)findViewById(R.id.dado_option);
                dado.setImageResource(R.drawable.do_1);
                a = 0;
                b = 0;
                c = 1;// 20 lados
            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (Dice_Settings.this, MainActivity.class);
                i.putExtra("seis_lados",a);
                i.putExtra("doze_lados",b);
                i.putExtra("vinte_lados", c);
                startActivity(i);
            }
        });
    }

    public int getA(){
        return this.a = a;
    }
    public int getB(){
        return this.b = b;
    }
    public int getC(){
        return this.c = c;
    }
}
