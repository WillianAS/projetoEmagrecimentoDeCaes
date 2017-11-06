package com.example.willian.projetopurina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdministrarAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_animal);

        Button btnPesquisar = (Button) findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new ListaCachorrosCpfOnClickListener());
    }
}
