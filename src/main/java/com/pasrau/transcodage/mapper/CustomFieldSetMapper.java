package com.pasrau.transcodage.mapper;

import static com.pasrau.transcodage.constants.TranscodageConstantes.*;

import com.pasrau.transcodage.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author MGU
 * Classe de mapping des Blocs
 */
public class CustomFieldSetMapper implements FieldSetMapper<IndividuData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFieldSetMapper.class);

    @Autowired
    @Qualifier("dataStore")
    private TranscodagePasrauDataStore transcodagePasrauDataStore;
    private boolean lastBloc30Encountered;
    private IndividuData currentIndividu;
    private Reglement currentReglement;
    private Regularisation currentRegularisation;
    private Versement currentVersement;
    private Cotisation currentCotisation;
    private final List<IndividuData> currentIndividus = new ArrayList<>();
    private int currentBloc30Count = 0;
    private int expectedBloc30Count;
    @Autowired
    @Qualifier("executionContext")
    private ExecutionContext context;

    @Override
    public IndividuData mapFieldSet(FieldSet fieldSet)  throws BindException {
        LOGGER.info("Entrée dans le traitement de {}.", "CustomFieldSetMapper");

        String blockInfo = fieldSet.readString("blockInfo");
        String value = fieldSet.readString("value");

        //Verifier si nous sommes en presence d'un nouveau bloc 30 (Individu) pour ajouter et continuer le traitement
        ajoutDonneesIndividu(blockInfo);

        // Créer une nouvelle personne au début de chaque bloc 30
        if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_001)) {
            currentIndividu = new IndividuData();
            currentIndividu.setNir(value);
            currentIndividu.setRegularisations(new ArrayList<>());
            currentIndividu.setReglements(new ArrayList<>());
            currentIndividu.setVersements(new ArrayList<>());
            currentIndividu.setCotisations(new ArrayList<>());
        }
        // En fonction du type de bloc, ajouter les informations appropriées à L'individu
        // Ajoutez des conditions pour les autres champs que vous souhaitez extraire et mapper
        if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_002)) {
            currentIndividu.setNomFamille(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_003)) {
            currentIndividu.setNomUsage(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_004)) {
            currentIndividu.setPrenom(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_005)) {
            currentIndividu.setSexe(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_006)) {
            currentIndividu.setDateNaissance(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_007)) {
            currentIndividu.setLieuNaissance(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_008)) {
            currentIndividu.setAdresse(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_009)) {
            currentIndividu.setCp(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_010)) {
            currentIndividu.setLocalite(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_014)) {
            currentIndividu.setCodeDeparetNaissance(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_015)) {
            currentIndividu.setCodePaysNaissance(value);
        } else if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_019)) {
            currentIndividu.setMatricule(value);
        }
            //Traitement du bloc 50 des reglements
       else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50)) {
            extrareEtTraiterBloc50Individu(blockInfo, value);
            // Traitement du Bloc 56 associé à individu
        } else if (blockInfo.startsWith(ConstantesRegularisations.BLOC_S21_G00_56)) {
            extraireBloc56RegularisationIndividu(blockInfo, value);
            // Traitement du Bloc 51 associé à individu
        } else if (blockInfo.startsWith(ConstantesVersements.BLOC_S21_G00_51) ) {
            extraireBloc51VersementsIndividu(blockInfo, value);
       }else if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81) ) {
             extraireBloc81CotisationsIndividu(blockInfo, value);
        }
        return null;
    }
    /**
     * Permettre de traiter tous les blocs 81(Cotisations) associés à un Individu
     * @param blockInfo blockInfo
     * @param value value
     */
    private void extraireBloc81CotisationsIndividu(String blockInfo, String value) {
        if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81_001)){
            // Bloc 81 associé à la personne
            currentCotisation = new Cotisation();
            currentCotisation.setCodeCotisation(value);
        }else if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81_002)){
            currentCotisation.setIdentifiantOrganisme(value);
        }else if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81_003)){
            currentCotisation.setMontantBrut(Double.valueOf(value.replace("'", "")));
        }else if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81_004)){
            currentCotisation.setMontantCotisation(Double.valueOf(value.replace("'", "")));
        }else if (blockInfo.startsWith(ConstantesCotisations.BLOC_S21_G00_81_007)){
            currentCotisation.setTauxCotisation(value);
        }

        // Vérifier si une cotisatioin avec les mêmes informations existe déjà
        boolean existingCotisation = currentIndividu.getCotisations().stream()
                .anyMatch(c -> Objects.equals(c.getCodeCotisation(), currentCotisation.getCodeCotisation())
                        && Objects.equals(c.getIdentifiantOrganisme(), currentCotisation.getIdentifiantOrganisme())
                        && Objects.equals(c.getMontantBrut(), currentCotisation.getMontantBrut())
                        && Objects.equals(c.getMontantCotisation(), currentCotisation.getMontantCotisation())
                        && Objects.equals(c.getTauxCotisation(), currentCotisation.getTauxCotisation())
                );

        if (!existingCotisation) {
            currentIndividu.getCotisations().add(currentCotisation);
        }
    }

    /**
     * Permettre de traiter tous les blocs 51(Versements) associés à un Individu
     * @param blockInfo blockInfo
     * @param value value
     */
    private void extraireBloc51VersementsIndividu(String blockInfo, String value) {
        if (blockInfo.startsWith(ConstantesVersements.BLOC_S21_G00_51_001)){
            // Bloc 56 associé à la personne
            currentVersement = new Versement();
            currentVersement.setDateDebutPeriode(value);
        }else if (blockInfo.startsWith(ConstantesVersements.BLOC_S21_G00_51_002)){
            currentVersement.setDateFinPeriode(value);
        }else if (blockInfo.startsWith(ConstantesVersements.BLOC_S21_G00_51_010)){
            currentVersement.setIdDroit(value);
        }else if (blockInfo.startsWith(ConstantesVersements.BLOC_S21_G00_51_013)){
            currentVersement.setMontantBrut(Double.valueOf(value.replace("'", "")));
        }

        // Vérifier si une régularisation avec les mêmes informations existe déjà
        boolean existingVersement = currentIndividu.getVersements().stream()
                .anyMatch(v -> Objects.equals(v.getDateDebutPeriode(), currentVersement.getDateDebutPeriode())
                        && Objects.equals(v.getDateFinPeriode(), currentVersement.getDateFinPeriode())
                        && Objects.equals(v.getIdDroit(), currentVersement.getIdDroit())
                        && Objects.equals(v.getMontantBrut(), currentVersement.getMontantBrut())
                );

        if (!existingVersement) {
            currentIndividu.getVersements().add(currentVersement);
        }
    }

    /**
     * Permettre de traiter tous les blocs 56(Regularisation) associés à un Individu
     * @param blockInfo blockInfo
     * @param value value
     */
    private void extraireBloc56RegularisationIndividu(String blockInfo, String value) {
        if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_001)){
            // Bloc 56 associé à la personne
            currentRegularisation = new Regularisation();
            currentRegularisation.setMoisErreur(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_002)){
            currentRegularisation.setTypeErreur(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_003)){
            currentRegularisation.setDeltaRnf(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_006)){
            currentRegularisation.setTauxMoisErreur(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_007)){
            currentRegularisation.setMontantdeltaPas(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_011)){
            currentRegularisation.setClasseRevenuMoisErreur(value);
        }

        // Vérifier si une régularisation avec les mêmes informations existe déjà
        boolean existingRegularisation = currentIndividu.getRegularisations().stream()
                .anyMatch(r -> Objects.equals(r.getMoisErreur(), currentRegularisation.getMoisErreur())
                        && Objects.equals(r.getTypeErreur(), currentRegularisation.getTypeErreur())
                        && Objects.equals(r.getDeltaRnf(), currentRegularisation.getDeltaRnf())
                        && Objects.equals(r.getTauxMoisErreur(), currentRegularisation.getTauxMoisErreur())
                        && Objects.equals(r.getMontantdeltaPas(), currentRegularisation.getMontantdeltaPas())
                        && Objects.equals(r.getClasseRevenuMoisErreur(), currentRegularisation.getClasseRevenuMoisErreur())
                );

        if (!existingRegularisation) {
            currentIndividu.getRegularisations().add(currentRegularisation);
        }
    }

    /**
     * Permettre de traiter tous les blocs 50(Reglements) associés à un Individu
     * @param blockInfo blockInfo
     * @param value value
     */
    private void extrareEtTraiterBloc50Individu(String blockInfo, String value) {

        if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_001)){
            // Bloc 56 associé à la personne
           currentReglement = new Reglement();
           currentReglement.setDateReglement(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_002)){
            currentReglement.setMontantRnf(Double.valueOf(value.replace("'", "")));
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_004)){
            currentReglement.setMontantNet(Double.valueOf(value.replace("'", "")));
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_006)){
            currentReglement.setTauxPas(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_007)){
            currentReglement.setTypeTaux(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_008)){
            currentReglement.setIdCrm(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_009)){
            currentReglement.setMontantPas(Double.valueOf(value.replace("'", "")));
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_014)){
            currentReglement.setClasserevenu(value);
        }else if (blockInfo.startsWith(ConstantesReglements.BLOC_S21_G00_50_019)){
            currentReglement.setMontanRetenuSource(Double.valueOf(value.replace("'", "")));
        }
        // Vérifier si un reglement avec les mêmes informations existe déjà
        boolean existingReglement = currentIndividu.getReglements().stream()
                .anyMatch(r -> Objects.equals(r.getDateReglement(), currentReglement.getDateReglement())
                        && Objects.equals(r.getMontantRnf(), currentReglement.getMontantRnf())
                        && Objects.equals(r.getMontantNet(), currentReglement.getMontantNet())
                        && Objects.equals(r.getTauxPas(), currentReglement.getTauxPas())
                        && Objects.equals(r.getTypeTaux(), currentReglement.getTypeTaux())
                        && Objects.equals(r.getIdCrm(), currentReglement.getIdCrm())
                        && Objects.equals(r.getMontantPas(), currentReglement.getMontantPas())
                        && Objects.equals(r.getClasserevenu(), currentReglement.getClasserevenu())
                        && Objects.equals(r.getMontanRetenuSource(), currentReglement.getMontanRetenuSource())
                );
        // Verifier que les infos de reglèments  ne sont pas les memes avant de les ajoutés
        if (!existingReglement) {
            currentIndividu.getReglements().add(currentReglement);
        }
    }

    /**
     * Ajout de l'invidu une fois qu'il est constituer et que un nouveau bloc 30 est detecter
     * @param blockInfo blockInfo
     */
    private void ajoutDonneesIndividu(String blockInfo) {
        if (blockInfo.startsWith(ConstantesIndividu.BLOC_S21_G00_30_001)) {
            currentBloc30Count++;
            lastBloc30Encountered = (currentBloc30Count == expectedBloc30Count);
            // Si l'individu en cours de traitement est constituer en non vide , alors on l'ajoute
            if (currentIndividu != null) {
                currentIndividus.add(currentIndividu);
                transcodagePasrauDataStore.addPersons(currentIndividus);
                currentIndividus.clear();
            }
        }
    }

    /**
     * Ajout du dernier individu contenu dans le fichier pasrau car, il n'y aura plus d'autre bloc 30 le suivant
     */
    public void ajoutInfosDernierBloc30Restant() {
        // Traitement du dernier bloc 30 après avoir parcouru toutes les lignes du fichier
        if (lastBloc30Encountered && currentIndividu != null) {
            currentIndividus.add(currentIndividu);
            transcodagePasrauDataStore.addPersons(currentIndividus);
            currentIndividus.clear();
        }
    }

    public boolean isLastBloc30Encountered() {
        return lastBloc30Encountered;
    }

    public void setExpectedBloc30Count(int expectedBloc30Count) {
        this.expectedBloc30Count = expectedBloc30Count;
    }
}