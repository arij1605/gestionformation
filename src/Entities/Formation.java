/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Formation {
    private int idformation;
    private String titre;
    private String description;
    private String niveau;
    private int prix ;



    public int getIdformation() {
        return idformation;
    }

    public void setIdformation(int idformation) {
        this.idformation = idformation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Formation(int idformation, String titre, String description, String niveau, int prix) {
        this.idformation = idformation;
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.prix = prix;
    }

    public Formation(String titre, String description, String niveau, int prix) {
        this.titre = titre;
        this.description = description;
        this.niveau = niveau;
        this.prix = prix;
    }

    public Formation() {
    } 
}
