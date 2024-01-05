package com.pasrau.transcodage.step;

import com.pasrau.transcodage.mapper.CustomFieldSetMapper;
import com.pasrau.transcodage.model.FlatPasrauItemReader;
import com.pasrau.transcodage.model.IndividuData;
import com.pasrau.transcodage.model.TranscodagePasrauDataStore;
import com.pasrau.transcodage.utils.TranscodageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class PasrauTranscodageProcessor implements ItemProcessor<FlatPasrauItemReader, List<IndividuData>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasrauTranscodageProcessor.class);

    // Utilisez le store pour stocker toutes les Individu par bloc 30.
    @Autowired
    @Qualifier("dataStore")
    private TranscodagePasrauDataStore transcodagePasrauDataStore;

    @Autowired
    @Qualifier("customerFieldMapper")
    private  CustomFieldSetMapper customFieldSetMapper ;

    @Override
    public List<IndividuData> process(FlatPasrauItemReader flatPasrauItemReader) throws Exception {
        LOGGER.debug("Début du traitement process pour le fichier : {}", flatPasrauItemReader.getFileName());

        // Utilisez le lineMapper pour extraire les données du FlatPasrauItemReader
        LineMapper<IndividuData> pasrauLineMapper = flatPasrauItemReader.getPasrauLineMapper();

        // Prétraitement pour compter le nombre de bloc (S21.G00.30.001)
        int expectedBloc30Count = TranscodageUtils.countBloc30(flatPasrauItemReader);

        // Utilisez le résultat du prétraitement pour configurer CustomFieldSetMapper
        customFieldSetMapper.setExpectedBloc30Count(expectedBloc30Count);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(flatPasrauItemReader.getCurrentResource().getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Mapper chaque ligne individuellement
                pasrauLineMapper.mapLine(line, 1);
                // Ajout de données null car stocker dans le store
               // transcodagePasrauDataStore.addPersons((List<IndividuData>) individus);
            }
        } catch (IOException e) {
            // Gérez les erreurs liées à la lecture du fichier'
            LOGGER.error("Erreur lors de la lecture du fichier", e);
        }

        customFieldSetMapper.ajoutInfosDernierBloc30Restant();

        return  transcodagePasrauDataStore.getAllPersons();
    }
}


