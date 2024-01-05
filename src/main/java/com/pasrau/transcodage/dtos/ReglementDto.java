package com.pasrau.transcodage.dtos;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;

import java.io.FileWriter;
import java.io.IOException;

public class ReglementDto implements csvDataFileWriter {

    private String dateReglement;

    private Double montantRnf;

    private Double montantNet;

    private String tauxPas;

    private String typeTaux;

    private String idCrm;

    private Double montantPas;

    private String classerevenu;

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
    public void writeToCsv(FileWriter writer) throws IOException {
          writer.append(idCrm).append(";")
                .append(classerevenu).append(";")
                .append(tauxPas).append(";")
                .append(typeTaux).append(";")
                .append(dateReglement).append(";")
                .append(String.valueOf(montanRetenuSource)).append(";")
                .append(String.valueOf(montantNet)).append(";")
                .append(String.valueOf(montantPas)).append(";")
                .append(String.valueOf(montantRnf)).append(";")
                .append("\n");
    }
}
