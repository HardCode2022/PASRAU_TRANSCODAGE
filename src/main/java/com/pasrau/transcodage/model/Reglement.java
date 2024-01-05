package com.pasrau.transcodage.model;

public class Reglement {
    /**
     *  Bloc S21.G00.50.001
     */
    private String dateReglement;
    /**
     *  Bloc S21.G00.50.002
     */
    private Double montantRnf;
    /**
     *  Bloc S21.G00.50.004
     */
    private Double montantNet;
    /**
     *  Bloc S21.G00.50.006
     */
    private String tauxPas;
    /**
     *  Bloc S21.G00.50.007
     */
    private String typeTaux;
    /**
     *  Bloc S21.G00.50.008
     */
    private String idCrm;
    /**
     *  Bloc S21.G00.50.009
     */
    private Double montantPas;
    /**
     *  Bloc S21.G00.50.014
     */
    private String classerevenu;
    /**
     *  Bloc S21.G00.50.019
     */
    private Double montanRetenuSource;

    public String getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(String dateReglement) {
        this.dateReglement = dateReglement;
    }

    public Double getMontantRnf() {
        return montantRnf;
    }

    public void setMontantRnf(Double montantRnf) {
        this.montantRnf = montantRnf;
    }

    public Double getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(Double montantNet) {
        this.montantNet = montantNet;
    }

    public String getTauxPas() {
        return tauxPas;
    }

    public void setTauxPas(String tauxPas) {
        this.tauxPas = tauxPas;
    }

    public String getTypeTaux() {
        return typeTaux;
    }

    public void setTypeTaux(String typeTaux) {
        this.typeTaux = typeTaux;
    }

    public String getIdCrm() {
        return idCrm;
    }

    public void setIdCrm(String idCrm) {
        this.idCrm = idCrm;
    }

    public Double getMontantPas() {
        return montantPas;
    }

    public void setMontantPas(Double montantPas) {
        this.montantPas = montantPas;
    }

    public String getClasserevenu() {
        return classerevenu;
    }

    public void setClasserevenu(String classerevenu) {
        this.classerevenu = classerevenu;
    }

    public Double getMontanRetenuSource() {
        return montanRetenuSource;
    }

    public void setMontanRetenuSource(Double montanRetenuSource) {
        this.montanRetenuSource = montanRetenuSource;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "dateVersement='" + dateReglement + '\'' +
                ", montantRnf='" + montantRnf + '\'' +
                ", montantNet='" + montantNet + '\'' +
                ", tauxPas='" + tauxPas + '\'' +
                ", typeTaux='" + typeTaux + '\'' +
                ", idCrm='" + idCrm + '\'' +
                ", montantPas='" + montantPas + '\'' +
                ", classerevenu='" + classerevenu + '\'' +
                ", montanRetenuSource='" + montanRetenuSource + '\'' +
                '}';
    }
}
