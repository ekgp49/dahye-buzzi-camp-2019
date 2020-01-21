package ekgp49.dbc.handler;

import java.util.List;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationAddCommand implements Command {
  Prompt prompt;
  List<Information> informationList;
  private int no = 1;

  public InformationAddCommand(Prompt prompt, List<Information> list) {
    this.prompt = prompt;
    this.informationList = list;
  }

  @Override
  public void execute() {
    Information information = new Information();
    information.setNo(this.no++);
    information.setCafeName(prompt.inputString("카페 상호: "));
    information.setCafeAddress(prompt.inputString("주소: "));
    information.setCafeCall(prompt.inputString("연락처: "));
    information.setCafeWebSite(prompt.inputString("웹사이트: "));
    information.setOpenTime(prompt.inputString("오픈시간: "));
    information.setCloseTime(prompt.inputString("종료시간: "));
    information.setHolliday(prompt.inputString("정기 휴일: "));
    information.setCafeMenu(prompt.inputString("메뉴: "));

    this.informationList.add(information);
    System.out.println("저장하였습니다.");
  }
}
