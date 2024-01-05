package com.pasrau.transcodage.dtos;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class BeneficiaireDto implements csvDataFileWriter {


    private String nir;

    private String nomFamille;

    private String nomUsage;

    private String prenom;

    private String sexe;

    private String dateNaissance;

    private String lieuNaissance;

    private String adresse;

    private String cp;

    private String localite;

    private String codeDeparetNaissance;

    private String codePaysNaissance;

    private String matricule;

    private String identifiantInterne;

    private String identifiantDroit;

    public String getNir() {
        return nir;
    }

    public void setNir(String nir) {
        this.nir = nir;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getNomUsage() {
        return nomUsage;
    }

    public void setNomUsage(String nomUsage) {
        this.nomUsage = nomUsage;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getCodeDeparetNaissance() {
        return codeDeparetNaissance;
    }

    public void setCodeDeparetNaissance(String codeDeparetNaissance) {
        this.codeDeparetNaissance = codeDeparetNaissance;
    }

    public String getCodePaysNaissance() {
        return codePaysNaissance;
    }

    public void setCodePaysNaissance(String codePaysNaissance) {
        this.codePaysNaissance = codePaysNaissance;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getIdentifiantInterne() {
        return identifiantInterne;
    }

    public void setIdentifiantInterne(String identifiantInterne) {
        this.identifiantInterne = identifiantInterne;
    }

    public String getIdentifiantDroit() {
        return identifiantDroit;
    }

    public void setIdentifiantDroit(String identifiantDroit) {
        this.identifiantDroit = identifiantDroit;
    }

    @Override
    public void writeToCsv(FileWriter writer) throws IOException {
        writer.append(nir).append(";")
                .append(nomFamille).append(";")
                .append(nomUsage).append(";")
                .append(prenom).append(";")
                .append(sexe).append(";")
                .append(dateNaissance).append(";")
                .append(lieuNaissance).append(";")
                .append(adresse).append(";")
                .append(cp).append(";")
                .append(localite).append(";")
                .append(codeDeparetNaissance).append(";")
                .append(codePaysNaissance).append(";")
                .append(matricule).append(";")
                .append(identifiantDroit).append(";")
                .append(identifiantInterne).append(";")
                .append("\n");
    }
}
