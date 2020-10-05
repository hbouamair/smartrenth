package sample.DataAcces;

import sample.Connection.DatabaseAcces;
import sample.Connection.DatabaseProvider;
import sample.Models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientProvider implements DatabaseProvider<Client> {
    DatabaseAcces database = DatabaseAcces.getInstance();


    @Override
    public Client getOne(String cin) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT  * from client where cin = ?");
            preparedStatement.setString(1, cin);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Client c = new Client(
                        resultat.getString("nom"),
                        resultat.getString("prenom"),
                        resultat.getString("cin"),
                        resultat.getString("telephone"),
                        resultat.getString("adresse"),
                        resultat.getString("login"),
                        resultat.getString("password")
                );
                c.setId(resultat.getString("id"));
                return c;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    public Client getOneByID(String id) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT  * from client where id = ?");
            preparedStatement.setString(1, id);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Client c = new Client(
                        resultat.getString("nom"),
                        resultat.getString("prenom"),
                        resultat.getString("cin"),
                        resultat.getString("telephone"),
                        resultat.getString("adresse"),
                        resultat.getString("login"),
                        resultat.getString("password")
                );
                c.setId(resultat.getString("id"));
                return c;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void insert(Client c) {
        try {
            String req = "INSERT INTO client(`id`,`nom`,`prenom`,`cin`,`telephone`,`adresse`,`login`,`password`) VALUES (?,?,?,?,?,?,?,?) ";
            PreparedStatement psi = database.getConnection().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            psi.setObject(1, null);
            psi.setString(2, c.getNom());
            psi.setString(3, c.getPrenom());
            psi.setString(4, c.getCin());
            psi.setString(5, c.getTelephone());
            psi.setString(6, c.getAdresse());
            psi.setString(7, c.getLogin());
            psi.setString(8, c.getPassword());
            psi.executeUpdate();

            ResultSet r = psi.getGeneratedKeys();
            if (r.next())
                c.setId(r.getString(1));

            System.out.println("ajout reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client c) {
        try {
            String req = "UPDATE `client` SET `nom` = ?, `prenom` = ?,   `telephone` = ?, " +
                    " `adresse` = ?, `login` = ?, `password` = ?, `cin` = ? WHERE `client`.`id` = ?  ";
            PreparedStatement psi = database.getConnection().prepareStatement(req);
            psi.setString(1, c.getNom());
            psi.setString(2, c.getPrenom());
            psi.setString(3, c.getTelephone());
            psi.setString(4, c.getAdresse());
            psi.setString(5, c.getLogin());
            psi.setString(6, c.getPassword());
            psi.setString(7, c.getCin());

            psi.setString(8, c.getId());
            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client object) {

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("delete from client where id = ? ");
            psi.setString(1, object.getId());

            psi.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Client> getAll() {
        List<Client> l = new ArrayList<Client>();

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("SELECT  * from client ");

            ResultSet resultat = psi.executeQuery();


            while (resultat.next()) {
                Client c = new Client(
                        resultat.getString("nom"),
                        resultat.getString("prenom"),
                        resultat.getString("cin"),
                        resultat.getString("telephone"),
                        resultat.getString("adresse"),
                        resultat.getString("login"),
                        resultat.getString("password")
                );
                c.setId(resultat.getString("id"));

                l.add(c);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }

    }


    public boolean connect(String login, String passwd) {

        try {
            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT * from client where login = ? AND password = ? ");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, passwd); // ui
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }//

}
