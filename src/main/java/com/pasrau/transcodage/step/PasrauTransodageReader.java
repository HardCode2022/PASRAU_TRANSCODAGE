package com.pasrau.transcodage.step;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import com.pasrau.transcodage.config.TranscodagePropertiesConfig;
import com.pasrau.transcodage.model.FlatPasrauItemReader;
import com.pasrau.transcodage.model.IndividuData;
import com.pasrau.transcodage.utils.FilesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class PasrauTransodageReader implements ItemReader<FlatPasrauItemReader> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasrauTransodageReader.class);

    private final LineMapper<IndividuData> pasrauLineMapper;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver ;

    @Autowired
    private TranscodagePropertiesConfig transcodagePropertiesConfig;

    private FlatPasrauItemReaderComposite listItemReader;


    public PasrauTransodageReader(LineMapper<IndividuData> pasrauLineMapper) {
        this.pasrauLineMapper = pasrauLineMapper;
    }

    @Override
    public FlatPasrauItemReader read() throws Exception {
       if (null == listItemReader){
           listItemReader = pasrauTranscodageReader();
       }
           return listItemReader.read();
    }
    public FlatPasrauItemReaderComposite pasrauTranscodageReader() throws IOException {
        String cheminInputFiles = transcodagePropertiesConfig.getInputDir();
        Resource[] resources = resourcePatternResolver.getResources("file:" + cheminInputFiles + "*.pasrau");

        List<FlatPasrauItemReader> flatFileItemReaders = new ArrayList<>();

        for (Resource resource : resources) {
            if (resource.exists()) {
                String fileName = resource.getFilename();
                LOGGER.debug("Nom du fichier en entrée : {}", fileName);
                FlatPasrauItemReader itemReader = new FlatPasrauItemReader();
                File file = resource.getFile();
                File nouveauFichier = new File(Paths.get(transcodagePropertiesConfig.getArchiveFile(), fileName).toString());
                FilesUtils.movefile(file, nouveauFichier);

                itemReader.setName(fileName);
                itemReader.setEncoding(getFileCharset(nouveauFichier));
                itemReader.setResource(new FileUrlResource(nouveauFichier.getAbsolutePath()));
                itemReader.setCurrentResource(new FileUrlResource(nouveauFichier.getAbsolutePath()));
                itemReader.setLineMapper(pasrauLineMapper);
                itemReader.setPasrauLineMapper(pasrauLineMapper);
                itemReader.setFileName(fileName);
                flatFileItemReaders.add(itemReader);

            } else {
                LOGGER.debug("Aucun fichier en entrée dans le répertoire {}", resource.getURI());
            }
        }

        FlatPasrauItemReader[] readersArray = flatFileItemReaders.toArray(new FlatPasrauItemReader[0]);
        return new FlatPasrauItemReaderComposite(readersArray);
    }

    /**
     *
     * @param nouveauFichier nouveauFichier
     * @return charsetMatch
     * @throws IOException Exception
     */
    private String getFileCharset(File nouveauFichier) throws  IOException{
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(nouveauFichier))) {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(bufferedInputStream);
            CharsetMatch charsetMatch = detector.detect();
            if (charsetMatch==null){
                throw new IOException("Cannot detect source charset");
            }
            return charsetMatch.getName();
        }
    }
}

