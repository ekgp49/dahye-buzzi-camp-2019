package ekgp49.dbc.handler;

import java.util.Scanner;
import ekgp49.dbc.domain.Information;
import util.ArrayList;

public class InformationHandler {
  public Scanner input;
  ArrayList<Information> informationList;
  private static int no = 1;

  public InformationHandler(Scanner input) {
    this.input = input;
    this.informationList = new ArrayList<>();
  }

  public InformationHandler(Scanner input, int capacity) {
    this.input = input;
    this.informationList = new ArrayList<>(capacity);
  }

  public void listInformaition() {
    Information[] arr = new Information[this.informationList.size()];
    this.informationList.toArray(arr);
    for(Information info : arr) {
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s\n", 
          info.getCafeName(), info.getCafeAddress(), info.getCafeCall(),
          info.getCafeWebSite(), info.getOpenTime(), 
          info.getCloseTime(), info.getHolliday(), info.getCafeMenu());
    }
  }

  public void addInformation() {
    Information information = new Information();
    information.setNo(no++);
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

    this.informationList.add(information);
    System.out.println("저장하였습니다.");
  }

  public void updateInformation() {
    System.out.println("가게 정보 번호는? ");
    int no = this.input.nextInt();
    input.nextLine();
    int index = -1;
    
    for (int i = 0; i < this.informationList.size(); i++) {
      if (this.informationList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.println("해당 번호의 정보가 없습니다.");
      return;
    }

    Information old = informationList.get(index);

    System.out.println("수정할 영역을 입력하세요.");
    System.out.println("1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간");
    System.out.println("6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체");
    System.out.print("입력 >> ");

    int command = input.nextInt();
    input.nextLine();

    switch(command) {
      case 1 : 
        System.out.print("카페 상호: ");
        old.setCafeName(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 2:
        System.out.print("주소: ");
        old.setCafeAddress(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 3:
        System.out.print("연락처: ");
        old.setCafeCall(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 4:
        System.out.print("웹사이트: ");
        old.setCafeWebSite(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 5:
        System.out.print("오픈시간: ");
        old.setOpenTime(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 6:
        System.out.print("종료시간: ");
        old.setCloseTime(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 7:
        System.out.print("정기 휴일은: ");
        old.setHolliday(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 8:
        System.out.print("메뉴는: ");
        old.setCafeMenu(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      case 9:
        System.out.print("카페 상호는: ");
        old.setCafeName(this.input.nextLine());
        System.out.print("주소는: ");
        old.setCafeAddress(this.input.nextLine());
        System.out.print("연락처는: ");
        old.setCafeCall(this.input.nextLine());
        System.out.print("웹사이트는: ");
        old.setCafeWebSite(this.input.nextLine());
        System.out.print("오픈시간은: ");
        old.setOpenTime(this.input.nextLine());
        System.out.print("종료시간은: ");
        old.setCloseTime(this.input.nextLine());
        System.out.print("정기 휴일은: ");
        old.setHolliday(this.input.nextLine());
        System.out.print("메뉴는: ");
        old.setCafeMenu(this.input.nextLine());
        System.out.println("수정했습니다.");
        break;
      default : 
        System.out.println("유효한 입력이 아닙니다.");
    }


    this.informationList.set(old, index);
    System.out.println("저장하였습니다.");
  }

  public void deleteInformaition() {
    System.out.println("가게 정보 번호는? ");
    int no = this.input.nextInt();
    input.nextLine();
    int index = -1;
    
    for (int i = 0; i < this.informationList.size(); i++) {
      if (this.informationList.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index == -1) {
      System.out.println("해당 번호의 정보가 없습니다.");
      return;
    }
    
    informationList.remove(index);
    System.out.println("정보를 삭제했습니다.");
  }
  
  

}
