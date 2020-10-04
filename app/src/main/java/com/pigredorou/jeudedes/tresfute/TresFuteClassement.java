package com.pigredorou.jeudedes.tresfute;

public class TresFuteClassement {
    private long id;
    private int score;
    private String nom;
    private String date;
    private int nbJoueurs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    // Sera utilisée par ArrayAdapter dans la ListView
    @Override
    public String toString() {
        return "ID : "+id+"\nscore : "+score+"\nNom : "+nom+"\nDate : "+date+"\nNb joueurs : "+nbJoueurs;
    }}
