package util;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  Scanner input;
  
  public Prompt(Scanner input) {
    this.input = input;
  }
  
  public String inputString(String label) {
    System.out.print(label);
    return input.nextLine();
  }
  
  public String inputString(String label, String value) {
    System.out.print(label);
    if (input.nextLine().equals(value)) {
      return value;
    }
    return input.nextLine();
  }
  
  public int inputInt(String label) {
    System.out.print(label);
    return Integer.parseInt(input.nextLine());
  }
  
  public int inputInt(String label, int value) {
    System.out.print(label);
    int newValue = Integer.parseInt(input.nextLine());
    if (newValue == value) {
      return value;
    }
    return newValue;
  }
  
  public Date inputDate(String label) {
    System.out.print(label);
    return Date.valueOf(input.nextLine());
  }
  
  public Date inputDate(String label, Date value) {
    System.out.print(label);
    Date newValue = Date.valueOf(input.nextLine());
    if (newValue.compareTo(value) == 0) {
      return value;
    }
    return newValue;
  }
}
