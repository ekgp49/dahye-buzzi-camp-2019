package ekgp49.dbc.handler;

import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationAddCommand implements Command {
  Prompt prompt;
  InformationDao infoDao;

  public InformationAddCommand(Prompt prompt, InformationDao infoDao) {
    this.prompt = prompt;
    this.infoDao = infoDao;
  }

  @Override
  public void execute() {
    Information information = new Information();
    information.setCafeName(prompt.inputString("카페 상호: "));
    information.setCafeAddress(prompt.inputString("주소: "));
    information.setCafeCall(prompt.inputString("연락처: "));
    information.setCafeWebSite(prompt.inputString("웹사이트: "));
    information.setOpenTime(prompt.inputString("오픈시간: "));
    information.setCloseTime(prompt.inputString("종료시간: "));
    information.setHolliday(prompt.inputString("정기 휴일: "));
    information.setCafeMenu(prompt.inputString("메뉴: "));

    try {
      infoDao.insert(information);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
