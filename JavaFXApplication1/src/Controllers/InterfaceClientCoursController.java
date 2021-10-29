/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cours;
import Entities.Formation;
import Services.ServiceCours;
import Services.ServiceFormation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class InterfaceClientCoursController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private Formation selectedformation;
    @FXML
    private AnchorPane AnchorCours;
    @FXML
    private Label labTitle;
    @FXML
    private Label labDescription;
    @FXML
    private Label labNiveau;
    @FXML
    private Label labPrix;
    @FXML
    private TableColumn<Cours, String> colNomCours;
    @FXML
    private TableColumn<Cours, String> colDescriptionCours;
    @FXML
    private TableView<Cours> TableCourse;
    @FXML
    private Button BtnBack;
    ObservableList <Cours> list ;
    @FXML
    private Button BtnAffiche;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
             AfficherAllCours();
             
             // TODO
         } catch (SQLException ex) {
             Logger.getLogger(InterfaceClientCoursController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
        public void initData(Formation formation) throws FileNotFoundException, IOException, SQLException{     
        selectedformation = formation;  
                 
        labTitle.setText(selectedformation.getTitre());
        labDescription.setText(selectedformation.getDescription());
        labNiveau.setText(selectedformation.getNiveau());
               int pid=selectedformation.getPrix();
                 String Spid=String.valueOf(pid);
                 labPrix.setText(Spid);
       
       
    }
         private void AfficherAllCours() throws SQLException {
             
                 ServiceCours sF= new ServiceCours();
        List formation = (List) sF.AfficherAllCours();
        ObservableList et = FXCollections.observableArrayList(formation);
        colNomCours.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomcours"));
        colDescriptionCours.setCellValueFactory(new PropertyValueFactory<Cours, String>("descriptioncours"));
        TableCourse.setItems(et);}
         
        
      private void AfficherCours(Formation formation) throws SQLException {
          selectedformation = formation; 
        ServiceCours sc = new ServiceCours();
         int idFormation = selectedformation.getIdformation();
         System.out.print(selectedformation.getIdformation());
        list = sc.getAllCoursByFormation(selectedformation.getIdformation());
        
        colNomCours.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomcours"));
        colDescriptionCours.setCellValueFactory(new PropertyValueFactory<Cours, String>("descriptioncours"));
        TableCourse.setItems( list);
   
   
   }   

    @FXML
    private void Back(ActionEvent event) throws IOException {
                               Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceClientFormation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AfficherBtn(ActionEvent event) {
    }
}
