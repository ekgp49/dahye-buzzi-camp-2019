package ekgp49.dbc.handler;

import java.util.Scanner;
import ekgp49.dbc.domain.Information;

public class InformationHandler {
  static final int INFORMATION_SIZE = 100;

  public Scanner input;
  Information[] informations = new Information[INFORMATION_SIZE];
  int informationsCount = 0;
 
  public InformationHandler(Scanner input) {
    this.input = input;
  }
  
  public void listInformaition() {
    for(int i = 0; i < this.informationsCount; i++) {
      Information info = this.informations[i];
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s, %s\n", 
      info.cafeName, info.cafeAddress, info.cafeCall, info.cafeWebSite, info.openTime, 
      info.closeTime, info.holliday, info.cafeMenu, info.starRate);
    }
  }

  public void addInformation() {
    Information information = new Information();
    System.out.print("카페 상호는? : ");
    information.cafeName = input.nextLine();
    System.out.print("주소는? : ");
    information.cafeAddress = input.nextLine();
    System.out.print("연락처는? : ");
    information.cafeCall = input.nextLine();
    System.out.print("웹사이트는? : ");
    information.cafeWebSite = input.nextLine();
    System.out.print("오픈시간은? : ");
    information.openTime = input.nextLine();
    System.out.print("종료시간은? : ");
    information.closeTime = input.nextLine();
    System.out.print("정기 휴일은? : ");
    information.holliday = input.nextLine();
    System.out.print("메뉴는? : ");
    information.cafeMenu = input.nextLine();
    System.out.print("별점은? : ");
    information.starRate = input.nextLine();
   
    this.informations[this.informationsCount++] = information;
    System.out.println("저장하였습니다.");
  }

}
