package sample.Models;



public class Location {
    private Vehicule veicule;
    private Client client;
    private String id;
    private String date;
    private int dureeJour;
    private double montant;
    private Position centerPosition;
    private double rayon;


    @Override
    public String toString() {
        return "Location{" +
                "veicule=" + veicule +
                ", client=" + client +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", dureeJour=" + dureeJour +
                ", montant=" + montant +
                ", centerPosition=" + centerPosition +
                ", rayon=" + rayon +
                '}';
    }

    public Location(Vehicule veicule, Client client, String date, int duree) {
        this.veicule = veicule;
        this.client = client;
        this.date = date;
        this.dureeJour = duree;
        calculerMontantTotal();
    }


    public void setCenterPosition(Position centerPosition) {
        this.centerPosition = centerPosition;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public double getRayon() {
        return rayon;
    }

    public Position getCenterPosition() {
        return centerPosition;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void calculerMontantTotal() {
        montant = dureeJour * veicule.getPrixParJour();
    }


    public Location(Vehicule veicule, Client client) {
        this.veicule = veicule;
        this.client = client;
        calculerMontantTotal();
    }


    public Vehicule getVeicule() {
        return veicule;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDureeJour() {
        return dureeJour;
    }

    public void setDureeJour(int dureeJour) {
        this.dureeJour = dureeJour;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


    public Vehicule getVehicule() {
        return veicule;
    }

    public void setVeicule(Vehicule veicule) {
        this.veicule = veicule;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
