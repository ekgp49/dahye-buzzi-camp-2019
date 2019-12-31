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
      info.getCafeName(), info.getCafeAddress(), info.getCafeCall(),
      info.getCafeWebSite(), info.getOpenTime(), 
      info.getCloseTime(), info.getHolliday(), info.getCafeMenu(), info.getStarRate());
    }
  }

  public void addInformation() {
    Information information = new Information();
    System.out.print("카페 상호는? : ");
    information.setCafeName(this.input.nextLine());
    System.out.print("주소는? : ");
    information.setCafeAddress(this.input.nextLine());
    System.out.print("연락처는? : ");
    information.setCafeCall(this.input.nextLine());
    System.out.print("웹사이트는? : ");
    information.setCafeWebSite(this.input.nextLine());
    System.out.print("오픈시간은? : ");
    information.setOpenTime(this.input.nextLine());
    System.out.print("종료시간은? : ");
    information.setCloseTime(this.input.nextLine());
    System.out.print("정기 휴일은? : ");
    information.setHolliday(this.input.nextLine());
    System.out.print("메뉴는? : ");
    information.setCafeMenu(this.input.nextLine());
    System.out.print("별점은? : ");
    information.setStarRate(this.input.nextLine());
   
    this.informations[this.informationsCount++] = information;
    System.out.println("저장하였습니다.");
  }

}
