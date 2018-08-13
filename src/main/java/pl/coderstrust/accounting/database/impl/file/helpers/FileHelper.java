package pl.coderstrust.accounting.database.impl.file.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileHelper {

  private File databaseFile;

  public FileHelper(@Value("${filePath}") String path) {
    this.databaseFile = new File(path);
  }

  public void writeInvoice(String string) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(databaseFile.getPath(), true))) {
      bufferedWriter.write(string);
      bufferedWriter.newLine();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public List<String> readLines() {
    if (!databaseFile.exists()) {
      return new ArrayList<>();
    }
    try {
      return Files.lines(databaseFile.toPath()).collect(Collectors.toList());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  public void clearDatabaseFile() {
    try (PrintWriter printWriter = new PrintWriter(databaseFile.getName())) {
      printWriter.print("");
    } catch (FileNotFoundException exception) {
      exception.printStackTrace();
    }
  }
}