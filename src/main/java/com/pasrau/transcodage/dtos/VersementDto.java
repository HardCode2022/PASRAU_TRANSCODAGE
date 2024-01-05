package com.pasrau.transcodage.dtos;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class VersementDto implements csvDataFileWriter {

    private  String dateDebutPeriode;
    private  String dateFinPeriode;

    private  String IdDroit;

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
    public void writeToCsv(FileWriter writer) throws IOException {
       writer.append(dateDebutPeriode).append(";")
               .append(dateFinPeriode).append(";")
               .append(IdDroit).append(";")
               .append(String.valueOf(montantBrut)).append(";")
               .append("\n");
    }
}
