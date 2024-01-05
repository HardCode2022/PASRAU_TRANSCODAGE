package com.pasrau.transcodage.step;

import com.pasrau.transcodage.config.TranscodagePropertiesConfig;
import com.pasrau.transcodage.csvWriter.csvDataFileWriter;
import com.pasrau.transcodage.dtos.BeneficiaireDto;
import com.pasrau.transcodage.dtos.ReglementDto;
import com.pasrau.transcodage.dtos.RegularisationDto;
import com.pasrau.transcodage.dtos.VersementDto;
import com.pasrau.transcodage.mapper.CustomFieldSetMapper;
import com.pasrau.transcodage.model.IndividuData;
import com.pasrau.transcodage.utils.ExtractionToCsvUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PasrauTranscodageWriter implements ItemWriter<List<IndividuData>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasrauTranscodageWriter.class);

    @Autowired
    private TranscodagePropertiesConfig transcodagePropertiesConfig;


    @Override
    public void write(List<? extends List<IndividuData>> allDatas) {
        LOGGER.info("Entrée dans le writer");
        //Recuperer le chemin de sortie des fichoiers CSV
        String cheminOutputFiles = transcodagePropertiesConfig.getOutputDir();
        Path path = Paths.get(cheminOutputFiles);
        //Constituer listes de beneficiaire qui sera envoyé en fichier
        List<BeneficiaireDto> beneficiaireDtos = new ArrayList<>();
        List<RegularisationDto> regularisationDtos = new ArrayList<>();
        List<VersementDto> versementDtos = new ArrayList<>();
        List<ReglementDto> reglementDtos = new ArrayList<>();

        // Traiter toutes les données
        ExtractionToCsvUtils.processAllData(allDatas, beneficiaireDtos, regularisationDtos, versementDtos, reglementDtos);
        // Extraire les bénéficiaires en fichier CSV
        extractToCsv(beneficiaireDtos, path, "BENEF", "GGV", "TEST");
        // Extraire les règlements en fichier CSV
        extractToCsv(reglementDtos, path, "REGL", "GGV", "TEST");
        // Extraire les règlements en fichier CSV
        extractToCsv(regularisationDtos, path, "RGUR", "GGV", "TEST");
        // Extraire les règlements en fichier CSV
        extractToCsv(versementDtos, path, "VER", "GGV", "TEST");
    }

    private <T extends csvDataFileWriter> void extractToCsv(List<T> dtos, Path path, String prefix, String ggv, String test) {
        // Extraire les données en fichier CSV
        ExtractionToCsvUtils.extractionCsv(dtos, path, prefix, ggv, test);
    }
}

