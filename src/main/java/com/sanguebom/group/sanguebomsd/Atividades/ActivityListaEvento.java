package com.sanguebom.group.sanguebomsd.Atividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.sanguebom.group.sanguebomsd.R;

public class ActivityListaEvento extends Activity {
    private Button botaoCriarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);

        botaoCriarEvento = findViewById(R.id.bntCriarEventoId);
        botaoCriarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityListaEvento.this,ActivityEventos.class));
            }
        });
    }

}
