package ekgp49.dbc.handler;

import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationUpdateCommand implements Command {
  Prompt prompt;
  InformationDao infoDao;

  public InformationUpdateCommand(Prompt prompt, InformationDao infoDao) {
    this.prompt = prompt;
    this.infoDao = infoDao;
  }

  @Override
  public void execute() {
    int no = prompt.inputInt("가게 정보 번호: ");
    Information old = null;
    try {
      old = infoDao.findByNo(no);
    } catch (Exception e) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    int command = prompt.inputInt("수정할 영역을 입력하세요.\n" + "1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간\n"
        + "6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체\n" + "입력 >> ");

    switch (command) {
      case 1:
        old.setCafeName(prompt.inputString("카페 상호: "));
        break;
      case 2:
        old.setCafeAddress(prompt.inputString("주소: "));
        break;
      case 3:
        old.setCafeCall(prompt.inputString("연락처: "));
        break;
      case 4:
        old.setCafeWebSite(prompt.inputString("웹사이트: "));
        break;
      case 5:
        old.setOpenTime(prompt.inputString("오픈시간: "));
        break;
      case 6:
        old.setCloseTime(prompt.inputString("종료시간: "));
        break;
      case 7:
        old.setHolliday(prompt.inputString("정기 휴일: "));
        break;
      case 8:
        old.setCafeMenu(prompt.inputString("메뉴: "));
        break;
      case 9:
        old.setCafeName(prompt.inputString("카페 상호: "));
        old.setCafeAddress(prompt.inputString("주소: "));
        old.setCafeCall(prompt.inputString("연락처: "));
        old.setCafeWebSite(prompt.inputString("웹사이트: "));
        old.setOpenTime(prompt.inputString("오픈시간: "));
        old.setCloseTime(prompt.inputString("종료시간: "));
        old.setHolliday(prompt.inputString("정기 휴일: "));
        old.setCafeMenu(prompt.inputString("메뉴: "));
        break;
      default:
        System.out.println("유효한 입력이 아닙니다.");
    }

    try {
      infoDao.update(old);
      System.out.println("정보를 변경했습니다.");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
