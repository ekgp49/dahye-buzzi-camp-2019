package ekgp49.dbc.handler;

import java.util.Scanner;
import ekgp49.dbc.domain.Search;

public class SearchHandler {
  static final int SEARCH_SIZE = 100;

  public Scanner input;
  Search[] searches = new Search[SEARCH_SIZE];
  int searchesCount = 0;

  public SearchHandler(Scanner input) {
    this.input = input;
  }
  
  public void listSearch() {
    System.out.println("검색 키워드");
    for(int i = 0; i < this.searchesCount; i++) {
      Search s = this.searches[i];
      System.out.printf("%s, %s, %s, %s\n", 
          s.getCafeArea(), s.getCafeName(), s.getCafeMenu(), s.getStarRate());
    }
  }

  public void addSearch() {
    Search search = new Search();
    System.out.print("지역은? : ");
    search.setCafeArea(this.input.nextLine());
    System.out.print("카페 상호는? : ");
    search.setCafeName(this.input.nextLine());    
    System.out.print("메뉴는? : ");
    search.setCafeMenu(this.input.nextLine());
    System.out.print("별점은? : ");
    search.setStarRate(this.input.nextLine());

    this.searches[this.searchesCount++] = search;
    System.out.println("저장하였습니다.");
  }

}
