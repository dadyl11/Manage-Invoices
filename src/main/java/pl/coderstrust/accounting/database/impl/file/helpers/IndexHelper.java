package pl.coderstrust.accounting.database.impl.file.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IndexHelper {

  private static File currentIdFile = new File("currentId.txt");

  public int generateId() throws Exception {
    if (currentIdFile.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(currentIdFile))) {
        int id = Integer.parseInt(br.readLine());
        saveId(id);
        return id;
      }
    } else {
      saveId(1);
      return 1;
    }
  }

  public void saveId(int id) throws Exception {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentIdFile))) {
      String stringId = String.valueOf(id + 1);
      bw.write(stringId);
    }
  }
}