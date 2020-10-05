package sample.Models;

public class Client {
    private String id;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String adresse;
    private String login;
    private String password;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Client(String nom, String prenom, String cin, String telephone, String adresse, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.adresse = adresse;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return cin;
    }

    public Client(String nom, String prenom, String cin, String telephone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.adresse = adresse;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
