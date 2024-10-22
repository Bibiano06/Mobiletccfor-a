package com.example.projetofirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Modalidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidades);

        Button abrindo_tela = findViewById(R.id.btn_home);


        abrindo_tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent intent = new Intent(Modalidades.this, FormHome.class);

            startActivity(intent);

            }
        });

    }

}