package pl.coderstrust.accounting.database.impl.file.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileHelper {

  private File dataBaseFile = new File("invoices.json");
  private File temporaryDataBaseFile = new File("temporaryInvoices.json");

  @Autowired
  public FileHelper() {
  }

  public void writeInvoice(String string, File path) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false))) {
      bufferedWriter.write(string);
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

  public void setDataBaseFile(File dataBaseFile) {
    this.dataBaseFile = dataBaseFile;
  }
}