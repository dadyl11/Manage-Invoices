package pl.coderstrust.accounting.database.impl.file.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

  public static File dataBaseFile = new File("invoices.json");
  public static File temporaryDataBaseFile = new File("temporaryInvoices.json");

  public void writeInvoice(String string, File path) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
      bufferedWriter.write(string);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public List<String> readLines(File path) {
    List<String> invoicesList = new ArrayList<>();
    String currentJson;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
      while ((currentJson = bufferedReader.readLine()) != null) {
        invoicesList.add(currentJson);
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    return invoicesList;
  }

  public void replaceInvoicesFiles() throws IOException {
    Path source = Paths.get(temporaryDataBaseFile.getPath());
    Path goal = Paths.get(dataBaseFile.getPath());
    Files.move(source, goal.resolve(source.getFileName()));
  }
}