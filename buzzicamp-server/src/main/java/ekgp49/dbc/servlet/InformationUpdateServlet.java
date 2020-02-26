package ekgp49.dbc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import ekgp49.dbc.DataLoaderListener;
import ekgp49.dbc.dao.InfoMenuDao;
import ekgp49.dbc.dao.InformationDao;
import ekgp49.dbc.domain.InfoMenu;
import ekgp49.dbc.domain.Information;
import util.Prompt;

public class InformationUpdateServlet implements Servlet {
  InformationDao infoDao;
  InfoMenuDao infoMenuDao;

  public InformationUpdateServlet(InformationDao infoDao, InfoMenuDao infoMenuDao) {
    this.infoDao = infoDao;
    this.infoMenuDao = infoMenuDao;
  }

  @Override
  public void service(PrintStream out, Scanner in) throws Exception {
    int no = Prompt.getInputInt(in, out, "번호? ");
    Information info = infoDao.findByNo(no);
    if (info == null) {
      out.println("해당번호의 회원정보이 없습니다.");
      return;
    }
    int command = Prompt.getInputInt(in, out, "수정할 영역을 입력하세요."//
        + "\n1 : 상호, 2: 주소, 3: 연락처, 4: 웹사이트, 5: 오픈시간\n"
        + "6 : 종료시간, 7: 정기 휴일, 8: 메뉴, 9: 전체 \n입력 >>");

    DataLoaderListener.con.setAutoCommit(false);
    try {
      switch (command) {
        case 1:
          info.setCafeName(Prompt.getInputString(in, out, "상호: "));
          break;
        case 2:
          info.setCafeAddress(Prompt.getInputString(in, out, "주소: "));
          break;
        case 3:
          info.setCafeCall(Prompt.getInputString(in, out, "연락처: \\n!{}!: "));
          break;
        case 4:
          info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트: "));
          break;
        case 5:
          info.setOpenTime(Prompt.getInputString(in, out, "오픈시간: "));
          break;
        case 6:
          info.setCloseTime(Prompt.getInputString(in, out, "종료시간: "));
          break;
        case 7:
          info.setHolliday(Prompt.getInputString(in, out, "정기 휴일: "));
          break;
        case 8:
          out.println("메뉴: ");
          changeMenu(out, in, no);
          break;
        case 9:
          info.setCafeName(Prompt.getInputString(in, out, "상호: "));
          info.setCafeAddress(Prompt.getInputString(in, out, "주소: "));
          info.setCafeCall(Prompt.getInputString(in, out, "연락처: "));
          info.setCafeWebSite(Prompt.getInputString(in, out, "웹사이트: "));
          info.setOpenTime(Prompt.getInputString(in, out, "오픈시간: "));
          info.setCloseTime(Prompt.getInputString(in, out, "클로즈시간: "));
          info.setHolliday(Prompt.getInputString(in, out, "정기 휴일: "));
          out.println("메뉴: ");
          changeMenu(out, in, no);
          break;
        default:
          out.println("유효한 입력이 아닙니다.");
      }

      if (infoDao.update(info) > 0) {
        DataLoaderListener.con.commit();
        out.println("정보를 수정하였습니다.");
      } else {
        throw new Exception("정보 수정 실패");
      }
    } catch (Exception e) {
      out.println(e.getMessage());
      DataLoaderListener.con.rollback();
    } finally {
      DataLoaderListener.con.setAutoCommit(true);
    }

  }

  private void changeMenu(PrintStream out, Scanner in, int no) throws Exception {
    infoMenuDao.deleteAll(no);
    while (true) {
      InfoMenu menu = new InfoMenu();
      String name = Prompt.getInputString(in, out, "> ");
      if (name.length() == 0) {
        break;
      }
      menu.setName(name);
      menu.setInformationNo(no);
      infoMenuDao.insert(menu);
    }
  }
}
