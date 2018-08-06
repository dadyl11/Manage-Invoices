package pl.coderstrust.accounting.database.impl.file.helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class IndexHelper {

  public static File currentIdFile = new File("currentIdFile.txt");

  public int generateId() throws IOException {
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

  public void saveId(int id) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentIdFile))) {
      String stringId = String.valueOf(id + 1);
      bw.write(stringId);
    }
  }
}