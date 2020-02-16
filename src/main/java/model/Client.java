/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hamza
 */
public class Client {
    private int id ;
    private String nom;
    private String prenom;
    private String cin;
    private String adresse ;
    private int numtele;

    public Client(int id,String nom,String prenom, String cin, String adresse, int numtele){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.cin=cin;
        this.adresse=adresse;
        this.numtele=numtele;


    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumtele() {
        return numtele;
    }

    public void setNumtele(int numtele) {
        this.numtele = numtele;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
