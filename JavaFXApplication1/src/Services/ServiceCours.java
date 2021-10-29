/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Cours;
import IServices.IServiceCours;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public class ServiceCours implements IServiceCours{
    
           Connection connexion;
ObservableList<Cours> CoursList = FXCollections.observableArrayList();
    public ServiceCours() {
        connexion = MyDB.getInstance().getConnection();
    }
        public Boolean chercher(int id) {
        String req ="select * from cours";
        List<Integer> list =new ArrayList<>();
         try {
             Statement st=connexion.createStatement();
             ResultSet rs =st.executeQuery(req);
               while(rs.next())
               {       
 
        list.add(rs.getInt(1));
               
               }
         } catch (SQLException ex) {
             Logger.getLogger(Cours.class.getName()).log(Level.SEVERE, null, ex);
         }
        return list.contains(id);
    } 

         public ObservableList<Cours> AfficherAllCours() throws SQLException {
        String req = "SELECT * FROM `cours`";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
     
           CoursList.add(new Cours(
                          rst.getInt("idcours")  ,
                   rst.getString("nomcours")  ,
                    rst.getString("descriptioncours"),
                    rst.getInt("formation_id")
                ));
        }
        return CoursList;
}
    @Override
    public void ajouterCours(Cours c) throws SQLException {
         try {
            Statement stm = connexion.createStatement();
                    String query = "INSERT INTO `cours`( `nomcours`, `descriptioncours`,`formation_id`) VALUES "
                            + "('"+c.getNomcours()+"','"+c.getDescriptioncours()+"','"+c.getFormation_id()+"')";
                    stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean supprimerCours(int idcours) throws SQLException {
          if(chercher(idcours)){
        try {
               PreparedStatement pstmt = connexion.prepareStatement("delete from cours where idcours= ?;");
       
             pstmt.setInt(1, idcours);
             pstmt.execute();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
         }
             System.out.println("delete valide");
         return true;
         }
         System.out.println("commentaire deleted n existe pas");
         return false; 
    }

    @Override
    public boolean modifierCours(Cours c, int idcours) throws SQLException {
              if(chercher(idcours)){
      try {
                    String query = "UPDATE cours SET nomcours =? ,descriptioncours =? WHERE idcours=? ";
                    PreparedStatement pstmt = connexion.prepareStatement(query);
                     pstmt.setString(1, c.getNomcours());
                      pstmt.setString(2, c.getDescriptioncours());
          
                    pstmt.setInt(3, idcours);

                    pstmt.executeUpdate();
                   

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCours.class.getName()).log(Level.SEVERE, null, ex);
        }  
      System.out.println("update valide");
         return true;
         }System.out.println("update invalid: Cours nexiste pas");
        return false;    }

    
        public Cours getServiceById(int id) throws SQLException {
        Cours ls = new Cours();
        Statement st = connexion.createStatement();
        String req = "SELECT * FROM cours where idcours=" + id;
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            ls = new Cours(rs.getInt(1),
                    rs.getString("nomcours"));
        }
        return ls;
    }
        
    @Override
    public ObservableList <Cours> getAllCoursByFormation(int idformation) throws SQLException {
                ObservableList <Cours> ls = (ObservableList <Cours>) new ArrayList<Cours>();
        Statement st = connexion.createStatement();
        String req = "SELECT * FROM cours where formation_id=" + idformation;
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            ls.add(this.getServiceById(rs.getInt("idcours")));
        }
        return ls;   }

    @Override
    public List<Cours> getAllCours() throws SQLException {
        
        String req = "SELECT * FROM `cours`";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
     
           CoursList.add(new Cours(
                          rst.getInt("idcours")  ,
                   rst.getString("nomcours")  ,
                    rst.getString("descriptioncours"),
                    rst.getInt("formation_id")));
        }
        return CoursList;
    }
    
}
