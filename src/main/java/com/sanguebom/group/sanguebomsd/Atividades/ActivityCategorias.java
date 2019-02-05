package com.sanguebom.group.sanguebomsd.Atividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.sanguebom.group.sanguebomsd.MainActivity;
import com.sanguebom.group.sanguebomsd.R;
import com.sanguebom.group.sanguebomsd.config.ConfiguracaoFirebase;

public class ActivityCategorias extends MainActivity {
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private ImageView botaoCampanha;
    private ImageView botaoEvento;
    private ImageView botaoPerguntasFrequentes;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listaevento,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(autenticacao.getCurrentUser() == null){ //usuario deslogado
            menu.setGroupVisible(R.id.group_deslogado,true);
        }else{ //usuario logado
            menu.setGroupVisible(R.id.group_logado, true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_Cadastrar :
                startActivity(new Intent(getApplicationContext(),ActivityCadastro.class));
                break;
            case R.id.menu_Sair:
                autenticacao.signOut();
                invalidateOptionsMenu();
                break;
            case R.id.menu_Eventos:
                startActivity(new Intent(getApplicationContext(),MeusEventosActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);


        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoCampanha = findViewById(R.id.btnCampanhaId);
        botaoEvento= findViewById(R.id.btnEventosId);
        botaoPerguntasFrequentes = findViewById(R.id.btnPerguntasFrequentesId);

        botaoCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCategorias.this,ActivityListaCampanhas.class));
            }
        });
        botaoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCategorias.this,ActivityListaEvento.class));
            }
        });
        botaoPerguntasFrequentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityCategorias.this, ActivityPerguntasFrequentes.class));
            }
        });
    }
}
