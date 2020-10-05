package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DataAcces.ClientProvider;
import sample.Models.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientExplorer implements Initializable {
    @FXML
    public TableView tableView;


    ObservableList<Client> listClient;
    ClientProvider clientProvider = new ClientProvider();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*btnAjouter.setOnAction(event -> {
            Client c = new Client("nom", "prenom", "cin", "0600000", "sale skldjfslkd");
            c.setLogin("username");
            c.setPassword("pass");
            clientProvider.insert(c);
            listClient.add(c);

        });
        btnSupprimer.setOnAction(event -> {
            Client c = (Client) tableView.getSelectionModel().getSelectedItem();
            clientProvider.delete(c);
            listClient.remove(c);
            System.out.println(" sup");
        });
        btnModifer.setOnAction(event -> {

            System.out.println(" mod");
        });*/


        listClient = FXCollections.observableList(clientProvider.getAll());
        tableView.setItems(listClient);

        TableColumn<Client, String> columnNom = new TableColumn<>("Nom");
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Client, String> columnprenom = new TableColumn<>("prenom");
        columnprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));


        TableColumn<Client, String> columncin = new TableColumn<>("CIN");
        columncin.setCellValueFactory(new PropertyValueFactory<>("cin"));


        TableColumn<Client, String> columnid = new TableColumn<>("id");
        columnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Client, String> columntelephone = new TableColumn<>("telephone");
        columntelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        TableColumn<Client, String> columnadress = new TableColumn<>("Adresse");
        columnadress.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableView.getColumns().addAll(columnid, columnNom, columnprenom, columncin, columnadress, columntelephone);

    }


    public void ajouter(ActionEvent actionEvent) {

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formClientView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormClientView controller1 = loader.getController();
        controller1.setOnFinished(new FormClientView.onFinish() {
            @Override
            public void finish(Client c) {
                clientProvider.insert(c);
                listClient.add(c);

                stage.close();
            }
        });

        stage.showAndWait();
    }

    public void modifier(ActionEvent actionEvent) {

        Client selectedItem = (Client) tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
            return;

        Parent view = null;
        FXMLLoader loader = null;

        try {
            loader = new FXMLLoader(getClass().getResource("../Views/formClientView.fxml"));
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setScene(new Scene(view, 600, 400));

        FormClientView controller1 = loader.getController();

        controller1.edit(selectedItem);
        controller1.setOnFinished(c -> {
            clientProvider.update(c);
            stage.close();
            tableView.setItems(listClient);
         });

        stage.showAndWait();
    }

    public void suprimer(ActionEvent actionEvent) {
        Client c = (Client) tableView.getSelectionModel().getSelectedItem();
        clientProvider.delete(c);
        listClient.remove(c);
        System.out.println("client supprimer");
    }


}
