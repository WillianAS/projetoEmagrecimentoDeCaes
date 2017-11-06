package com.example.willian.projetopurina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CadastrarAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_animal);

        Button btnConcluir = (Button) findViewById(R.id.concluir);
        btnConcluir.setOnClickListener(new CreateCadastroOnClickListener());

    }
}
