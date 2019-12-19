package ekgp49.dbc;

import java.util.Scanner;
public class App {
  public static void main(String[] args) {
    // 카페 명단 출력
    Scanner keyboard = new Scanner(System.in);
    final int SIZE = 100;

    class Search {
      String cafeArea;
      String cafeNum;
      String cafeName;
      String cafeMenu; 
      String starRate;
    }
    int count = 0;

    Search[] searches = new Search[SIZE];

    for(int i = 0; i < SIZE; i++) {
      count++;

      Search search = new Search();

      System.out.print("지역은? : ");
      search.cafeArea = keyboard.nextLine();

      System.out.print("카페 수는? : ");
      search.cafeNum = keyboard.nextLine();

      System.out.print("카페 상호는? : ");
      search.cafeName = keyboard.nextLine();    

      System.out.print("메뉴는? : ");
      search.cafeMenu = keyboard.nextLine();

      System.out.print("별점은? : ");
      search.starRate = keyboard.nextLine();

      searches[i] = search;

      System.out.println("계속 입력하시겠습니까? Y/N");
      System.out.print("=> ");

      String response = keyboard.nextLine(); 

      System.out.println();

      if(!response.equalsIgnoreCase("y")) {
        System.out.println("----------------------------------------");
        break;
      }
    }

    System.out.println("카페 명단");

    for(int i = 0; i < count; i++) {
      Search search = searches[i];
      System.out.printf("%s, %s, %s, %s, %s\n", 
          search.cafeArea, search.cafeNum, search.cafeName, search.cafeMenu, search.starRate);
    }

    //    System.out.printf("지역 : %s\n", cafeArea);
    //    System.out.printf("카페 수 : %s\n", cafeNum);
    //    System.out.printf("상호 : %s\n", cafeName);
    //    System.out.printf("메뉴 : %s\n", cafeMenu);
    //    System.out.printf("별점 : %s\n", starRate);

    keyboard.close();

  }

}
