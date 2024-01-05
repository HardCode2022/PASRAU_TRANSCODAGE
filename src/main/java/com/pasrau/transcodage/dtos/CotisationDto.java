package com.pasrau.transcodage.dtos;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CotisationDto implements csvDataFileWriter {

    private String codeCotisation ;

    private String identifiantOrganisme;

    private Double montantBrut ;

    private Double montantCotisation;

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
    public void writeToCsv(FileWriter fileWriter) throws IOException {

    }
}
