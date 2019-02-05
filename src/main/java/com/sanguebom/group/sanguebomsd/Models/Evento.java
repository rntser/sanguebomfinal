package com.sanguebom.group.sanguebomsd.Models;

import com.google.firebase.database.DatabaseReference;
import com.sanguebom.group.sanguebomsd.config.ConfiguracaoFirebase;

import java.util.List;

public class Evento {
    private  String idEvento;
    private String nome;
    private String tema;
    private String assunto;


    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference eventoRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_eventos");
    eventoRef.child(idUsuario)
            .child(getIdEvento())
            .setValue(this);
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public Evento() {
        DatabaseReference eventoRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_eventos");
        setIdEvento( eventoRef.push().getKey());
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
