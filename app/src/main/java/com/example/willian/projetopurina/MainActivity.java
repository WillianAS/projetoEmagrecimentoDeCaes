package com.example.willian.projetopurina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button administrar = (Button) findViewById(R.id.botao_administrar);
        administrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AdministrarAnimal.class);
                startActivity(myIntent);
            }
        });

        Button cadastrar = (Button) findViewById(R.id.botao_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, CadastrarAnimal.class);
                startActivity(myIntent);
            }
        });
    }
}
