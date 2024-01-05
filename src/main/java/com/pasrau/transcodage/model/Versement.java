package com.pasrau.transcodage.model;

public class Versement {
    /**
     *  Bloc S21.G00.51.001
     */
    private  String dateDebutPeriode;
    /**
     *  Bloc S21.G00.51.002
     */
    private  String dateFinPeriode;
    /**
     *  Bloc S21.G00.51.010
     */
    private  String IdDroit;
    /**
     *  Bloc S21.G00.51.013
     */
    private  Double montantBrut;

    public String getDateDebutPeriode() {
        return dateDebutPeriode;
    }

    public void setDateDebutPeriode(String dateDebutPeriode) {
        this.dateDebutPeriode = dateDebutPeriode;
    }

    public String getDateFinPeriode() {
        return dateFinPeriode;
    }

    public void setDateFinPeriode(String dateFinPeriode) {
        this.dateFinPeriode = dateFinPeriode;
    }

    public String getIdDroit() {
        return IdDroit;
    }

    public void setIdDroit(String idDroit) {
        IdDroit = idDroit;
    }

    public Double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(Double montantBrut) {
        this.montantBrut = montantBrut;
    }

    @Override
    public String toString() {
        return "Versement{" +
                "dateDebutPeriode='" + dateDebutPeriode + '\'' +
                ", dateFinPeriode='" + dateFinPeriode + '\'' +
                ", IdDroit='" + IdDroit + '\'' +
                ", montantBrut='" + montantBrut + '\'' +
                '}';
    }
}
