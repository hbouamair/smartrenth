package sample.Models;

public class Manager {

    private String id;
    private String Nom;
    private String Prenom;
    private String Login;
    private String Password;
    private String cin;

    public Manager(String nom, String prenom, String login, String password, String cin) {
        Nom = nom;
        Prenom = prenom;
        Login = login;
        Password = password;
        this.cin = cin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Manager(String nom, String prenom, String cin) {
        Nom = nom;
        Prenom = prenom;
        this.cin = cin;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}
