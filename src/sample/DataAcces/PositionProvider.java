package sample.DataAcces;

import sample.Connection.DatabaseAcces;
import sample.Connection.DatabaseProvider;
import sample.Models.Position;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PositionProvider implements DatabaseProvider<Position> {
    DatabaseAcces database = DatabaseAcces.getInstance();

    @Override
    public Position getOne(String id) {


        try {

            PreparedStatement preparedStatement
                    = database.getConnection().prepareStatement("SELECT * from position where id = ?");
            preparedStatement.setString(1, id);

            ResultSet resultat = preparedStatement.executeQuery();


            if (resultat.next()) {

                Position p = new Position(
                        resultat.getString("id"),
                        resultat.getDouble("latitude"),
                        resultat.getDouble("longitude")
                );
                p.setId(resultat.getString("id"));
                return p;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public void insert(Position object) { //

        try {
            String req = "INSERT INTO position VALUES (?,?,?) ";
            PreparedStatement psi = database.getConnection().prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            psi.setObject(1, null);
            psi.setDouble(2, object.getLatitude());
            psi.setDouble(3, object.getLongitude());
            psi.executeUpdate();

            ResultSet r = psi.getGeneratedKeys();
            if (r.next())
                object.setId(r.getString(1));

            System.out.println("ajout reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Position p) {
        try {
            String req = "UPDATE `position` SET  `latitude` = ?,   `longitude` = ? where `id` = ?";
            PreparedStatement psi = database.getConnection().prepareStatement(req);
            psi.setDouble(1, p.getLatitude());
            psi.setDouble(2, p.getLongitude());
            psi.setString(3, p.getId());

            psi.executeUpdate();
            System.out.println("UPDATE reusii !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Position p) {
        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("delete from position where id = ? ");
            psi.setString(1, p.getId());

            psi.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Position> getAll() {
        List<Position> l = new ArrayList<Position>();

        try {
            PreparedStatement psi
                    = database.getConnection().prepareStatement("SELECT  * from position ");

            ResultSet resultat = psi.executeQuery();


            while (resultat.next()) {
                Position p = new Position(
                        resultat.getString("id"),
                        resultat.getDouble("latitude"),
                        resultat.getDouble("longitude")
                        );
                l.add(p);
            }
            return l;
        } catch (Exception e) {
            e.printStackTrace();
            return l;
        }

    }
}
