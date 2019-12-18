package ekgp49.dbc;

import java.util.Scanner;
public class App {
  public static void main(String[] args) {
    // 카페 명단 출력
    Scanner keyboard = new Scanner(System.in);
    final int SIZE = 100;
    String[] cafeArea = new String[SIZE];
    String[] cafeNum = new String[SIZE];
    String[] cafeName = new String[SIZE];
    String[] cafeMenu = new String[SIZE]; 
    String[] starRate = new String[SIZE];
    String response;
    int count = 0;

    for(int i = 0; i < SIZE; i++) {
      count++;
      System.out.print("지역은? : ");
      cafeArea[i] = keyboard.nextLine();

      System.out.print("카페 수는? : ");
      cafeNum[i] = keyboard.nextLine();

      System.out.print("카페 상호는? : ");
      cafeName[i] = keyboard.nextLine();    

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

    System.out.println("카페 명단");

    for(int i = 0; i < count; i++) {
      System.out.printf("%s, %s, %s, %s, %s\n", 
          cafeArea[i], cafeNum[i], cafeName[i], cafeMenu[i], starRate[i]);
    }
    
    //    System.out.printf("지역 : %s\n", cafeArea);
    //    System.out.printf("카페 수 : %s\n", cafeNum);
    //    System.out.printf("상호 : %s\n", cafeName);
    //    System.out.printf("메뉴 : %s\n", cafeMenu);
    //    System.out.printf("별점 : %s\n", starRate);

    keyboard.close();

  }

}
