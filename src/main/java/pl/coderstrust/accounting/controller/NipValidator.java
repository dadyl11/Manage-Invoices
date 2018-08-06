package pl.coderstrust.accounting.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NipValidator {

  private Pattern pattern = Pattern.compile("[1-9]{1}\\d{9}");

  public static boolean isValidNIP(String nip) {
    Matcher matcher = pattern.matcher(nip);
    return matcher.matches() && controlSumIsCorrect(nip);
  }

  private static boolean controlSumIsCorrect(String nip) {
    int[] checks = {6, 5, 7, 2, 3, 4, 5, 6, 7};
    String[] niparray = nip.split("");
    int checksum = 0;
    for (int i = 0; i < 9; i++) {
      checksum += checks[i] * Integer.parseInt(niparray[i]);
    }
    int checksumActual = Integer.parseInt(nip.substring(9));
    return checksum % 11 == checksumActual;
  }
}
