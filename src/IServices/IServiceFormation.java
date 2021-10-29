/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Formation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IServiceFormation {
        public void ajouterFormation (Formation f) throws SQLException ;
    public void supprimerFormation(int idformtion) throws SQLException;
    public boolean modifierFormation(Formation f,int idformtion) throws SQLException;
    public List<Formation> getAllFormations() throws SQLException ;
    public List<Formation> displayRecherche(String critere);
}
