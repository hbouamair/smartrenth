package sample.DataAcces;

import sample.Connection.DatabaseAcces;
import sample.Connection.DatabaseProvider;
import sample.Models.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerProvider implements DatabaseProvider<Manager> {
    DatabaseAcces database = DatabaseAcces.getInstance();


    public boolean connect(String login, String passwd) {

        try {
            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT * from manager where login = ? AND password = ? ");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, passwd);
            ResultSet resultat = preparedStatement.executeQuery();
            while (resultat.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }//


    @Override
    public Manager getOne(String cin) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT  * from manager where cin = ?");
            preparedStatement.setString(1, cin);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Manager m = new Manager(
                        resultat.getString("nom"),
                        resultat.getString("prenom"),
                        resultat.getString("cin"),
                        resultat.getString("login"),
                        resultat.getString("password")
                );
                m.setId(resultat.getString("id"));
                return m;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void insert(Manager m) {
        try {
            String req = "INSERT INTO manager(`id`,`nom`,`prenom`,`cin`,`login`,`password`) VALUES (?,?,?,?,?,?) ";
            PreparedStatement psi = database.getConnection().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            psi.setObject(1, null);
            psi.setString(2, m.getNom());
            psi.setString(3, m.getPrenom());
            psi.setString(4, m.getCin());
            psi.setString(5, m.getLogin());
            psi.setString(6, m.getPassword());
            psi.executeUpdate();

            ResultSet r = psi.getGeneratedKeys();
            if (r.next())
                m.setId(r.getString(1));

            System.out.println("ajout reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manager m) {
        try {
            String req = "UPDATE `manager` SET `nom` = ?, `prenom` = ?, `cin` = ?, `login` = ?, `password` = ?, `cin` = ? WHERE `manager`.`id` = ?  ";
            PreparedStatement psi = database.getConnection().prepareStatement(req);
            psi.setString(1, m.getNom());
            psi.setString(2, m.getPrenom());
            psi.setString(3, m.getLogin());
            psi.setString(4, m.getPassword());
            psi.setString(5, m.getCin());

            psi.setString(6, m.getId());
            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Manager m) {

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("delete from manager where id = ? ");
            psi.setString(1, m.getId());

            psi.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Manager> getAll() {
        List<Manager> l = new ArrayList<Manager>();

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("SELECT  * from manager ");

            ResultSet resultat = psi.executeQuery();


            while (resultat.next()) {
                Manager m = new Manager(
                        resultat.getString("nom"),
                        resultat.getString("prenom"),
                        resultat.getString("login"),
                        resultat.getString("password"),
                        resultat.getString("cin")
                );
                m.setId(resultat.getString("id"));

                l.add(m);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }

    }
}
