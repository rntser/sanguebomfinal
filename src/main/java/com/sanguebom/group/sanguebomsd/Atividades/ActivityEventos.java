package com.sanguebom.group.sanguebomsd.Atividades;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sanguebom.group.sanguebomsd.Models.Evento;
import com.sanguebom.group.sanguebomsd.Models.Usuario;
import com.sanguebom.group.sanguebomsd.R;

import org.w3c.dom.Text;

public class ActivityEventos extends Activity {
    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);



    }


}

