package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Connection.Helper;
import sample.DataAcces.ClientProvider;
import sample.DataAcces.LocationProvider;
import sample.DataAcces.VehiculeProvider;
import sample.Models.Client;
import sample.Models.Location;
import sample.Models.Position;
import sample.Models.Vehicule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationExplorer implements Initializable {
    @FXML
    public TableView tableView;

    ObservableList<Location> listLocation;
    LocationProvider locationProvider = new LocationProvider();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listLocation = FXCollections.observableList(locationProvider.getAll());
        tableView.setItems(listLocation);


        tableView.getColumns().addAll(new Helper<Location>().getTableColumes(Location.class));

    }


    public void ajouter(ActionEvent actionEvent) {

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formLocationView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormLocationView controller1 = loader.getController();
        controller1.setOnFinished(c -> {
            locationProvider.insert(c);
            listLocation.add(c);

            stage.close();
        });

        stage.showAndWait();
    }

    public void modifier(ActionEvent actionEvent) {

        Location selectedItem = (Location) tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
            return;

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formLocationView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormLocationView controller1 = loader.getController();

        controller1.edit(selectedItem);
        controller1.setOnFinished(c -> {
            locationProvider.update(c);
            stage.close();
            tableView.setItems(listLocation);
        });

        stage.showAndWait();
    }

    public void suprimer(ActionEvent actionEvent) {
        Location c = (Location) tableView.getSelectionModel().getSelectedItem();
        if (c == null) return;
        locationProvider.delete(c);
        listLocation.remove(c);
        System.out.println("client supprimer");
    }

}
