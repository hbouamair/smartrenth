package sample.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAcces {
    private Connection connection;
    private static DatabaseAcces intante;

    public static DatabaseAcces getInstance() {
        if (intante == null)
            intante = new DatabaseAcces();

        return intante;
    }

    public Connection getConnection() {
        return connection;
    }

    private DatabaseAcces() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/locationVoiture", "root", "");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
