package com.pigredorou.jeudedes.tresfute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TresFuteClassementBDD {

    private SQLiteDatabase bdd;

    private TresFuteDBOpenHelper maBaseSQLite;

    public TresFuteClassementBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new TresFuteDBOpenHelper(context);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertClassement(TresFuteClassement classement){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(TresFuteDBOpenHelper.Constants.COL_SCORE, classement.getScore());
        values.put(TresFuteDBOpenHelper.Constants.COL_NOM, classement.getNom());
        values.put(TresFuteDBOpenHelper.Constants.COL_DATE, classement.getDate());
        values.put(TresFuteDBOpenHelper.Constants.COL_NBJ, classement.getNbJoueurs());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT, null, values);
    }

    public int updateClassement(int id, TresFuteClassement classement){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(TresFuteDBOpenHelper.Constants.COL_SCORE, classement.getScore());
        values.put(TresFuteDBOpenHelper.Constants.COL_NOM, classement.getNom());
        values.put(TresFuteDBOpenHelper.Constants.COL_DATE, classement.getDate());
        values.put(TresFuteDBOpenHelper.Constants.COL_NBJ, classement.getNbJoueurs());
        return bdd.update(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT, values, TresFuteDBOpenHelper.Constants.COL_ID + " = " +id, null);
    }

    public int removeClassementWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT, TresFuteDBOpenHelper.Constants.COL_ID + " = " +id, null);
    }

    public TresFuteClassement getClassementWithNom(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT,
                new String[] {TresFuteDBOpenHelper.Constants.COL_ID,
                        TresFuteDBOpenHelper.Constants.COL_SCORE,
                        TresFuteDBOpenHelper.Constants.COL_NOM,
                        TresFuteDBOpenHelper.Constants.COL_DATE,
                        TresFuteDBOpenHelper.Constants.COL_NBJ}, TresFuteDBOpenHelper.Constants.COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToLivre(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private TresFuteClassement cursorToLivre(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        TresFuteClassement classement = new TresFuteClassement();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        classement.setId(c.getInt(TresFuteDBOpenHelper.Constants.NUM_COL_ID));
        classement.setScore(c.getInt(TresFuteDBOpenHelper.Constants.NUM_COL_SCORE));
        classement.setNom(c.getString(TresFuteDBOpenHelper.Constants.NUM_COL_NOM));
        classement.setDate(c.getString(TresFuteDBOpenHelper.Constants.NUM_COL_DATE));
        classement.setNbJoueurs(c.getInt(TresFuteDBOpenHelper.Constants.NUM_COL_NBJ));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return classement;
    }

    public Cursor getClassementTrieParSCore(){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT, new String[] {TresFuteDBOpenHelper.Constants.COL_ID,
                TresFuteDBOpenHelper.Constants.COL_SCORE, TresFuteDBOpenHelper.Constants.COL_NOM, TresFuteDBOpenHelper.Constants.COL_DATE,
                TresFuteDBOpenHelper.Constants.COL_NBJ}, null, null, null, null, TresFuteDBOpenHelper.Constants.COL_SCORE + " DESC", "10");
        return c;
    }

}
