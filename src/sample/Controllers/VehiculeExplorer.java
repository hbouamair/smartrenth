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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Connection.Helper;
import sample.DataAcces.ClientProvider;
import sample.DataAcces.VehiculeProvider;
import sample.Models.Client;
import sample.Models.Vehicule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehiculeExplorer implements Initializable {

    @FXML
    public TableView tableView;
    public HBox btnContainer;


    ObservableList<Vehicule> listVoiture;
    VehiculeProvider vehiculeProvider = new VehiculeProvider();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!Helper.isAdmin)
            btnContainer.setVisible(false);
        
        listVoiture = FXCollections.observableList(vehiculeProvider.getAll());
        tableView.setItems(listVoiture);



        tableView.getColumns().addAll(new Helper<Vehicule>().getTableColumes(Vehicule.class));

    }


    public void ajouter(ActionEvent actionEvent) {

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formVehiculeView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormVehiculeView controller1 = loader.getController();
        controller1.setOnFinished(c -> {
            vehiculeProvider.insert(c);
            listVoiture.add(c);

            stage.close();
        });

        stage.showAndWait();
    }

    public void modifier(ActionEvent actionEvent) {

        Vehicule selectedItem = (Vehicule) tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
            return;

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formVehiculeView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormVehiculeView controller1 = loader.getController();

        controller1.edit(selectedItem);
        controller1.setOnFinished(c -> {
            vehiculeProvider.update(c);
            stage.close();
            tableView.setItems(listVoiture);
        });

        stage.showAndWait();
    }

    public void suprimer(ActionEvent actionEvent) {
        Vehicule c = (Vehicule) tableView.getSelectionModel().getSelectedItem();
        vehiculeProvider.delete(c);
        listVoiture.remove(c);
        System.out.println("client supprimer");
    }


}
