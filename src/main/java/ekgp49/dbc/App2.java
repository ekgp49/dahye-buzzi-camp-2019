package ekgp49.dbc;

import java.util.Scanner;
public class App2 {

  public static void main(String[] args) {
    // 한 개의 카페 정보 출력
    Scanner keyboard = new Scanner(System.in);
    final int SIZE = 100;
    
    class Information {
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
    int count = 0;

    Information[] informations = new Information[SIZE];
    
    for (int i = 0; i < SIZE; i++) {
      count++;
      
      Information info = new Information();
      
      System.out.print("카페 상호는? : ");
      info.cafeName = keyboard.nextLine();

      System.out.print("주소는? : ");
      info.cafeAddress = keyboard.nextLine();

      System.out.print("연락처는? : ");
      info.cafeCall = keyboard.nextLine();

      System.out.print("웹사이트는? : ");
      info.cafeWebSite = keyboard.nextLine();

      System.out.print("오픈시간은? : ");
      info.openTime = keyboard.nextLine();

      System.out.print("종료시간은? : ");
      info.closeTime = keyboard.nextLine();

      System.out.print("정기 휴일은? : ");
      info.holliday = keyboard.nextLine();

      System.out.print("메뉴는? : ");
      info.cafeMenu = keyboard.nextLine();

      System.out.print("별점은? : ");
      info.starRate = keyboard.nextLine();
  
      informations[i] = info;
      
      System.out.println("계속 입력하시겠습니까? Y/N");
      System.out.print("=> ");

      String response = keyboard.nextLine(); 

      System.out.println();

      if(!response.equalsIgnoreCase("y")) {
        System.out.println("----------------------------------------");
        break;
      }
    }

    for(int i = 0; i < count; i++) {
      Information info = informations[i];

      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s, %s\n", 
      info.cafeName, info.cafeAddress, info.cafeCall, info.cafeWebSite, info.openTime, 
      info.closeTime, info.holliday, info.cafeMenu, info.starRate);
    }


    //    System.out.println("상호 : 카페 0336");
    //    System.out.println("주소 : 경기도 수원시 장안구 조원동");
    //    System.out.println("연락처 : 010-5295-0336");
    //    System.out.println("웹사이트 : www.cafe0336.com");
    //    System.out.println("영업시간 : 6AM ~ 3PM");
    //    System.out.println("정기휴일 : 격주 월요일");
    //    System.out.println("메뉴 : 아메리카노");
    //    System.out.println("별점 : 별5개");
    //    

    keyboard.close();
  }

}
