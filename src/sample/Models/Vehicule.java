package sample.Models;

import sample.Connection.Helper;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Vehicule implements Serializable {
    private String Id;
    private String matricule;
    private String couleur;
    private String modelle;
    private String marque;
    private String carburant;
    private double prixParJour;
    private double kilometrage;
    private Position position = new Position("1", 0, 0);


    public void startTracing() {
        try {


            Socket socket = new Socket(Helper.IPADRESS, Helper.PORT);

            Thread thread = new Thread(() -> {

                sendDataForEver(socket);


            });
            thread.start();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return Objects.equals(Id, vehicule.Id) &&
                Objects.equals(matricule, vehicule.matricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, matricule);
    }

    private void sendDataForEver(Socket socket) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                Thread.sleep(Helper.NBR_MINUTE);
                objectOutputStream.reset();
                objectOutputStream.writeObject(this);
                objectOutputStream.flush();


                double lat = ThreadLocalRandom.current().nextDouble(-90, 90);
                double longi = ThreadLocalRandom.current().nextDouble(-180, 180);

                setPosition(new Position(lat, longi));
                System.out.println(" looooog " + getPosition());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return marque + "/" + matricule;
    }


    public Vehicule(String matricule, String couleur, String modelle,
                    String marque, String carburant, double prixParHeur, double kilometrage) {
        this.matricule = matricule;
        this.couleur = couleur;
        this.modelle = modelle;
        this.marque = marque;
        this.carburant = carburant;
        this.prixParJour = prixParHeur;
        this.kilometrage = kilometrage;
    }

    public Vehicule(String matricule, String couleur, String modelle, String marque) {
        this.matricule = matricule;
        this.couleur = couleur;
        this.modelle = modelle;
        this.marque = marque;
        this.carburant = "DESIEL";
        this.prixParJour = 0;
        this.kilometrage = 0;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String getModelle() {
        return modelle;
    }

    public void setModelle(String modelle) {
        this.modelle = modelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public double getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(double prixParHeur) {
        this.prixParJour = prixParHeur;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
