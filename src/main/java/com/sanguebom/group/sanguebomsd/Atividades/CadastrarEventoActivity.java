package com.sanguebom.group.sanguebomsd.Atividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sanguebom.group.sanguebomsd.Models.Evento;
import com.sanguebom.group.sanguebomsd.R;

public class CadastrarEventoActivity extends AppCompatActivity {
 private EditText nomeEvento;
 private EditText assuntoEvento;
 private  EditText temaEvento;
 private Button botaoSalvarEvento;
 private Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        botaoSalvarEvento = findViewById(R.id.btnSalvarEvento);

    }
    public void salvarEvento(View view) {
        String nome = nomeEvento.getText().toString();
        String tema = temaEvento.getText().toString();
        String assunto = assuntoEvento.getText().toString();


    }
    public void salvarEvento(){
        evento.salvar();


    }
    private Evento configurarEvento(){
        String nome = nomeEvento.getText().toString();
        String tema = temaEvento.getText().toString();
        String assunto = assuntoEvento.getText().toString();

        Evento evento = new Evento();

        evento.setNome(nome);
        evento.setAssunto(assunto);
        evento.setTema(tema);
        return evento;
    }



        public void validarDadosEventos(View view){
       evento = configurarEvento();

        if(!evento.getNome().isEmpty()) {

        } else {
            exibirMensagemErro("Preencha o campo nome!");
        }

        if(!evento.getTema().isEmpty()) {
        } else {
            exibirMensagemErro("Preencha o campo tema!");
        }
        if(!evento.getAssunto().isEmpty()) {
        } else {
            exibirMensagemErro("Preencha o campo assunto!");
        }
        salvarEvento();

    }

    private void exibirMensagemErro(String mensagem){
        Toast.makeText(this,mensagem, Toast.LENGTH_SHORT).show();
    }



    private void inicializarComponentes(){

        nomeEvento = findViewById(R.id.edtNomeEvento);
        assuntoEvento = findViewById(R.id.edtAssuntoEvento);
        temaEvento = findViewById(R.id.edtTemaEvento);
        botaoSalvarEvento = findViewById(R.id.btnSalvarEvento);
    }


}

