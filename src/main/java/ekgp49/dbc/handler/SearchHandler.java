package ekgp49.dbc.handler;

import ekgp49.dbc.domain.Search;
import util.LinkedList;
import util.Prompt;

public class SearchHandler {
  Prompt prompt;
  LinkedList<Search> searchList;

  public SearchHandler(Prompt prompt) {
    this.prompt = prompt;
    searchList = new LinkedList<>();
  }

  public void listSearch() {
    System.out.println("검색 키워드");
  }

  public void addSearch() {
    Search search = new Search();
    search.setCafeArea(prompt.inputString("지역: "));
    search.setCafeName(prompt.inputString("카페 상호: "));    
    search.setCafeMenu(prompt.inputString("메뉴: "));
    search.setStarRate(prompt.inputInt("별점: "));

    searchList.add(search);

    System.out.println("저장하였습니다.");
  }
}
