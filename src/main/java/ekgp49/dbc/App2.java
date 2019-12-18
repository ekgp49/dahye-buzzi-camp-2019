package ekgp49.dbc;

import java.util.Scanner;
public class App2 {

  public static void main(String[] args) {
    // 한 개의 카페 정보 출력
    Scanner keyboard = new Scanner(System.in);
    final int SIZE = 100;
    String[] cafeName = new String[SIZE];
    String[] cafeAddress = new String[SIZE];
    String[] cafeCall = new String[SIZE];
    String[] cafeWebSite = new String[SIZE]; 
    String[] openTime = new String[SIZE];
    String[] closeTime = new String[SIZE];
    String[] holliday = new String[SIZE]; 
    String[] cafeMenu = new String[SIZE];
    String[] starRate = new String[SIZE];
    String response;
    int count = 0;

    for (int i = 0; i < SIZE; i++) {
      count++;
      System.out.print("카페 상호는? : ");
      cafeName[i] = keyboard.nextLine();

      System.out.print("주소는? : ");
      cafeAddress[i] = keyboard.nextLine();

      System.out.print("연락처는? : ");
      cafeCall[i] = keyboard.nextLine();

      System.out.print("웹사이트는? : ");
      cafeWebSite[i] = keyboard.nextLine();

      System.out.print("오픈시간은? : ");
      openTime[i] = keyboard.nextLine();

      System.out.print("종료시간은? : ");
      closeTime[i] = keyboard.nextLine();

      System.out.print("정기 휴일은? : ");
      holliday[i] = keyboard.nextLine();

      System.out.print("메뉴는? : ");
      cafeMenu[i] = keyboard.nextLine();

      System.out.print("별점은? : ");
      starRate[i] = keyboard.nextLine();

      System.out.println("계속 입력하시겠습니까? Y/N");
      System.out.print("=> ");

      response = keyboard.nextLine(); 

      System.out.println();

      if(!response.equalsIgnoreCase("y")) {
        System.out.println("----------------------------------------");
        break;
      }
    }

    for(int i = 0; i < count; i++) {
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s, %s\n", 
          cafeName[i], cafeAddress[i], cafeCall[i], cafeWebSite[i], openTime[i], 
          closeTime[i], holliday[i], cafeMenu[i], starRate[i]);
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
