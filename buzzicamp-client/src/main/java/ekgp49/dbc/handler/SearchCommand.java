package ekgp49.dbc.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import ekgp49.dbc.domain.Information;
import ekgp49.dbc.domain.Search;
import util.Prompt;

public class SearchCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public SearchCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    Information[] info = null;
    try {
      out.writeUTF("/search");
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      List<Information> infos = (List<Information>) in.readObject();
      info = infos.toArray(new Information[] {});
    } catch (Exception e) {
      System.out.println("실행 중 오류 발생" + e.getMessage());
    }

    Search search = new Search();
    search.setCafeName(prompt.inputString("카페 상호: "));
    search.setCafeArea(prompt.inputString("지역: "));
    search.setCafeMenu(prompt.inputString("메뉴: "));
    // search.setStarRate(prompt.inputInt("별점: "));

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
    for (Information i : Arrays.copyOf(arr, index)) {
      System.out.printf("상호: %s, 주소: %s,  연락처: %s, " + "웹사이트: %s, %s시 ~ %s시, 휴일: %s, 메뉴: %s\n",
          i.getCafeName(), i.getCafeAddress(), i.getCafeCall(), i.getCafeWebSite(), i.getOpenTime(),
          i.getCloseTime(), i.getHolliday(), i.getCafeMenu());
    }
  }
}
