package com.sanguebom.group.sanguebomsd.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;
    private static DatabaseReference referenciaFirebase;

    public static String getIdUsuario(){
        FirebaseAuth autenticacao =  getFirebaseAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }


    //retorna a instancia do FirebaseAuth



    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null) {

            autenticacao = FirebaseAuth.getInstance();

        }
        return autenticacao;
    }
    public static DatabaseReference getFirebase(){
        if (referenciaFirebase ==null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
}
