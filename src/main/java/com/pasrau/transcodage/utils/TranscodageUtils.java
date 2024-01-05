package com.pasrau.transcodage.utils;

import com.pasrau.transcodage.model.FlatPasrauItemReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.pasrau.transcodage.constants.TranscodageConstantes.*;


public class TranscodageUtils {

    public static int countBloc30(FlatPasrauItemReader flatPasrauItemReader) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(flatPasrauItemReader.getCurrentResource().getInputStream()))) {
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(ConstantesIndividu.BLOC_S21_G00_30_001)) {
                    count++;
                }
            }
            return count;
        }
    }
}
