package com.pgalik.shawzinMacroGenerator.service;

import com.pgalik.shawzinMacroGenerator.model.Macro;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MacroFileSaver {
    private static final String directoryPath = "";
//    private static final String directoryPath = "C:\\";
//    private static final String directoryPath = "C:\\Program Files (x86)\\OSCAR Editor  X7\\OSCAR Editor  X7\\ScriptsMacros\\English\\MacroLibrary";

    public void saveMacroAs(final Macro macro, final String fileName) throws IOException {
        Path filePath = Path.of(directoryPath, fileName + ".amc");
        try {
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException ignored) {
        }
        final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()));
        writer.write(macro.toString());
        writer.close();
    }
}
