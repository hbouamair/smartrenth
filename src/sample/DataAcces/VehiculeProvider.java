package sample.DataAcces;

import sample.Connection.DatabaseAcces;
import sample.Connection.DatabaseProvider;
import sample.Models.Vehicule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehiculeProvider implements DatabaseProvider<Vehicule> {
    DatabaseAcces database = DatabaseAcces.getInstance();
    PositionProvider positionProvider = new PositionProvider();

    @Override
    public Vehicule getOne(String matricule) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT * from vehicule where matricule = ?");
            preparedStatement.setString(1, matricule);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Vehicule v = new Vehicule(
                        resultat.getString("matricule"),
                        resultat.getString("couleur"),
                        resultat.getString("modelle"),
                        resultat.getString("marque"),
                        resultat.getString("carburant"),
                        resultat.getDouble("prixParJour"),
                        resultat.getDouble("kilometrage")
                );

                v.setId(resultat.getString("id"));
                v.setPosition(positionProvider.getOne(resultat.getString("idPosition")));
                return v;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    public Vehicule getOneByID(String id) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT * from vehicule where id = ?");
            preparedStatement.setString(1, id);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Vehicule v = new Vehicule(
                        resultat.getString("matricule"),
                        resultat.getString("couleur"),
                        resultat.getString("modelle"),
                        resultat.getString("marque"),
                        resultat.getString("carburant"),
                        resultat.getDouble("prixParJour"),
                        resultat.getDouble("kilometrage")
                );

                v.setId(resultat.getString("id"));
                v.setPosition(positionProvider.getOne(resultat.getString("idPosition")));
                return v;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void insert(Vehicule v) {
        try {
            String req = "INSERT INTO Vehicule(`id`,`matricule`,`couleur`,`modelle`,`marque`,`carburant`,`prixParJour`,`kilometrage`,`idPosition`) VALUES (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement psi = database.getConnection().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            psi.setObject(1, null);
            psi.setString(2, v.getMatricule());
            psi.setString(3, v.getCouleur());
            psi.setString(4, v.getModelle());
            psi.setString(5, v.getMarque());
            psi.setString(6, v.getCarburant());
            psi.setDouble(7, v.getPrixParJour());
            psi.setDouble(8, v.getKilometrage());

            if (v.getPosition().getId() == null || positionProvider.getOne(v.getPosition().getId()) == null)
                positionProvider.insert(v.getPosition());


            psi.setString(9, v.getPosition().getId());


            psi.executeUpdate();

            ResultSet r = psi.getGeneratedKeys();
            if (r.next())
                v.setId(r.getString(1));

            System.out.println("ajout reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicule v) {
        try {
            String req = "UPDATE `vehicule` SET `matricule` = ?, `couleur` = ?,   `modelle` = ?, " +
                    " `marque` = ?, `carburant` = ?, `prixParJour` = ?, `kilometrage` = ? WHERE `vehicule`.`id` = ?  ";
            PreparedStatement psi = database.getConnection().prepareStatement(req);
            psi.setString(1, v.getMatricule());
            psi.setString(2, v.getCouleur());
            psi.setString(3, v.getModelle());
            psi.setString(4, v.getMarque());
            psi.setString(5, v.getCarburant());
            psi.setDouble(6, v.getPrixParJour());
            psi.setDouble(7, v.getKilometrage());

            psi.setString(8, v.getId());
            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(Vehicule v) {
        try {
            String req = "UPDATE `vehicule` SET `idPosition` = ?  WHERE `vehicule`.`id` = ?  ";
            PreparedStatement psi = database.getConnection().prepareStatement(req);

            if (positionProvider.getOne(v.getPosition().getId()) == null)
                positionProvider.insert(v.getPosition());

            psi.setString(1, v.getPosition().getId());
            psi.setString(2, v.getId());

            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Vehicule v) {

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("delete from vehicule where id = ? ");
            psi.setString(1, v.getId());

            psi.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public List<Vehicule> getAll() {
        List<Vehicule> l = new ArrayList<Vehicule>();

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("SELECT  * from vehicule ");

            ResultSet resultat = psi.executeQuery();
//

            while (resultat.next()) {
                Vehicule v = new Vehicule(
                        resultat.getString("matricule"),
                        resultat.getString("couleur"),
                        resultat.getString("modelle"),
                        resultat.getString("marque"),
                        resultat.getString("carburant"),
                        resultat.getDouble("prixParJour"),
                        resultat.getDouble("kilometrage")
                );
                v.setId(resultat.getString("id"));
                v.setPosition(positionProvider.getOne(resultat.getString("idPosition")));

                l.add(v);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }

    }
}
