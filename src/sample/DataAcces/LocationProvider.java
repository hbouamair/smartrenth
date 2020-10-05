package sample.DataAcces;

import sample.Connection.DatabaseAcces;
import sample.Connection.DatabaseProvider;
import sample.Models.Client;
import sample.Models.Location;
import sample.Models.Position;
import sample.Models.Vehicule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LocationProvider implements DatabaseProvider<Location> {
    DatabaseAcces database = DatabaseAcces.getInstance();

    ClientProvider clientProvider = new ClientProvider();
    VehiculeProvider vehiculeProvider = new VehiculeProvider();
    PositionProvider positionProvider = new PositionProvider();

    @Override
    public Location getOne(String id) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT  * from Location where id = ?");
            preparedStatement.setString(1, id);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Client client = clientProvider.getOneByID(resultat.getString("idClient"));
                Vehicule vehicule = vehiculeProvider.getOneByID(resultat.getString("idVehicule"));
                Position position = positionProvider.getOne(resultat.getString("idCenterPosition"));

                Location location = new Location(
                        vehicule,
                        client,
                        resultat.getString("date"),
                        resultat.getInt("dureeJour")
                );
                location.setId(resultat.getString("id"));
                location.setRayon(resultat.getDouble("rayon"));
                location.setMontant(resultat.getDouble("montant"));
                location.setCenterPosition(position);
                return location;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void insert(Location c) {

        if (c.getClient() == null || c.getVehicule() == null || c.getCenterPosition() == null)
            System.out.println("data insatisfied  ");
        else
            try {


                if (c.getCenterPosition().getId() == null)
                    positionProvider.insert(c.getCenterPosition());


                String req = " INSERT INTO `location` (`id`,`idClient`, `idVehicule`,`idCenterPosition`,  `date`, `dureeJour`, `montant`,  `rayon`)" +
                        " VALUES    (?,?,?,?,?,?,?,?) ";
                PreparedStatement psi = database.getConnection().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
                psi.setObject(1, null);
                psi.setString(2, c.getClient().getId());
                psi.setString(3, c.getVehicule().getId());

                psi.setString(4, c.getCenterPosition().getId());
                psi.setString(5, c.getDate());
                psi.setInt(6, c.getDureeJour());
                psi.setDouble(7, c.getMontant());
                psi.setDouble(8, c.getRayon());


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
    public void update(Location c) {
        try {


            if (c.getCenterPosition().getId() == null)
                positionProvider.insert(c.getCenterPosition());

            String req = "UPDATE `location` SET `idClient` = ?, `idVehicule` = ?, `date` = ?," +
                    " `dureeJour` = ?, `montant` = ?, `idCenterPosition` = ?, `rayon` = ? WHERE `location`.`id` =?  ";
            PreparedStatement psi = database.getConnection().prepareStatement(req);
            psi.setString(1, c.getClient().getId());
            psi.setString(2, c.getVehicule().getId());
            psi.setString(3, c.getDate());
            psi.setInt(4, c.getDureeJour());
            psi.setDouble(5, c.getMontant());
            psi.setString(6, c.getCenterPosition().getId());
            psi.setDouble(7, c.getRayon());

            psi.setString(8, c.getId());
            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Location object) {

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("delete from location where id = ? ");
            psi.setString(1, object.getId());

            psi.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Location> getAll() {
        List<Location> locationList = new ArrayList<>();

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("SELECT  * from location ");

            ResultSet resultat = psi.executeQuery();


            while (resultat.next()) {
                Client client = clientProvider.getOneByID(resultat.getString("idClient"));
                Vehicule vehicule = vehiculeProvider.getOneByID(resultat.getString("idVehicule"));
                Position position = positionProvider.getOne(resultat.getString("idCenterPosition"));

                Location location = new Location(
                        vehicule,
                        client,
                        resultat.getString("date"),
                        resultat.getInt("dureeJour")
                );
                location.setId(resultat.getString("id"));
                location.setRayon(resultat.getDouble("rayon"));
                location.setMontant(resultat.getDouble("montant"));
                location.setCenterPosition(position);

                locationList.add(location);
            }
            return locationList;
        } catch (Exception e) {
            e.printStackTrace();
            return locationList;
        }

    }
}
