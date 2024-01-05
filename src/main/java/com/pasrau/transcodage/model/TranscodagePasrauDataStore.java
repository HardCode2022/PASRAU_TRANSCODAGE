package com.pasrau.transcodage.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * MGU
 * Classe de stokage de données entre le mapper et le processor
 */
@Component
public class TranscodagePasrauDataStore {

    private final List<IndividuData> allPersons = new ArrayList<>();

    public List<IndividuData> getAllPersons() {
        return allPersons;
    }

    public void addPersons(List<IndividuData> persons) {
        if (persons!=null) {
            allPersons.addAll(persons);
        }
    }
}
