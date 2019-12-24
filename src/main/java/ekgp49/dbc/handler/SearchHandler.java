package ekgp49.dbc.handler;

import java.util.Scanner;
import ekgp49.dbc.domain.Search;

public class SearchHandler {
  public static Scanner keyboard;
  static final int SEARCH_SIZE = 100;
  
  static Search[] searches = new Search[SEARCH_SIZE];
  static int searchesCount = 0;
  
  public static void listSearch() {
    System.out.println("검색 키워드");
    for(int i = 0; i < searchesCount; i++) {
      Search s = searches[i];
      System.out.printf("%s, %s, %s, %s\n", 
          s.cafeArea, s.cafeName, s.cafeMenu, s.starRate);
    }
  }

  public static void addSearch() {
    Search search = new Search();
    System.out.print("지역은? : ");
    search.cafeArea = keyboard.nextLine();
    System.out.print("카페 상호는? : ");
    search.cafeName = keyboard.nextLine();    
    System.out.print("메뉴는? : ");
    search.cafeMenu = keyboard.nextLine();
    System.out.print("별점은? : ");
    search.starRate = keyboard.nextLine();
    
    searches[searchesCount++] = search;
    System.out.println("저장하였습니다.");
  }

}
