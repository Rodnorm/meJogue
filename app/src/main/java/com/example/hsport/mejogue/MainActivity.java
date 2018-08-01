package com.example.hsport.mejogue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    int dadoNum;
    Random rand;
    TextView TextResultado;
    ArrayList<Integer> dValor;
    ArrayList<ImageView> imagemDado;
    MediaPlayer mp;
    ImageView exibirDado;
    //
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                mp.start();
            }
        });
        //

        Dice_Settings d = new Dice_Settings();
        Intent i = getIntent();
        final int a = i.getIntExtra("seis_lados", 0);
        final int b = i.getIntExtra("doze_lados", 0);
        final int c = i.getIntExtra("vinte_lados", 0);
        exibirDado = (ImageView) findViewById(R.id.dados);

        if (a == 0 && b == 0 && c == 0 || a == 1 && b == 0 && c ==0){
            exibirDado.setImageResource(R.drawable.die_3);
        }else if (a == 0 && b== 1 && c == 0){
            exibirDado.setImageResource(R.drawable.d_1);
        }else if (a == 0 && b == 0 && c ==1){
            exibirDado.setImageResource(R.drawable.do_1);
        }

        mp = MediaPlayer.create(MainActivity.this, R.raw.dice); //adiciona o som
        Toast.makeText(getApplicationContext(), "Bem vindo ao meJogaEu!!", Toast.LENGTH_SHORT).show();
        rand = new Random();//instacia o random dos dados
        dValor = new ArrayList<>();
        TextResultado = (TextView) findViewById(R.id.TextResultado);//encontra o campo do resultado
        final ImageView dadoImagem = (ImageView) findViewById(R.id.dados);
        imagemDado = new ArrayList<>();
        imagemDado.add(dadoImagem);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.meJoga);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                if (a == 1 && b == 0 && c == 0 || a == 0 && b == 0 && c == 0) {
                    //dadoImagem.setImageResource(R.drawable.die_1);
                    //dadoImagem.setImageResource(R.drawable.die_3);
                    rolaDadosSEIS(view);
                } else if (b == 1 && a == 0 && c == 0) {
                    //dadoImagem.setImageResource(R.drawable.d_7);
                    //dadoImagem.setImageResource(R.drawable.d_1);
                    rolaDadosDOZE(view);
                } else if (c == 1 && a == 0 && b == 0) {
                    //dadoImagem.setImageResource(R.drawable.do_13);
                    //dadoImagem.setImageResource(R.drawable.do_1);
                    rolaDadosVINTE(view);
                }
            }
        });

    }

    public void rolaDadosSEIS(View v) {
        dadoNum = rand.nextInt(6) + 1;
        dValor.clear();
        dValor.add(dadoNum);

        for (int i = 0; i < 1; i++) {
            String nomeImage = "die_" + dValor.get(i) + ".png";
            try {
                InputStream stream = getAssets().open(nomeImage);
                Drawable d = Drawable.createFromStream(stream, null);
                imagemDado.get(i).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rolaDadosVINTE(View v) {
        dadoNum = rand.nextInt(20) + 1;
        dValor.clear();
        dValor.add(dadoNum);
        for (int i = 0; i < 1; i++) {
            String nomeImage = "do_" + dValor.get(i) + ".png";
            try {
                InputStream stream = getAssets().open(nomeImage);
                Drawable d = Drawable.createFromStream(stream, null);
                imagemDado.get(i).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rolaDadosDOZE(View v) {
        dadoNum = rand.nextInt(12) + 1;
        dValor.clear();
        dValor.add(dadoNum);
        for (int i = 0; i < 1; i++) {
            String nomeImage = "d_" + dValor.get(i) + ".png";
            try {
                InputStream stream = getAssets().open(nomeImage);
                Drawable d = Drawable.createFromStream(stream, null);
                imagemDado.get(i).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(this, Dice_Settings.class);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
