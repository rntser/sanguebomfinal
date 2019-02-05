package com.sanguebom.group.sanguebomsd;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sanguebom.group.sanguebomsd.Models.Campanha;

import java.util.List;

public class CampanhaLista extends ArrayAdapter<Campanha> {
    private Activity context;
    private List<Campanha> campanhaList;
    public CampanhaLista(Activity context, List<Campanha> campanhaList){
        super(context, R.layout.listacampanha_layout, campanhaList);
        this.context = context;
        this.campanhaList = campanhaList;

    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listacampanha_layout, null, true);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.textViewNome);
        TextView textViewTema = (TextView) listViewItem.findViewById(R.id.textViewTema);
        TextView textViewAssunto = (TextView) listViewItem.findViewById(R.id.textViewAssunto);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);


        Campanha campanha = campanhaList.get(position);

        textViewNome.setText(campanha.getNomeCampanha());
        textViewAssunto.setText(campanha.getAssuntoCampanha());
        textViewTema.setText(campanha.getTemaCampanha());

        textViewGenre.setText(campanha.getGenre());

        return listViewItem;

    }
}
