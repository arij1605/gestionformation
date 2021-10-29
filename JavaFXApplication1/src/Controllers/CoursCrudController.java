/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Cours;
import Services.ServiceCours;
import Services.ServiceFormation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CoursCrudController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfformation;
    @FXML
    private TableView<?> tvformation;
    @FXML
    private TableColumn<Cours, Integer> colId;
    @FXML
    private TableColumn<Cours, String> colTitre;
    @FXML
    private TableColumn<Cours, String> colDescription;
  
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button BtnBack;
    @FXML
    private Button btnajouter;
    @FXML
    private TableColumn<Cours, Integer> colFormation;

    Cours cc = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                try {
            // TODO preload Data on the table
            AfficherCours();
        } catch (SQLException ex) {
            Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);

        }
        tvformation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cc = (Cours) tvformation.getSelectionModel().getSelectedItem();
                System.out.println(cc);
                
                int cid = cc.getIdcours();
                String Spid = String.valueOf(cid);
                tfNom.setText(cc.getNomcours());  
                tfdescription.setText(cc.getDescriptioncours());
                int pprice = (int) cc.getFormation_id();
                String Spprice = String.valueOf(pprice);
                tfformation.setText(Spprice);
               
                
            }
        });
    }    
     private void AfficherCours() throws SQLException {
         ServiceCours sc = new ServiceCours();
        List cours = (List) sc.getAllCours();
        ObservableList et = FXCollections.observableArrayList(cours);
        colId.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("idcours"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Cours, String>("nomcours"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Cours, String>("descriptioncours"));
        colFormation.setCellValueFactory(new PropertyValueFactory<Cours, Integer>("formation_id"));
        tvformation.setItems(et);}
    

    @FXML
    private void ModifierCours(ActionEvent event)  throws SQLException {
        ServiceCours sC= new ServiceCours();
         System.out.println(cc);
        if (cc == null) {
            JOptionPane.showMessageDialog(null, "choisir formation");

        } else {
            String title = tfNom.getText();
            String description = tfdescription.getText();
            
            int formation = Integer.parseInt(tfformation.getText());
       
            sC.modifierCours(new Cours(title, description, formation), cc.getIdcours());

            Notifications notificationBuilder = Notifications.create().title("cours modifié").graphic(null)
            	.hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
            tfNom.clear();
            tfdescription.clear();
            tfformation.clear();

            cc = null;
            try {
                AfficherCours();
              
            } catch (SQLException ex) {
                Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void BackAdmin(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceAdminFormation.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void SupprimerCours(ActionEvent event)  throws SQLException {
           ServiceCours sC = new ServiceCours();
                Cours c= (Cours) tvformation.getSelectionModel().getSelectedItem();
        System.out.println(c);
        if (cc == null) {
            JOptionPane.showMessageDialog(null, "choisir Cours");

        } else {
            sC.supprimerCours(cc.getIdcours());

            try { //afficher au fur et a mesure avec l'ajout
                AfficherCours();
            } catch (SQLException ex) {
                Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

             Notifications notificationBuilder = Notifications.create().title("Cours Supprimer").graphic(null)
            	.hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
            tfNom.clear();
            tfdescription.clear();
            tfformation.clear();
            cc = null;
        }
    }

    @FXML
    private void AjouterCours(ActionEvent event) throws SQLException {
              ServiceCours sC= new ServiceCours();
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Ajout de Cours");
        String titleval = tfNom.getText().toString().toLowerCase();
        String descrival = tfdescription.getText().toString().toLowerCase();
      
        String formval = tfformation.getText().toString().toLowerCase();
        boolean verif = true;
        System.out.println("Initialize Done !");
   
     
          Cours cours = new Cours(titleval,descrival,Integer.parseInt(formval));
                 System.out.println(cours);
            System.out.println("Cours Initialized !");
            sC.ajouterCours(cours);
            System.out.println("Formation Added Done !");
            Notifications notificationBuilder = Notifications.create().title("Cours ajouté").graphic(null)
            		.hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_LEFT);
            notificationBuilder.show();
        

        try {
            AfficherCours();
        } catch (SQLException ex) {
            Logger.getLogger(FormationsCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        tfNom.clear();
        tfdescription.clear();
        tfformation.clear();
       
    }
    
}
