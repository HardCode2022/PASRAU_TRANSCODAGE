package com.pasrau.transcodage.utils;

import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.stereotype.Component;

@Component
public class CustomRecordSeparatorPolicy implements RecordSeparatorPolicy {

    private boolean isStartOfRecord = true;
    private boolean isBlock30Detected = false;

    @Override
    public boolean isEndOfRecord(String record) {
        if (record.startsWith("S21.G00.30.001")) {
            // Nouveau début d'enregistrement (bloc 30 détecté)
            isBlock30Detected = true;
            if (!isStartOfRecord) {
                // Fin de l'enregistrement précédent
                return true;
            } else {
                // Première ligne de l'enregistrement
                isStartOfRecord = false;
                return false;
            }
        } else if (isBlock30Detected && record.startsWith("S21.G00.30.001")) {
            // Fin de l'enregistrement dès qu'un nouveau bloc 30 est détecté
            isBlock30Detected = false;
            return true;
        }
        // Fin de fichier
        return true;
    }

    @Override
    public String postProcess(String record) {
        return record;
    }

    @Override
    public String preProcess(String record) {
        isStartOfRecord = true;
        return record;
    }

}
