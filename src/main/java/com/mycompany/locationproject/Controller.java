package com.mycompany.locationproject;

import API.Intconnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CurrentManager;
import model.CurrentUser;

public class Controller implements Initializable {

    public AnchorPane panvoiture;
    @FXML
    private Pane panregistr;
    @FXML
    private Pane paneLogin;
    @FXML
    private Label btnerr;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField nomcp;
    @FXML
    private JFXTextField usr;
    @FXML
    private JFXTextField eml;
    @FXML
    private JFXPasswordField passw;


    @FXML
    private BorderPane clients;
    
    
    
    
    
    @FXML
    public void signUp(ActionEvent event) throws IOException {
          panregistr.toFront();
          paneLogin.setVisible(false);
    
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }
  Connection con = null;
  PreparedStatement  preparedstatement = null;
  ResultSet resultset = null;

    @FXML
    public void handlebutton(ActionEvent event) throws IOException, SQLException{ 
              String usernam = username.getText();
              String pass = password.getText();
              
              con = Intconnection.conDB();
              String sql= ("SELECT type FROM users WHERE username =" +"'"+usernam+"'"+"AND passwod ="+"'"+pass+"'");
              preparedstatement = con.prepareStatement(sql);
              resultset = preparedstatement.executeQuery();

              // CurrentUser.setId(resultset.getInt("Id"));
              if(usernam.isEmpty()|| pass.isEmpty()){
                  Alert alert=new Alert(AlertType.INFORMATION);
                  alert.setTitle("Error");
                  alert.setHeaderText(null);
                  alert.setContentText("Please enter all the information");
                  alert.showAndWait();
              }else{
             
              if(resultset.next()){
            String type =resultset.getString("type");  
                    CurrentUser.fullname =usernam;


                 if(type.equals("Manager")){
                     CurrentManager.fullname=usernam;
                     Node node = (Node) event.getSource();
                      Stage stage = (Stage) node.getScene().getWindow();
                      //stage.setMaximized(true);
                      stage.close();
                      Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/Managerlog.fxml")));
                      stage.setScene(scene);
                      stage.show();
                      
                                            }
                else if(type.equals("client")) {
                       Node node = (Node) event.getSource();
                      Stage stage = (Stage) node.getScene().getWindow();
                      //stage.setMaximized(true);
                      stage.close();
                      Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("/fxml/userslog.fxml")));
                      stage.setScene(scene);
                      stage.show();
                  }else{
                    
                     setLblError(Color.TOMATO, "Enter Correct Email/Password"); 
                }
                
                 
          }
              else{
                       setLblError(Color.TOMATO, "Enter Correct Email/Password"); 
              }
         }
    }
     public void setLblError(Color color, String text) {
       btnerr.setTextFill(color);
       btnerr.setText(text);
        System.out.println(text);
    }

    @FXML
    public  boolean Registration(ActionEvent event) throws SQLException {
        Connection con =Intconnection.conDB();
      String sql1= ("INSERT INTO  users(nomcomplet,username,email,passwod,type) VALUES (?,?,?,?,?)  ") ;
              PreparedStatement statement= con.prepareStatement(sql1);
             
            statement.setString(1, nomcp.getText());
             statement.setString(2, usr.getText());
              statement.setString(3, eml.getText());
              statement.setString(4, passw.getText()); 
              statement.setString(5, "client");
              
              int rs= statement.executeUpdate();
              
              if (rs!=0){
                  return true;
              }else{
                  return false;
              }
         

        
    }
    @FXML
    private void goback(MouseEvent event) {
         paneLogin.toFront();
           panregistr.toBack();
    }


    

    

    
    
}

