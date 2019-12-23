package ekgp49.dbc;

import java.util.Scanner;

public class InformationHandler {
  static Scanner keyboard;
  static final int INFORMATION_SIZE = 100;
  static class Information {
    String cafeName;
    String cafeAddress;
    String cafeCall;
    String cafeWebSite; 
    String openTime;
    String closeTime;
    String holliday; 
    String cafeMenu;
    String starRate;
  }
  static Information[] informations = new Information[INFORMATION_SIZE];
  static int informationsCount = 0;
 
  static void listInformaition() {
    for(int i = 0; i < informationsCount; i++) {
      Information info = informations[i];
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s, %s\n", 
      info.cafeName, info.cafeAddress, info.cafeCall, info.cafeWebSite, info.openTime, 
      info.closeTime, info.holliday, info.cafeMenu, info.starRate);
    }
  }

  static void addInformation() {
    Information information = new Information();
    System.out.print("카페 상호는? : ");
    information.cafeName = keyboard.nextLine();
    System.out.print("주소는? : ");
    information.cafeAddress = keyboard.nextLine();
    System.out.print("연락처는? : ");
    information.cafeCall = keyboard.nextLine();
    System.out.print("웹사이트는? : ");
    information.cafeWebSite = keyboard.nextLine();
    System.out.print("오픈시간은? : ");
    information.openTime = keyboard.nextLine();
    System.out.print("종료시간은? : ");
    information.closeTime = keyboard.nextLine();
    System.out.print("정기 휴일은? : ");
    information.holliday = keyboard.nextLine();
    System.out.print("메뉴는? : ");
    information.cafeMenu = keyboard.nextLine();
    System.out.print("별점은? : ");
    information.starRate = keyboard.nextLine();
   
    informations[informationsCount++] = information;
    System.out.println("저장하였습니다.");
  }

}
