package pl.coderstrust.accounting.database.impl.file.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileHelper {

  private File dataBaseFile;
  private File temporaryDataBaseFile = new File("invoices.json");

  @Autowired
  public FileHelper(@Value("${filePath}") String path) {
    this.dataBaseFile = new File(path);
  }

  public void writeInvoice(String string, File path) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false))) {
      bufferedWriter.write(string + System.getProperty("line.separator"));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public String readLines(File path) {
    if (!path.exists()) {
      return "";
    }

    String invoicesList = "";
    String currentJson;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      while ((currentJson = bufferedReader.readLine()) != null) {
        invoicesList = invoicesList + currentJson;
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return invoicesList;
  }

  public void replaceInvoicesFiles() {
    File temporaryFile = new File(temporaryDataBaseFile.getPath());
    File originalDatabaseFile = new File(dataBaseFile.getPath());
    originalDatabaseFile.delete();
    temporaryFile.renameTo(dataBaseFile);
  }

  public File getDataBaseFile() {
    return dataBaseFile;
  }

  public File getTemporaryDataBaseFile() {
    return temporaryDataBaseFile;
  }
}