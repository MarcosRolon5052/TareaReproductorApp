package com.utic.tareareproductorapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // Variables y objetos
    private MediaPlayer mediaPlayer;
    private Button btnPlay, btnPause, btnResume, btnStop;
    private RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7;
    private int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aplicar insets de ventanas para Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botones
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);

        // Radio botones
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        radio6 = findViewById(R.id.radio6);
        radio7 = findViewById(R.id.radio7);
    }

    // Método para liberar el reproductor
    private void destruir() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Método para reproducir
    public void reproducir(View view) {
        // Liberar el reproductor si está en uso
        destruir();

        // Reproducción basada en el radio seleccionado
        if (radio1.isChecked()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pista1);
        } else if (radio2.isChecked()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pista2);
        } else if (radio3.isChecked()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pista3);
        } else if (radio4.isChecked()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pista4);
        } else if (radio5.isChecked()) {
            mediaPlayer = MediaPlayer.create(this, R.raw.pista5);
        } else if (radio6.isChecked()) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource("https://techtransfer.com.py/music/pista06.mp3");
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (radio7.isChecked()) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource("https://techtransfer.com.py/music/pista07.mp3");
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Iniciar la reproducción
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    // Método para pausar la reproducción
    public void pausar(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            posicion = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }

    // Método para continuar la reproducción desde donde se pausó
    public void continuar(View view) {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(posicion);
            mediaPlayer.start();
        }
    }

    // Método para detener la reproducción
    public void detener(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            posicion = 0;
        }
    }
}
