package ekgp49.dbc.handler;

import java.util.Scanner;
import ekgp49.dbc.domain.Search;

public class SearchHandler {
  public Scanner input;
  ArrayList searchList;

  public SearchHandler(Scanner input) {
    this.input = input;
    searchList = new ArrayList();
  }

  public SearchHandler(Scanner input, int capacity) {
    this.input = input;
    searchList = new ArrayList(capacity);
  }

  public void listSearch() {
    System.out.println("검색 키워드");
    Object[] arr = searchList.toArray();
    for(Object obj : arr) {
      Search s = (Search) obj;
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

    searchList.add(search);

    System.out.println("저장하였습니다.");
  }

}
