package com.pasrau.transcodage.dtos;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class RegularisationDto implements csvDataFileWriter {

    private String moisErreur;

    private String typeErreur;

    private String deltaRnf;

    private String tauxMoisErreur;

    private String montantdeltaPas;

    private String classeRevenuMoisErreur;

    private String codePrecisionClasseRevenu;

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
    public void writeToCsv(FileWriter writer) throws IOException {
         writer.append(moisErreur).append(";")
                 .append(typeErreur).append(";")
                 .append(deltaRnf).append(";")
                 .append(tauxMoisErreur).append(";")
                 .append(montantdeltaPas).append(";")
                 .append(classeRevenuMoisErreur).append(";")
                 .append(codePrecisionClasseRevenu).append(";")
                 .append(montantPrecision).append(";")
                 .append("\n");


    }
}
