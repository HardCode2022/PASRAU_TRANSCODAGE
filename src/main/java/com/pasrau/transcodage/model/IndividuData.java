package com.pasrau.transcodage.model;

import java.util.List;

public class IndividuData {

    /**
     * Bloc S21.G00.30.001
     */
    private String nir;
    /**
    * Bloc S21.G00.30.002
    */
    private String nomFamille;
    /**
     *  Bloc S21.G00.30.003
     */
    private String nomUsage;
    /**
     *  Bloc S21.G00.30.004
     */
    private String prenom;
    /**
     *  Bloc S21.G00.30.005
     */
    private String sexe;
    /**
     *  Bloc S21.G00.30.006
     */
    private String dateNaissance;
    /**
     *  Bloc S21.G00.30.007
     */
    private String lieuNaissance;
    /**
     *  Bloc S21.G00.30.008
     */
    private String adresse;
    /**
     *  Bloc S21.G00.30.009
     */
    private String cp;
    /**
     *  Bloc S21.G00.30.010
     */
    private String localite;
    /**
     *  Bloc S21.G00.30.014
     */
    private String codeDeparetNaissance;
    /**
     *  Bloc S21.G00.30.015
     */
    private String codePaysNaissance;
    /**
     *  Bloc S21.G00.30.019
     */
    private String matricule;
    /**
     *  Bloc S21.G00.47.001
     */
    private String identifiantInterne;
    /**
     *  Bloc S21.G00.47.002
     */
    private String identifiantDroit;
    /**
     *  Bloc S21.G00.50
     */
    private List<Reglement> reglements;
    /**
     *  Bloc S21.G00.51
     */
    private List<Versement> versements;
    /**
     *  Bloc S21.G00.56
     */
    private List<Regularisation> regularisations;
    /**
     *  Bloc S21.G00.81
     */
    private List<Cotisation> cotisations ;

    public String getNir() {
        return nir;
    }

    public void setNir(String nir) {
        this.nir = nir;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Reglement> getReglements() {
        return reglements;
    }

    public void setReglements(List<Reglement> reglements) {
        this.reglements = reglements;
    }

    public List<Regularisation> getRegularisations() {
        return regularisations;
    }

    public void setRegularisations(List<Regularisation> regularisations) {
        this.regularisations = regularisations;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public List<Versement> getVersements() {
        return versements;
    }

    public void setVersements(List<Versement> versements) {
        this.versements = versements;
    }

    public List<Cotisation> getCotisations() {
        return cotisations;
    }

    public void setCotisations(List<Cotisation> cotisations) {
        this.cotisations = cotisations;
    }

    @Override
    public String toString() {
        return "PersonneData{" +
                "nir='" + nir + '\'' +
                ", nomFamille='" + nomFamille + '\'' +
                ", nomUsage='" + nomUsage + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", adresse='" + adresse + '\'' +
                ", cp='" + cp + '\'' +
                ", localite='" + localite + '\'' +
                ", codeDeparetNaissance='" + codeDeparetNaissance + '\'' +
                ", codePaysNaissance='" + codePaysNaissance + '\'' +
                ", matricule='" + matricule + '\'' +
                ", identifiantInterne='" + identifiantInterne + '\'' +
                ", identifiantDroit='" + identifiantDroit + '\'' +
                ", reglements=" + reglements +
                ", versements=" + versements +
                ", regularisations=" + regularisations +
                '}';
    }

}
