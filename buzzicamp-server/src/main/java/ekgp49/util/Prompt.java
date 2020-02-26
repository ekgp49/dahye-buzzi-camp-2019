package ekgp49.util;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

public class Prompt {

  public static String getInputString(Scanner in, PrintStream out, String label) {
    out.println(label);
    out.println("!{}!");
    out.flush();
    return in.nextLine();
  }

  public static String getInputString(Scanner in, PrintStream out, String label,
      String defaultValue) {
    String value = getInputString(in, out, label);
    if (value.length() == 0) {
      return defaultValue;
    }
    return value;
  }

  public static int getInputInt(Scanner in, PrintStream out, String label) {
    return Integer.parseInt(getInputString(in, out, label));
  }

  public static int getInputInt(Scanner in, PrintStream out, String label, String defaultValue) {
    return Integer.parseInt(getInputString(in, out, label, defaultValue));
  }

  public static Date getInputDate(Scanner in, PrintStream out, String label) {
    return Date.valueOf(getInputString(in, out, label));
  }

  public static Date getInputDate(Scanner in, PrintStream out, String label, String defaultValue) {
    return Date.valueOf(getInputString(in, out, label, defaultValue));
  }
}
