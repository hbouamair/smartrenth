package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataAcces.ClientProvider;
import sample.DataAcces.ManagerProvider;
import sample.Models.Client;
import sample.Models.Vehicule;

public class Main extends Application {


    // tatsmle nn
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/login.fxml"));
        primaryStage.setTitle("Hello World");
        // primaryStage.setScene(new Scene(root, 1024, 600));
        primaryStage.setScene(new Scene(root, 807, 600));
        primaryStage.show();
    } //


    public static void main(String[] args) {


        launch(args);


        //ClientProvider cp = new ClientProvider();
        //System.out.println(cp.connect("username", "pass"));


/*
        Vehicule v = new Vehicule("hjhjh", "color", "model", "mark");
        Vehicule vv = new Vehicule("ddd", "ddd", "model", "mark");

        v.startTracing();
        vv.startTracing();*/

    }
}
