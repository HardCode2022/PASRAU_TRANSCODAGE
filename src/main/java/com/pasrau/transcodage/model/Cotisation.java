package com.pasrau.transcodage.model;

public class Cotisation {
    /**
     *  Bloc S21.G00.81.001
     */
    private String codeCotisation ;
    /**
     *  Bloc S21.G00.81.002
     */
    private String identifiantOrganisme;
    /**
     *  Bloc S21.G00.81.003
     */
    private Double montantBrut ;
    /**
     *  Bloc S21.G00.81.004
     */
    private Double montantCotisation;
    /**
     *  Bloc S21.G00.81.007
     */
    private String tauxCotisation ;

    public String getCodeCotisation() {
        return codeCotisation;
    }

    public void setCodeCotisation(String codeCotisation) {
        this.codeCotisation = codeCotisation;
    }

    public String getIdentifiantOrganisme() {
        return identifiantOrganisme;
    }

    public void setIdentifiantOrganisme(String identifiantOrganisme) {
        this.identifiantOrganisme = identifiantOrganisme;
    }

    public Double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(Double montantBrut) {
        this.montantBrut = montantBrut;
    }

    public Double getMontantCotisation() {
        return montantCotisation;
    }

    public void setMontantCotisation(Double montantCotisation) {
        this.montantCotisation = montantCotisation;
    }

    public String getTauxCotisation() {
        return tauxCotisation;
    }

    public void setTauxCotisation(String tauxCotisation) {
        this.tauxCotisation = tauxCotisation;
    }

    @Override
    public String toString() {
        return "Cotisation{" +
                "codeCotisation='" + codeCotisation + '\'' +
                ", identifiantOrganisme='" + identifiantOrganisme + '\'' +
                ", montantBrut=" + montantBrut +
                ", montantCotisation=" + montantCotisation +
                ", tauxCotisation='" + tauxCotisation + '\'' +
                '}';
    }
}
