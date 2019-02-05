package com.sanguebom.group.sanguebomsd.Atividades;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sanguebom.group.sanguebomsd.CampanhaLista;
import com.sanguebom.group.sanguebomsd.Models.Campanha;
import com.sanguebom.group.sanguebomsd.MainActivity;
import com.sanguebom.group.sanguebomsd.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityCampanhas extends MainActivity {
    Button botaosalvarCampanha;
    EditText nomeCampanha, temaCampanha, assuntoCampanha;
    Spinner spinnerGenres;
    DatabaseReference databaseCampanhas;

    ListView listViewCampanhas;
    List<Campanha> campanhaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campanhas);

        databaseCampanhas = FirebaseDatabase.getInstance().getReference("campanha");
        botaosalvarCampanha = findViewById(R.id.btnSalvarCampanha);
        nomeCampanha = findViewById(R.id.edtNomeCampanha);
        assuntoCampanha = findViewById(R.id.edtAssuntoCampanha);
        temaCampanha = findViewById(R.id.edtTemaCampanha);
        spinnerGenres = findViewById(R.id.spinner);

        listViewCampanhas = (ListView) findViewById(R.id.listViewCampanhas);
        campanhaList = new ArrayList<>();

        botaosalvarCampanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarcampanha();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseCampanhas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                campanhaList.clear();


           for (DataSnapshot campanhaSnapshot : dataSnapshot.getChildren()){
               Campanha campanha = campanhaSnapshot.getValue(Campanha.class);

               campanhaList.add(campanha);

           }
                CampanhaLista adapter = new CampanhaLista(ActivityCampanhas.this, campanhaList);
                listViewCampanhas.setAdapter(adapter);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }

    private void salvarcampanha(){
        String nome = nomeCampanha.getText().toString().trim();
        String tema = temaCampanha.getText().toString().trim();
        String assunto = assuntoCampanha.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if (!TextUtils.isEmpty(nome)){
           String id =  databaseCampanhas.push().getKey();

           Campanha campanha = new Campanha(id,nome, assunto, tema, genre);

           databaseCampanhas.child(id).setValue(campanha);
            Toast.makeText(this, "Campanha adiciona com sucesso!", Toast.LENGTH_LONG).show();


        }else {
            Toast.makeText(this, "Preencha o nome da Campanha", Toast.LENGTH_LONG).show();
        }
        if (!TextUtils.isEmpty(tema)){

        }else {
            Toast.makeText(this, "Preencha o tema da Campanha", Toast.LENGTH_LONG).show();
        } if (!TextUtils.isEmpty(assunto)){

        }else {
            Toast.makeText(this, "Preencha o assunto da Campanha", Toast.LENGTH_LONG).show();
        }
    }
}

