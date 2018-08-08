package pl.coderstrust.accounting.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class NipValidator {

  private final Pattern pattern = Pattern.compile("[1-9]{1}\\d{9}");

  public boolean isValid(String nip) {
    if (nip == null) {
      throw new IllegalArgumentException("Nip is null!");
    }
    Matcher matcher = pattern.matcher(nip);
    return matcher.matches() && controlSumIsCorrect(nip);
  }

  private static boolean controlSumIsCorrect(String nip) {
    int[] checksArray = {6, 5, 7, 2, 3, 4, 5, 6, 7};
    int[] nipAsArray = Stream.of(nip.split("")).mapToInt(Integer::parseInt).toArray();
    int checksum = 0;
    for (int i = 0; i < 9; i++) {
      checksum += checksArray[i] * nipAsArray[i];
    }
    int checksumActual = Integer.parseInt(nip.substring(9));
    return checksum % 11 == checksumActual;
  }
}
