package com.example.willian.projetopurina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ListaDeCachorros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_cachorros);

        String nomeCachorro = getIntent().getExtras().getString("nomeCachorro");
        TextView textNomeCachorro = (TextView) findViewById(R.id.txtNomeCachorro);

        textNomeCachorro.setText(nomeCachorro);

        Button btnConcluir = (Button) findViewById(R.id.btnInserirNovoPeso);
        btnConcluir.setOnClickListener(new InsereNovoPesoOnClickListener());
    }
}
