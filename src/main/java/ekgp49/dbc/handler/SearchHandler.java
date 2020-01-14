package ekgp49.dbc.handler;

import java.util.Arrays;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Search;
import util.Prompt;
import util.Queue;

public class SearchHandler {
  Prompt prompt;
  Queue<Search> list = new Queue<>();

  public SearchHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  public void keySearch(InformationHandler information) {
    Search search = new Search();
    search.setCafeArea(prompt.inputString("지역: "));
    search.setCafeName(prompt.inputString("카페 상호: "));    
    search.setCafeMenu(prompt.inputString("메뉴: "));
    //    search.setStarRate(prompt.inputInt("별점: "));
    this.list.offer(search);
    listSearch(search, information);
  }

  private void listSearch(Search search, InformationHandler information) {
    Information[] info = information.informationList.toArray(new Information[] {});
    Information[] arr = new Information[info.length];
    int index = 0;
    for (int i = 0; i < info.length; i++) {
      if (search.getCafeArea() == null) {
        search.setCafeArea(info[i].getCafeAddress()); 
      }
      if (search.getCafeName() == null) {
        search.setCafeName(info[i].getCafeName()); 
      }
      if (search.getCafeMenu() == null) {
        search.setCafeMenu(info[i].getCafeMenu()); 
      }
      if (info[i].getCafeName().contains(search.getCafeName())
          && info[i].getCafeAddress().contains(search.getCafeArea())
          && info[i].getCafeMenu().contains(search.getCafeMenu())) {
        arr[index++] = info[i];
      }
    }
    if (index == 0) {
      System.out.println("검색 결과가 없습니다.");
      return;
    }
    for(Information i : Arrays.copyOf(arr, index)) {
      System.out.printf("주소: %s, 상호: %s, 연락처: %s, "
          + "웹사이트: %s, %s시 ~ %s시, 휴일: %s, 메뉴: %s\n", 
          i.getCafeAddress(), i.getCafeName(), i.getCafeCall(),
          i.getCafeWebSite(), i.getOpenTime(), 
          i.getCloseTime(), i.getHolliday(), i.getCafeMenu());
    }
  }
}
