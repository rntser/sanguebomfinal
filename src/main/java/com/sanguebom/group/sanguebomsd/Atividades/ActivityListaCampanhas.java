package com.sanguebom.group.sanguebomsd.Atividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sanguebom.group.sanguebomsd.R;

public class ActivityListaCampanhas extends Activity {

    private Button botaocriarCampanha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_campanhas);

        botaocriarCampanha = findViewById(R.id.criarCampanhaId);

        botaocriarCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityListaCampanhas.this,ActivityCampanhas.class));
            }
        });
    }

}
