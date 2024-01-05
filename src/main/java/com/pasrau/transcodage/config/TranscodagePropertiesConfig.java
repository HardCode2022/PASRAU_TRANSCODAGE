package com.pasrau.transcodage.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
@ConfigurationProperties("transcodage.config")
public class TranscodagePropertiesConfig {

    public static String PATH_EEREUR="erreur/";

    private String inputDir;
    private String outputDir;
    private String archiveFile;
    private String erreurFichier;

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getArchiveFile() {
        return archiveFile;
    }

    public void setArchiveFile(String archiveFile) {
        this.archiveFile = archiveFile;
    }

    public String getErreurFichier() {
        return erreurFichier;
    }

    public void setErreurFichier(String erreurFichier) {
        this.erreurFichier = erreurFichier;
    }

    public String getCheminFichierOutputErreur(){
        return Paths.get(getOutputDir(),PATH_EEREUR).toString();
    }
}
