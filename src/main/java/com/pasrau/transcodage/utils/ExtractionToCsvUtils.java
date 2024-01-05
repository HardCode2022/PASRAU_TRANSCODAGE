package com.pasrau.transcodage.utils;

import com.pasrau.transcodage.csvWriter.csvDataFileWriter;
import com.pasrau.transcodage.dtos.BeneficiaireDto;
import com.pasrau.transcodage.dtos.ReglementDto;
import com.pasrau.transcodage.dtos.RegularisationDto;
import com.pasrau.transcodage.dtos.VersementDto;
import com.pasrau.transcodage.model.IndividuData;
import com.pasrau.transcodage.model.Reglement;
import com.pasrau.transcodage.model.Regularisation;
import com.pasrau.transcodage.model.Versement;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class ExtractionToCsvUtils {

         public static final String PATTERN_DATE_YYYYMMDD_HHMMSS = "yyyyMMdd HHmmss";

        public static void extractionCsv(List<? extends csvDataFileWriter> csvDataList, Path normalizedPath, String codeEntite, String codeSA, String fileNamePrefix) {
            String timesTamp = getTimesTamp(LocalDateTime.now()).replace(":", "");

            String nomfichier = codeSA + "_" + codeEntite + "_" + fileNamePrefix + timesTamp + ".csv";

           Path filePath = getPath(normalizedPath, nomfichier);

            try (FileWriter writerFile = new FileWriter(filePath.toFile())) {
                // Vider le contenu du fichier avant écriture
                writerFile.write("");
                csvDataList.forEach(csvData->{
                    // Écrire les données dans le fichier CSV
                    try {
                        csvData.writeToCsv(writerFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static Path getPath(Path normalizedPath, String nomfichier) {
            return normalizedPath.resolve(nomfichier);
        }

        private static String getTimesTamp(LocalDateTime pLocalDateTime) {
            String lDateYYYYMMDDHHMMSS = PATTERN_DATE_YYYYMMDD_HHMMSS.format(String.valueOf(pLocalDateTime));
            return String.format("%s%s", "_" ,lDateYYYYMMDDHHMMSS);
        }

    /**
     * Transfere de données Beneficiaire
     * @param beneficiaireDtos beneficiaireDtos
     * @param individuData individuData
     */
    public static void transfereDonneesBeneficiairePourExtraction(List<BeneficiaireDto> beneficiaireDtos, IndividuData individuData) {
        BeneficiaireDto beneficiaireDto = new BeneficiaireDto();
        beneficiaireDto.setNir(individuData.getNir());
        beneficiaireDto.setNomFamille(individuData.getNomFamille());
        beneficiaireDto.setNomUsage(individuData.getNomUsage());
        beneficiaireDto.setPrenom(individuData.getPrenom());
        beneficiaireDto.setSexe(individuData.getSexe());
        beneficiaireDto.setDateNaissance(individuData.getDateNaissance());
        beneficiaireDto.setLieuNaissance(individuData.getLieuNaissance());
        beneficiaireDto.setAdresse(individuData.getAdresse());
        beneficiaireDto.setCp(individuData.getCp());
        beneficiaireDto.setLocalite(individuData.getLocalite());
        beneficiaireDto.setCodeDeparetNaissance(individuData.getCodeDeparetNaissance());
        beneficiaireDto.setCodePaysNaissance(individuData.getCodePaysNaissance());
        beneficiaireDto.setMatricule(individuData.getMatricule());
        beneficiaireDto.setIdentifiantInterne(individuData.getIdentifiantInterne());
        beneficiaireDto.setIdentifiantDroit(individuData.getIdentifiantDroit());
        beneficiaireDtos.add(beneficiaireDto);
    }

    /**
     * Transfere de données reglements
     * @param reglementDtos reglementDtos
     * @param regl regl
     */
    public static void transfereDonneesReglementPourExtraction(List<ReglementDto> reglementDtos, Reglement regl) {
            ReglementDto reglementdto = new ReglementDto();
            reglementdto.setIdCrm(regl.getIdCrm());
            reglementdto.setMontantPas(regl.getMontantPas());
            reglementdto.setMontantNet(regl.getMontantNet());
            reglementdto.setMontantRnf(regl.getMontantRnf());
            reglementdto.setMontanRetenuSource(regl.getMontanRetenuSource());
            reglementdto.setDateReglement(regl.getDateReglement());
            reglementdto.setClasserevenu(regl.getClasserevenu());
            reglementdto.setTypeTaux(regl.getTypeTaux());
            reglementdto.setTauxPas(regl.getTauxPas());
            reglementDtos.add(reglementdto);
    }

    /**
     * Transfere de données regularisations
     * @param regularisationDtos regularisationDtos
     * @param regularisation regularisation
     */
    public static void transfereDonneesRegularisationPourExtraction(List<RegularisationDto> regularisationDtos, Regularisation regularisation) {
        RegularisationDto regularisationDto = new RegularisationDto();
        regularisationDto.setMoisErreur(regularisation.getMoisErreur());
        regularisationDto.setTypeErreur(regularisation.getTypeErreur());
        regularisationDto.setDeltaRnf(regularisation.getDeltaRnf());
        regularisationDto.setClasseRevenuMoisErreur(regularisation.getClasseRevenuMoisErreur());
        regularisationDto.setMontantdeltaPas(regularisation.getMontantdeltaPas());
        regularisationDto.setCodePrecisionClasseRevenu(regularisation.getCodePrecisionClasseRevenu());
        regularisationDto.setTauxMoisErreur(regularisation.getTauxMoisErreur());
        regularisationDto.setMontantPrecision(regularisation.getMontantPrecision());
        regularisationDtos.add(regularisationDto);
    }

    /**
     * Transfere de données versements
     * @param versementDtos versementDtos
     * @param versement versement
     */
    public static void transfereDonneesVersementPourExtraction(List<VersementDto> versementDtos, Versement versement) {
        VersementDto versementDto = new VersementDto();
        versementDto.setDateDebutPeriode(versement.getDateDebutPeriode());
        versementDto.setDateFinPeriode(versement.getDateFinPeriode());
        versementDto.setIdDroit(versement.getIdDroit());
        versementDto.setMontantBrut(versement.getMontantBrut());
        versementDtos.add(versementDto);
    }

    /**
     * Proceder au transfere de données au niveau de chaque DTOs
     * @param allDatas allDatas
     * @param beneficiaireDtos beneficiaireDtos
     * @param regularisationDtos regularisationDtos
     * @param versementDtos versementDtos
     * @param reglementDtos reglementDtos
     */
    public static void processAllData(List<? extends List<IndividuData>> allDatas, List<BeneficiaireDto> beneficiaireDtos, List<RegularisationDto> regularisationDtos, List<VersementDto> versementDtos, List<ReglementDto> reglementDtos) {
        allDatas.forEach(datas -> datas.forEach(individuData -> {
            // Gerer les bénéficiaires pour extraction
            ExtractionToCsvUtils.transfereDonneesBeneficiairePourExtraction(beneficiaireDtos, individuData);
            // Gerer les reglements pour extraction
            individuData.getReglements().forEach(reglement -> {
                ExtractionToCsvUtils.transfereDonneesReglementPourExtraction(reglementDtos, reglement);
            });
           // Gerer les regularisations pour extraction
            individuData.getRegularisations().forEach(regularisation -> {
                ExtractionToCsvUtils.transfereDonneesRegularisationPourExtraction(regularisationDtos, regularisation);
            });
            // Gerer les versements pour extraction
            individuData.getVersements().forEach(versement -> {
                ExtractionToCsvUtils.transfereDonneesVersementPourExtraction(versementDtos, versement);
            });
        }));
    }
}
