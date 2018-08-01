package pl.coderstrust.accounting.database.impl.file.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

  public static File dataBaseFile = new File("invoices.json");
  public static File temporaryDataBaseFile = new File("temporaryInvoices.json");

  public void writeInvoice(String string, File path) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, false))) {
      bufferedWriter.write(string);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public String readLines(File path) {
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

  public void replaceInvoicesFiles() throws IOException {

    File temporaryFile = new File(temporaryDataBaseFile.getPath());
    File originalDatabaseFile = new File(dataBaseFile.getPath());
    originalDatabaseFile.delete();
    temporaryFile.renameTo(dataBaseFile);

//    Path source = Paths.get(temporaryDataBaseFile.getPath());
//    Path goal = Paths.get(dataBaseFile.getPath());
//    Files.move(source, goal.resolve(source.getFileName()));
  }
}