package sample.Connection;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.Position;
import sample.Models.Vehicule;

import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Helper<T> {
    public static final long MINUTE = 1000 * 60;
    public static final long NBR_MINUTE = (long) (0.1 * MINUTE); //
    public static InetAddress IPADRESS;
    public static boolean isAdmin=false;

    static {
        try {
            IPADRESS = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static int PORT = 5555;

    public static double distanceBetween(Position centerPosition, Position position) {


        int Radius = 6371;// radius of earth in Km
        double lat1 = centerPosition.getLatitude();
        double lat2 = position.getLatitude();
        double lon1 = centerPosition.getLongitude();
        double lon2 = position.getLongitude();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;


        System.out.println("---->" + valueResult);
        return valueResult;


    }


    public List<TableColumn> getTableColumes(Class c) {

        List<TableColumn> list = new ArrayList<>();
        for (Field declaredField : c.getDeclaredFields()) {

            TableColumn<T, String> cc = new TableColumn<>(declaredField.getName());
            cc.setCellValueFactory(new PropertyValueFactory<>(declaredField.getName()));
            list.add(cc);

        }
        return list;


    }
}
