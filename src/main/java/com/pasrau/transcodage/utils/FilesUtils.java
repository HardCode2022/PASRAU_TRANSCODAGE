package com.pasrau.transcodage.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FilesUtils {
    public FilesUtils() {
    }


    public static  void movefile(File sourceFile , File destFile) throws IOException {
        File fileparent = destFile.getParentFile();
        if (!fileparent.exists()){
            fileparent.mkdirs();
        }
        Files.move(sourceFile.toPath(),destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
