package ekgp49.dbc.handler;

import java.util.Arrays;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Search;
import util.LinkedList;
import util.Prompt;

public class SearchHandler {
  Prompt prompt;
  LinkedList<Search> searchList;
  private int no = 1;

  public SearchHandler(Prompt prompt) {
    this.prompt = prompt;
    searchList = new LinkedList<>();
  }

  public void keySearch(InformationHandler information) {
    Search search = new Search();
    search.setNo(no++);
    //    search.setCafeArea(prompt.inputString("지역: "));
    search.setCafeName(prompt.inputString("카페 상호: "));    
    //    search.setCafeMenu(prompt.inputString("메뉴: "));
    //    search.setStarRate(prompt.inputInt("별점: "));

    this.searchList.add(search);
    listSearch(search, information);
  }

  void listSearch(Search search, InformationHandler information) {
    Information[] info = information.informationList.toArray(new Information[] {});
    Information[] arr = new Information[info.length];
    int index = 0;
    for (int i = 0; i < info.length; i++) {
      if (info[i].getCafeName().equals(search.getCafeName())) {
        arr[index++] = info[i];
      }
    }
    if (arr.length == 0) {
      System.out.println("검색 결과가 없습니다.");
      return;
    }
    for(Information i : Arrays.copyOf(arr, index)) {
      System.out.printf("%s, %s, %s, %s, %s ~ %s, %s, %s\n", 
          i.getCafeName(), i.getCafeAddress(), i.getCafeCall(),
          i.getCafeWebSite(), i.getOpenTime(), 
          i.getCloseTime(), i.getHolliday(), i.getCafeMenu());
    }
  }

}
