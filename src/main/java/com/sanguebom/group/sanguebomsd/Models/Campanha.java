package com.sanguebom.group.sanguebomsd.Models;

public class Campanha {
    String campanhaid;
    String nomeCampanha;
    String temaCampanha;
    String assuntoCampanha;
    String genre;

    public Campanha(){

    }

    public Campanha(String campanhaid, String nomeCampanha, String temaCampanha, String assuntoCampanha, String genre) {
        this.campanhaid = campanhaid;
        this.nomeCampanha = nomeCampanha;
        this.temaCampanha = temaCampanha;
        this.assuntoCampanha = assuntoCampanha;
        this.genre = genre;

    }

    public String getCampanhaid() {
        return campanhaid;
    }

    public void setCampanhaid(String campanhaid) {
        this.campanhaid = campanhaid;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
    }

    public String getTemaCampanha() {
        return temaCampanha;
    }

    public void setTemaCampanha(String temaCampanha) {
        this.temaCampanha = temaCampanha;
    }

    public String getAssuntoCampanha() {
        return assuntoCampanha;
    }

    public void setAssuntoCampanha(String assuntoCampanha) {
        this.assuntoCampanha = assuntoCampanha;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
