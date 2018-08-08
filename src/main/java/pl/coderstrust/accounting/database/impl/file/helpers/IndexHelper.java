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
public class IndexHelper {

  private File currentIdFile;

  @Autowired
  public IndexHelper(@Value("${idFilePath}") String path) {
    currentIdFile = new File(path);
  }

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

  public File getCurrentIdFile() {
    return currentIdFile;
  }
}