package com.pasrau.transcodage.csvWriter;

import java.io.FileWriter;
import java.io.IOException;

public interface csvDataFileWriter {
    void writeToCsv(FileWriter fileWriter) throws IOException;
}
