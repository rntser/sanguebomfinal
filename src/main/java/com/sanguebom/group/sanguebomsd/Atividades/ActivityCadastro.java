package com.sanguebom.group.sanguebomsd.Atividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sanguebom.group.sanguebomsd.MainActivity;
import com.sanguebom.group.sanguebomsd.Models.Usuario;
import com.sanguebom.group.sanguebomsd.R;
import com.sanguebom.group.sanguebomsd.config.ConfiguracaoFirebase;

public class ActivityCadastro extends MainActivity {
    private EditText campoEmail, campoSenha;
    private Button botaoCadastrarUsuario;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private ImageView logarUsandoFacebook;
    private Switch tipoAcesso;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        tipoAcesso = findViewById(R.id.switchAcesso);
        campoSenha = findViewById(R.id.edtSenha);
        campoEmail = findViewById(R.id.edtEmail);
        logarUsandoFacebook = (ImageView) findViewById(R.id.imgFacebookid);


        logarUsandoFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 startActivity(new Intent(ActivityCadastro.this, MainActivity.class));
            }
        });




        botaoCadastrarUsuario = findViewById( R.id.btncadastrarUsuárioId);
        //cadastro de usuário

        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                //validar se os campos foram preenchidos


                    if (!textoEmail.isEmpty()){
                        if(!textoSenha.isEmpty()){


                            usuario = new Usuario();

                            usuario.setEmail(textoEmail);
                            usuario.setSenha(textoSenha);




                            cadastrarUsuario();

                            //verificar se tiver logado

                            if(tipoAcesso.isChecked()){//cadastro
                                autenticacao.createUserWithEmailAndPassword(
                                        textoEmail, textoSenha
                                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if( task.isSuccessful()){
                                            Toast.makeText(ActivityCadastro.this, "Cadastro realizado com sucesso",Toast.LENGTH_SHORT).show();
                                            //direcionar para tela principal do app

                                        }else{
                                            Toast.makeText(ActivityCadastro.this, "Falha ao cadastrar usuário",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else {//login do usuário

                                autenticacao.signInWithEmailAndPassword(
                                        textoEmail, textoSenha
                                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if( task.isSuccessful()){
                                            Toast.makeText(ActivityCadastro.this, "Logado realizado com sucesso",Toast.LENGTH_SHORT).show();

                                         startActivity(new Intent(getApplicationContext(),ActivityCategorias.class));
                                        }
                                        else{
                                            Toast.makeText(ActivityCadastro.this, "Erro ao fazer o login" +task.getException(),Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }


                        }else{
                            Toast.makeText(ActivityCadastro.this, "Prencha a senha!", Toast.LENGTH_SHORT).show();

                        }
                    }

                        else{
                        Toast.makeText(ActivityCadastro.this, "Prencha o E-mail!", Toast.LENGTH_SHORT).show();


                    }
                    }




        });


    }


    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword( usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(ActivityCadastro.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
startActivity(new Intent(getApplicationContext(),ActivityCategorias.class));
finish();
                }else{
                    Toast.makeText(ActivityCadastro.this, "Erro ao cadastrar usuario!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}