package com.pasrau.transcodage.model;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.core.io.Resource;

public class FlatPasrauItemReader extends FlatFileItemReader<IndividuData> {

    private String fileName;

    private LineMapper<IndividuData> pasrauLineMapper;

    private Resource currentResource;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LineMapper<IndividuData> getPasrauLineMapper() {
        return pasrauLineMapper;
    }

    public void setPasrauLineMapper(LineMapper<IndividuData> pasrauLineMapper) {
        this.pasrauLineMapper = pasrauLineMapper;
    }

    @Override
    protected void doOpen() throws Exception {
        super.doOpen();
        // Obtenez la ressource actuelle lors de l'ouverture du lecteur
        currentResource = getCurrentResource();
    }

    public Resource getCurrentResource() {
        return currentResource;
    }

    public void setCurrentResource(Resource currentResource) {
        this.currentResource = currentResource;
    }
}
