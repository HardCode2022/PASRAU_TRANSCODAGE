package com.pasrau.transcodage.model;

public class Regularisation {

    /**
     *  Bloc S21.G00.56.001
     */
    private String moisErreur;
    /**
     *  Bloc S21.G00.56.002
     */
    private String typeErreur;
    /**
     *  Bloc S21.G00.56.003
     */
    private String deltaRnf;
    /**
     *  Bloc S21.G00.56.006
     */
    private String tauxMoisErreur;
    /**
     *  Bloc S21.G00.56.007
     */
    private String montantdeltaPas;
    /**
     *  Bloc S21.G00.56.011
     */
    private String classeRevenuMoisErreur;
    /**
     *  Bloc S21.G00.57.001
     */
    private String codePrecisionClasseRevenu;
    /**
     *  Bloc S21.G00.57.002
     */
    private String montantPrecision;

    public String getMoisErreur() {
        return moisErreur;
    }

    public void setMoisErreur(String moisErreur) {
        this.moisErreur = moisErreur;
    }

    public String getTypeErreur() {
        return typeErreur;
    }

    public void setTypeErreur(String typeErreur) {
        this.typeErreur = typeErreur;
    }

    public String getDeltaRnf() {
        return deltaRnf;
    }

    public void setDeltaRnf(String deltaRnf) {
        this.deltaRnf = deltaRnf;
    }

    public String getTauxMoisErreur() {
        return tauxMoisErreur;
    }

    public void setTauxMoisErreur(String tauxMoisErreur) {
        this.tauxMoisErreur = tauxMoisErreur;
    }

    public String getMontantdeltaPas() {
        return montantdeltaPas;
    }

    public void setMontantdeltaPas(String montantdeltaPas) {
        this.montantdeltaPas = montantdeltaPas;
    }

    public String getClasseRevenuMoisErreur() {
        return classeRevenuMoisErreur;
    }

    public void setClasseRevenuMoisErreur(String classeRevenuMoisErreur) {
        this.classeRevenuMoisErreur = classeRevenuMoisErreur;
    }

    public String getCodePrecisionClasseRevenu() {
        return codePrecisionClasseRevenu;
    }

    public void setCodePrecisionClasseRevenu(String codePrecisionClasseRevenu) {
        this.codePrecisionClasseRevenu = codePrecisionClasseRevenu;
    }

    public String getMontantPrecision() {
        return montantPrecision;
    }

    public void setMontantPrecision(String montantPrecision) {
        this.montantPrecision = montantPrecision;
    }

    @Override
    public String toString() {
        return "Regularisation{" +
                "moisErreur='" + moisErreur + '\'' +
                ", typeErreur='" + typeErreur + '\'' +
                ", deltaRnf='" + deltaRnf + '\'' +
                ", tauxMoisErreur='" + tauxMoisErreur + '\'' +
                ", montantdeltaPas='" + montantdeltaPas + '\'' +
                ", classeRevenuMoisErreur='" + classeRevenuMoisErreur + '\'' +
                '}';
    }
}
